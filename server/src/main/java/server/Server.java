package server;

import commands.CommandManager;
import database_managers.DatabaseUserManager;
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
    public static int bufferSize = 4096;
    private CommandManager commandManager;
    private DatabaseUserManager databaseUserManager;

    public Server(int port, CommandManager com, DatabaseUserManager dbum) {
        PORT = port;
        commandManager = com;
        databaseUserManager = dbum;
    }

    private boolean openSocket() {
        try {
            Main.logger.info("Открываю сокет");
            socketAddress = new InetSocketAddress(PORT);
            datagramChannel = DatagramChannel.open();
            datagramChannel.bind(socketAddress);
            Main.logger.info("Сокет открыт");
        } catch (IllegalArgumentException | IOException e) {
            Main.logger.error("Ошибка открытия сокета");
            return false;
        }
        return true;
    }

    private CommandMsg read() {

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

    private CommandMsg deSerialize(byte[] data) {
        try {
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

    private void close() {
        try {
            Main.logger.info("Закрывю канал");
            datagramChannel.close();
            Main.logger.info("Канал закрыт");
        } catch (IOException e) {
            Main.logger.error("Ошибка закрытия");
        }
    }

    static private boolean work = true;

    public void run() {
        if (!openSocket()) {
            Main.logger.error("Неполучилось открыть сокет");
            return;
        }
        work = true;
        while (work) {
            CommandMsg msg = read();
            RequestHandler requestHandler = new RequestHandler(msg, datagramChannel, databaseUserManager, commandManager, socketAddress);
        }
        close();
    }


    public static void interrupt() {
        work = false;
    }
}
