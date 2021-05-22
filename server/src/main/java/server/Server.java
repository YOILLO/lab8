package server;

import commands.CommandManager;
import main.Main;
import messages.AnswerMsg;
import messages.CommandMsg;
import messages.Status;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server {
    private int PORT;
    private SocketAddress socketAddress;
    private DatagramChannel datagramChannel;
    private final int bufferSize = 1024;
    private CommandManager commandManager;

    public Server (int port, CommandManager com){
        PORT = port;
        commandManager = com;
    }

    private boolean openSocket(){
        try {
            Main.logger.info("Открываю сокет");
            socketAddress = new InetSocketAddress(PORT);
            datagramChannel = DatagramChannel.open();
            datagramChannel.bind(socketAddress);
            Main.logger.info("Сокет открыт");
        } catch (IllegalArgumentException | IOException e){
            Main.logger.error("Ошибка открытия сокета");
            return false;
        }
        return true;
    }

    private CommandMsg read(){

        ByteBuffer buffer = ByteBuffer.wrap(new byte[bufferSize]);
        try {
            Main.logger.info("Ожидаю входящее сообщение");
            socketAddress = datagramChannel.receive(buffer);
            return deSerialize(buffer.array());
        } catch (IOException e) {
            Main.logger.error("Ошибка чтения файла");
        }
        return null;
    }

    private CommandMsg deSerialize(byte[] data){
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
            CommandMsg commandMsg = (CommandMsg) objectInputStream.readObject();
            objectInputStream.close();
            return commandMsg;
        } catch (IOException e) {
            Main.logger.error("Ошибка чтения объекта");
        } catch (ClassNotFoundException e) {
            Main.logger.error("Не найден класс");
        }
        return null;
    }

    private void sendMsg(AnswerMsg answerMsg){
        try {
            Main.logger.info("Начинаю отправку ответа");
            byte[] bf = serializer(answerMsg);
            if (bf == null)
                throw new IOException();
            ByteBuffer buff = ByteBuffer.wrap(bf);
            datagramChannel.send(buff, socketAddress);
            Main.logger.info("Отправлено");
        } catch (IOException e) {
            Main.logger.error("Ошибка отправки сообщения");
        }
    }

    private byte[] serializer(AnswerMsg answerMsg){
        try{
            Main.logger.info("Начинаю сериализацию");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bufferSize);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(answerMsg);
            objectOutputStream.close();

            byte[] ret = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            Main.logger.info("Сереализировал");
            return ret;
        } catch (IOException e) {
            Main.logger.error("Ошибка перевода объекта в массив");
        }
        return null;
    }

    private void close(){
        try {
            Main.logger.info("Закрывю канал");
            datagramChannel.close();
            Main.logger.info("Канал закрыт");
        } catch (IOException e) {
            Main.logger.error("Ошибка закрытия");
        }
    }

    public void run(){
        if(!openSocket()) {
            Main.logger.error("Неполучилось открыть сокет");
            return;
        }
        boolean work = true;
        while (work){
            CommandMsg msg = read();
            AnswerMsg ans = new AnswerMsg();
            if(!commandManager.launchCommand(msg, ans)){
                ans.setStatus(Status.EXIT);
                work = false;
            }
            sendMsg(ans);
        }
        close();
    }
}
