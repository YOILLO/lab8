package io;

import data.*;
import main.Main;

import java.io.*;

import java.time.LocalDate;
import java.util.Vector;

/**
 * Manage collection file
 */
public class FileManager {
    private String FileName;

    public FileManager(String file)
    {
        FileName = file;
    }

    /**
     * Read collection from file
     * @return Collection
     */
    public Vector<Flat> readCollection(){
        try {
            Vector<Flat> buffer = new Vector<Flat>();
            BufferedReader reader = new BufferedReader(new FileReader(FileName));
            String str;
            while ((str = reader.readLine()) != null){
                String[] arr = str.split(";");
                Flat bufFl = new Flat(Integer.parseInt(arr[0].trim()),
                        arr[1].trim(), new Coordinates(Float.parseFloat(arr[2].trim()), Float.parseFloat(arr[3].trim())),
                        LocalDate.parse(arr[4].trim()), Integer.parseInt(arr[5].trim()), Integer.parseInt(arr[6].trim()),
                        Float.parseFloat(arr[7].trim()), Furnish.valueOf(arr[8].trim()), Transport.valueOf(arr[9].trim()),
                        new House(arr[10].trim(), Long.parseLong(arr[11].trim()), Long.parseLong(arr[12].trim())));
                buffer.add(bufFl);

            }
            reader.close();
            Main.logger.info("Коллекция успешно считана");
            return buffer;
        }
        catch (java.io.FileNotFoundException e) {
            Main.logger.error("Ошибка открытия файла.");
        } catch (IOException e) {
            Main.logger.error("Ошибка доступа к файлу");
        } catch (NumberFormatException e){
            Main.logger.error("Ошибка парсинга");
        } catch (NullPointerException e){
            Main.logger.error("Ошибка парсинга 2");
        }
        return new Vector<>();
    }

    /**
     * Save collection to file
     * @param coll Collection
     */
    public void WriteCollection(Vector<Flat> coll)
    {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FileName));
            for (Flat fl : coll){
                bufferedWriter.write(fl.toCSV());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            Main.logger.info("Успешно записано");
        }catch (IOException e) {
            Main.logger.error("Ошибка записи файла");
        }
    }
}
