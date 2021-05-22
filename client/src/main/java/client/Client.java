package client;

import main.Main;
import messages.AnswerMsg;
import messages.CommandMsg;
import messages.Status;

import javax.xml.crypto.Data;
import io.Console;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

import static io.Console.println;
import static io.Console.printError;

public class Client {
    private int PORT;
    private String ADDR;
    private SocketAddress socketAddress;
    private DatagramSocket datagramSocket;
    private final int bufferSize = 1024;
    private Console console;
    private Scanner scanner;

    public Client (String adrres , int port, Console con, Scanner sc){
        PORT = port;
        console = con;
        ADDR = adrres;
        scanner = sc;
    }

    private boolean openSocket(){
        try {
            println("Открываю сокет");
            socketAddress = new InetSocketAddress(ADDR ,PORT);
            datagramSocket = new DatagramSocket();
            println("Сокет открыт");
        } catch (IllegalArgumentException | IOException e){
            printError("Ошибка открытия сокета");
            return false;
        }
        return true;
    }

    private AnswerMsg read(){

        byte[] buffer = new byte[bufferSize];
        try {
            //println("Ожидаю входящее сообщение");
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(datagramPacket);
            return deSerialize(datagramPacket.getData());
        } catch (IOException e) {
            printError("Ошибка чтения файла");
        }
        return null;
    }

    private AnswerMsg deSerialize(byte[] data){
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
            AnswerMsg answerMsg = (AnswerMsg) objectInputStream.readObject();
            objectInputStream.close();
            return answerMsg;
        } catch (IOException e) {
            printError("Ошибка чтения объекта");
        } catch (ClassNotFoundException e) {
            printError("Не найден класс");
        }
        return null;
    }

    private void sendMsg(CommandMsg commandMsg){
        try {
            //Main.logger.info("Начинаю отправку ответа");
            byte[] bf = serializer(commandMsg);
            if (bf == null)
                throw new IOException();
            DatagramPacket datagramPacket = new DatagramPacket(bf, bf.length, socketAddress);
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            printError("Ошибка отправки сообщения");
        }
    }

    private byte[] serializer(CommandMsg commandMsg){
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bufferSize);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(commandMsg);
            objectOutputStream.close();

            byte[] ret = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return ret;
        } catch (IOException e) {
            printError("Ошибка перевода объекта в массив");
        }
        return null;
    }

    private void close(){
        println("Закрывю канал");
        datagramSocket.close();
        println("Канал закрыт");
    }

    public void run() {
        if (!openSocket()) {
            printError("Неполучилось открыть сокет");
            return;
        }
        boolean work = true;
        while (work) {
            String[] userCommand;
            userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
            Serializable obj = null;
            if (userCommand[0].trim().equals("add")){
                obj = console.askFlat();
            } else if (userCommand[0].trim().equals("update")){
                obj = console.askFlat();
            } else if (userCommand[0].trim().equals("remove_any_by_house")){
                obj = console.askHouse();
            } else if (userCommand[0].trim().equals("remove_greater")){
                obj = console.askFlatWithID();
            }
            CommandMsg commandMsg = new CommandMsg(userCommand[0], userCommand[1], obj);
            sendMsg(commandMsg);
            AnswerMsg answerMsg = read();
            println(answerMsg.getAnswer());
            if (answerMsg.getStatus().equals(Status.EXIT)){
                work = false;
            }
        }
    }
}
