package io;

import data.*;
import exceptions.EmptyIO;
import exceptions.WrongFormat;
import main.Main;
import messages.User;

import java.io.*;
import java.time.LocalDate;
import java.util.NoSuchElementException;

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
    public RowFlat readRowFlat(){
        try {
            String name = null;

            String st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            if (st.contains(";")) throw new WrongFormat();
            name = st;

            float x;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (name.equals("")) throw new EmptyIO();
            x = Float.parseFloat(st);


            float y;

            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            y = Float.parseFloat(st);


            Integer erea;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            erea = Integer.parseInt(st);
            if (erea < 0) throw new WrongFormat();


            Integer numberOfRooms;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            numberOfRooms = Integer.parseInt(st);
            if (numberOfRooms < 0 || numberOfRooms > 17) throw new WrongFormat();


            float price;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            price = Float.parseFloat(st);
            if (price < 0 || price > 191928932) throw new WrongFormat();


            Furnish furnish;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            furnish = Furnish.valueOf(st);


            Transport transport;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            transport = Transport.valueOf(st);

            String houseName = null;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            if (st.contains(";")) throw new WrongFormat();
            houseName = st;

            long year;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            year = Long.parseLong(st);
            if (year < 0) throw new WrongFormat();

            long numberOfFlors;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            numberOfFlors = Long.parseLong(st);
            if (numberOfFlors < 0) throw new WrongFormat();


            return new RowFlat(name, new Coordinates(x, y), erea,
                    numberOfRooms, price, furnish, transport,
                    new House(houseName, year, numberOfFlors));
        } catch (WrongFormat wrongFormat) {
            Main.logger.error("Ошибка парсинга");
        } catch (IOException e) {
            Main.logger.error("Ошибка парсинга");
        } catch (EmptyIO emptyIO) {
            Main.logger.error("Ошибка парсинга");
        }
        return null;
    }

    public Flat readFlat(User user){
        try {
            int Id = 0;

            String st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            Id = Integer.parseInt(st);


            String name = null;

            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            if (st.contains(";")) throw new WrongFormat();
            name = st;

            float x;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            x = Float.parseFloat(st);


            float y;

            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            y = Float.parseFloat(st);


            Integer erea;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            erea = Integer.parseInt(st);
            if (erea < 0) throw new WrongFormat();


            Integer numberOfRooms;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            numberOfRooms = Integer.parseInt(st);
            if (numberOfRooms < 0 || numberOfRooms > 17) throw new WrongFormat();


            float price;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            price = Float.parseFloat(st);
            if (price < 0 || price > 191928932) throw new WrongFormat();


            Furnish furnish;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            furnish = Furnish.valueOf(st);


            Transport transport;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            transport = Transport.valueOf(st);

            String houseName = null;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            if (st.contains(";")) throw new WrongFormat();
            houseName = st;

            long year;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            year = Long.parseLong(st);
            if (year < 0) throw new WrongFormat();

            long numberOfFlors;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            numberOfFlors = Long.parseLong(st);
            if (numberOfFlors < 0) throw new WrongFormat();


            return new Flat(Id, name, new Coordinates(x, y), LocalDate.now(), erea,
                    numberOfRooms, price, furnish, transport,
                    new House(houseName, year, numberOfFlors), user);
        } catch (WrongFormat wrongFormat) {
            Main.logger.error("Ошибка парсинга");
        } catch (IOException e) {
            Main.logger.error("Ошибка парсинга");
        } catch (EmptyIO emptyIO) {
            Main.logger.error("Ошибка парсинга");
        }
        return null;
    }

    public House readHouse(){
        try {

            String houseName = null;
            String st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            if (st.contains(";")) throw new WrongFormat();
            houseName = st;

            long year;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            year = Long.parseLong(st);
            if (year < 0) throw new WrongFormat();

            long numberOfFlors;
            st = bufferedReader.readLine().trim();
            if (st == null) throw new EmptyIO();
            if (st.equals("")) throw new EmptyIO();
            numberOfFlors = Long.parseLong(st);
            if (numberOfFlors < 0) throw new WrongFormat();


            return new House(houseName, year, numberOfFlors);
        } catch (WrongFormat wrongFormat) {
            Main.logger.error("Ошибка парсинга");
        } catch (IOException e) {
            Main.logger.error("Ошибка парсинга");
        } catch (EmptyIO emptyIO) {
            Main.logger.error("Ошибка парсинга");
        }
        return null;
    }
}
