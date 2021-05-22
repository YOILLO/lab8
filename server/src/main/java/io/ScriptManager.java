package io;

import main.Main;

import java.io.*;

/**
 * Work with script file
 */
public class ScriptManager {
    BufferedReader bufferedReader;

    public ScriptManager(String file){
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            Main.logger.error("Ошибка открытия файла");
        }
    }

    /**
     * Read string from file
     * @return String from filr
     */
    public String readLine(){
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            Main.logger.error("Ошибка чтения файла");
        }
        return null;
    }
}
