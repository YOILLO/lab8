package main;

import lab5.commands.*;
import lab5.io.Console;
import lab5.io.FileManager;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Main app class
 * @author Iaroslav Kalviiainen
 */
public class Main {
    public static void main(String[] args)
    {
        try {
            System.setOut(new PrintStream(System.out, true, "windows-1251"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String file = "";
        try {
            file = args[0];
        }catch (ArrayIndexOutOfBoundsException e){
            Console.printError("Нет файла");
        }
        Console.println("Фаил: " + file);
        FileManager fileManager = new FileManager(file);
        CollectionManager collection = new CollectionManager(fileManager);
        Scanner scanner = new Scanner(System.in, "windows-1251");
        Console console = new Console(scanner, collection);
        CommandManager commandManager = new CommandManager(console, scanner,
                new AbstractCommand[]{new AddCom(collection, console),
                new AddIfMinCom(collection, console, scanner), new ClearCom(collection),
                new ExitCom(), new FilterContainsNameCom(collection),
                new RemoveAnyByHouseCom(collection, console), new PrintAscendingCom(collection),
                new InfoCom(collection), new ShowCom(collection),
                new RemoveLastCom(collection), new RemoveById(collection),
                new RemoveGreaterCom(collection, console, scanner), new UpdateIDCom(collection, console),
                new SaveCom(collection)});
        commandManager.ConsoleMod();
    }
}
