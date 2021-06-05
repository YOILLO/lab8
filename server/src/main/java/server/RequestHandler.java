package server;

import commands.CommandManager;
import database_managers.DatabaseUserManager;
import messages.AnswerMsg;
import messages.CommandMsg;
import messages.Status;

import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class RequestHandler extends Thread{
    private CommandMsg commandMsg;
    private DatagramChannel datagramChannel;
    private DatabaseUserManager databaseUserManager;
    private CommandManager commandManager;
    private SocketAddress socketAddress;

    public RequestHandler(CommandMsg com, DatagramChannel ds, DatabaseUserManager dbum, CommandManager cm, SocketAddress sa){
        commandManager = cm;
        commandMsg = com;
        datagramChannel = ds;
        databaseUserManager = dbum;
        socketAddress = sa;

        start();

    }
    @Override
    public void run(){
        AnswerMsg ans = new AnswerMsg();
        ans.setStatus(Status.ERROR);
        if (commandMsg.getCommand().trim().equals("save")) {
            ans.addError("Нет права на сохранения");
        } else if (commandMsg.getCommand().trim().equals("register")) {
            if (databaseUserManager.insertUser(commandMsg.getUser())){
                ans.setStatus(Status.OK);
            }
        } else if (commandMsg.getCommand().trim().equals("enter")) {
            if(databaseUserManager.checkUserByUsernameAndPassword(commandMsg.getUser())){
                ans.setStatus(Status.OK);
            }
        } else if (!commandManager.launchCommand(commandMsg, ans)) {
            ans.setStatus(Status.EXIT);
        }
        AnswerHandler answerHandler = new AnswerHandler(datagramChannel, socketAddress, ans);
    }
}
