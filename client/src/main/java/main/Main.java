package main;

import client.Client;
import io.Console;

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
        Scanner scanner = new Scanner(System.in, "windows-1251");
        Console console = new Console(scanner);
        Client client = new Client("localhost", 1812, console, scanner);

        client.run();
    }
}
