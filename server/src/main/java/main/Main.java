package main;

import collection.CollectionManager;
import commands.*;
import database_managers.DatabaseCollectionManager;
import database_managers.DatabaseManager;
import database_managers.DatabaseUserManager;
import messages.AnswerMsg;
import messages.CommandMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.Server;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Main app class
 * @author Iaroslav Kalviiainen
 */
public class Main {

    public static final Logger logger = LoggerFactory.getLogger("My logger");

    public static void main(String[] args)
    {
        try {
            System.setOut(new PrintStream(System.out, true, "windows-1251"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DatabaseManager databaseManager = new DatabaseManager("jdbc:postgresql://pg:5432/studs", "s311772", "axu862");
        DatabaseUserManager databaseUserManager = new DatabaseUserManager(databaseManager);
        DatabaseCollectionManager databaseCollectionManager = new DatabaseCollectionManager(databaseManager, databaseUserManager);
        CollectionManager collection = new CollectionManager(databaseCollectionManager);
        CommandManager commandManager = new CommandManager(
                new AbstractCommand[]{new AddCom(collection),
                new AddIfMinCom(collection), new ClearCom(collection),
                new ExitCom(), new FilterContainsNameCom(collection),
                new RemoveAnyByHouseCom(collection), new PrintAscendingCom(collection),
                new InfoCom(collection), new ShowCom(collection),
                new RemoveLastCom(collection), new RemoveById(collection),
                new RemoveGreaterCom(collection), new UpdateIDCom(collection),
                new SaveCom(collection)}, databaseUserManager);
        Server server = new Server(1812, commandManager, databaseUserManager);

        Runnable save = () -> {
            Scanner scanner = new Scanner(System.in, "windows-1251");
            while (true){
                String com = scanner.nextLine();
                if (com.trim().equals("save")){
                } else if (com.trim().equals("stop_server")){
                    Server.interrupt();
                    break;
                }
            }
        };

        Thread th = new Thread(save);

        th.start();

        server.run();
    }
}
