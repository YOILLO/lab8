package server;

import main.Main;
import messages.AnswerMsg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class AnswerHandler extends Thread{

    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private AnswerMsg answerMsg;

    public AnswerHandler (DatagramChannel dc, SocketAddress sa, AnswerMsg am){
        datagramChannel = dc;
        socketAddress = sa;
        answerMsg = am;

        start();
    }


    private void sendMsg(AnswerMsg answerMsg) {
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

    private byte[] serializer(AnswerMsg answerMsg) {
        try {
            Main.logger.info("Начинаю сериализацию");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Server.bufferSize);
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

    public void run(){
        sendMsg(answerMsg);
    }
}
