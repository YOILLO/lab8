package io;

import data.*;
import exceptions.WrongFormat;
import exceptions.EmptyIO;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Control and parse console command
 */
public class Console {
    private Scanner userScaner;

    public Console(Scanner sc)
    {
        userScaner = sc;
    }

    /**
     * Get flat from user command
     * @return Got flat
     */
    public RowFlat askFlat()
    {

        String name = null;
        while (name == null) {
            try{
                println("Ведите имя: ");
                String nm = userScaner.nextLine().trim();
                if (nm == null) throw new EmptyIO();
                if (nm.equals("")) throw new EmptyIO();
                if (nm.contains(";")) throw  new WrongFormat();
                name = nm;
            }
            catch (EmptyIO e)
            {
                printError("Строка не может быть пустой");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            } catch (WrongFormat wrongFormat) {
                printError("Не используйте \";\"");
            }
        }

        float x;
        while (true) {
            try{
                println("Ведите кординату X: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (name.equals("")) throw new EmptyIO();
                x = Float.parseFloat(st);
                break;
            }
            catch (EmptyIO e)
            {
                printError("Строка не может быть пустой");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        float y;
        while (true) {
            try{
                println("Ведите кординату Y: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                y = Float.parseFloat(st);
                break;
            }
            catch (EmptyIO e)
            {
                printError("Строка не может быть пустой");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        Integer erea;
        while (true) {
            try {
                println("Ведите район: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                erea = Integer.parseInt(st);
                if (erea < 0) throw new WrongFormat();
                break;
            } catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            } catch (WrongFormat e){
                printError("Неверный формат, число должно быть больше 0");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        Integer numberOfRooms;
        while (true) {
            try {
                println("Ведите количество комнат: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                numberOfRooms = Integer.parseInt(st);
                if (numberOfRooms < 0 || numberOfRooms > 17) throw new WrongFormat();
                break;
            } catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            } catch (WrongFormat e) {
                printError("Неверный формат, число должно быть больше 0 и меньше 17");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        float price;
        while (true) {
            try{
                println("Ведите цену: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                price = Float.parseFloat(st);
                if (price < 0 || price > 191928932) throw new WrongFormat();
                break;
            }
            catch (EmptyIO e)
            {
                printError("Строка не может быть пустой");
            }catch (WrongFormat e){
                printError("Неверный формат, число должно быть больше 0 и меньше 191928932");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        Furnish furnish;
        while (true) {
            try{
                println("Ведите вид мебели, варианты:");
                println(Furnish.nameList());
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                furnish = Furnish.valueOf(st);
                break;
            }
            catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            }catch (NoSuchElementException exception) {
                printError("Категория не распознана!");
            }catch (IllegalArgumentException exception) {
                printError("Категории нет в списке!");
            }
        }

        Transport transport;
        while (true) {
            try{
                println("Ведите вид транспорта, варианты:");
                println(Transport.nameList());
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                transport = Transport.valueOf(st);
                break;
            }
            catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            }catch (NoSuchElementException exception) {
                printError("Категория не распознана!");
            }catch (IllegalArgumentException exception) {
                printError("Категории нет в списке!");
            }
        }

        String houseName = null;
        while (houseName == null) {
            try{
                println("Ведите имя дома: ");
                String nm = userScaner.nextLine().trim();
                if (nm == null) throw new EmptyIO();
                if (nm.equals("")) throw new EmptyIO();
                if (nm.contains(";")) throw  new WrongFormat();
                houseName = nm;
            }
            catch (EmptyIO e)
            {
                printError("Строка не может быть пустой");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }catch (WrongFormat wrongFormat) {
                printError("Не используйте \";\"");
            }
        }

        long year;
        while (true) {
            try {
                println("Ведите возраст дома: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                year = Long.parseLong(st);
                if (year < 0) throw new WrongFormat();
                break;
            } catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            } catch (WrongFormat e){
                printError("Неверный формат, число должно быть больше 0");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        long numberOfFlors;
        while (true) {
            try {
                println("Ведите количество этажей: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                numberOfFlors = Long.parseLong(st);
                if (numberOfFlors < 0) throw new WrongFormat();
                break;
            } catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            } catch (WrongFormat e){
                printError("Неверный формат, число должно быть больше 0");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }


        return new RowFlat(name, new Coordinates(x,y), erea,
                numberOfRooms, price, furnish, transport,
                new House(houseName, year, numberOfFlors));
    }

    /**
     * Get flat from user but with consume ID
     * @return Flat
     */
    public Flat askFlatWithID()
    {
        int Id = 0;
        while (true) {
            try {
                println("Ведите ID: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                Id = Integer.parseInt(st);
                if (Id < 0) throw new WrongFormat();
                break;
            } catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            } catch (WrongFormat e){
                printError("Неверный формат, число должно быть больше 0");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        println("ID = " + Integer.toString(Id));

        String name = null;
        while (name == null) {
            try{
                println("Ведите имя: ");
                String nm = userScaner.nextLine().trim();
                if (nm == null) throw new EmptyIO();
                if (nm.equals("")) throw new EmptyIO();
                if (nm.contains(";")) throw  new WrongFormat();
                name = nm;
            }
            catch (EmptyIO e)
            {
                printError("Строка не может быть пустой");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            } catch (WrongFormat wrongFormat) {
                printError("Не используйте \";\"");
            }
        }

        float x;
        while (true) {
            try{
                println("Ведите кординату X: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (name.equals("")) throw new EmptyIO();
                x = Float.parseFloat(st);
                break;
            }
            catch (EmptyIO e)
            {
                printError("Строка не может быть пустой");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        float y;
        while (true) {
            try{
                println("Ведите кординату Y: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                y = Float.parseFloat(st);
                break;
            }
            catch (EmptyIO e)
            {
                printError("Строка не может быть пустой");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        Integer erea;
        while (true) {
            try {
                println("Ведите район: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                erea = Integer.parseInt(st);
                if (erea < 0) throw new WrongFormat();
                break;
            } catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            } catch (WrongFormat e){
                printError("Неверный формат, число должно быть больше 0");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        Integer numberOfRooms;
        while (true) {
            try {
                println("Ведите количество комнат: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                numberOfRooms = Integer.parseInt(st);
                if (numberOfRooms < 0 || numberOfRooms > 17) throw new WrongFormat();
                break;
            } catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            } catch (WrongFormat e) {
                printError("Неверный формат, число должно быть больше 0 и меньше 17");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        float price;
        while (true) {
            try{
                println("Ведите цену: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                price = Float.parseFloat(st);
                if (price < 0 || price > 191928932) throw new WrongFormat();
                break;
            }
            catch (EmptyIO e)
            {
                printError("Строка не может быть пустой");
            }catch (WrongFormat e){
                printError("Неверный формат, число должно быть больше 0 и меньше 191928932");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        Furnish furnish;
        while (true) {
            try{
                println("Ведите вид мебели, варианты:");
                println(Furnish.nameList());
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                furnish = Furnish.valueOf(st);
                break;
            }
            catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            }catch (NoSuchElementException exception) {
                printError("Категория не распознана!");
            }catch (IllegalArgumentException exception) {
                printError("Категории нет в списке!");
            }
        }

        Transport transport;
        while (true) {
            try{
                println("Ведите вид транспорта, варианты:");
                println(Transport.nameList());
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                transport = Transport.valueOf(st);
                break;
            }
            catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            }catch (NoSuchElementException exception) {
                printError("Категория не распознана!");
            }catch (IllegalArgumentException exception) {
                printError("Категории нет в списке!");
            }
        }

        String houseName = null;
        while (houseName == null) {
            try{
                println("Ведите имя дома: ");
                String nm = userScaner.nextLine().trim();
                if (nm == null) throw new EmptyIO();
                if (nm.equals("")) throw new EmptyIO();
                if (nm.contains(";")) throw  new WrongFormat();
                houseName = nm;
            }
            catch (EmptyIO e)
            {
                printError("Строка не может быть пустой");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }catch (WrongFormat wrongFormat) {
                printError("Не используйте \";\"");
            }
        }

        long year;
        while (true) {
            try {
                println("Ведите возраст дома: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                year = Long.parseLong(st);
                if (year < 0) throw new WrongFormat();
                break;
            } catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            } catch (WrongFormat e){
                printError("Неверный формат, число должно быть больше 0");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        long numberOfFlors;
        while (true) {
            try {
                println("Ведите количество этажей: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                numberOfFlors = Long.parseLong(st);
                if (numberOfFlors < 0) throw new WrongFormat();
                break;
            } catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            } catch (WrongFormat e){
                printError("Неверный формат, число должно быть больше 0");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }


        return new Flat(Id, name, new Coordinates(x,y), java.time.LocalDate.now(), erea,
                numberOfRooms, price, furnish, transport, new House(houseName, year, numberOfFlors));
    }

    /**
     * Ask house from user
     * @return House
     */
    public  House askHouse()
    {
        String houseName = null;
        while (houseName == null) {
            try{
                println("Ведите имя дома: ");
                String nm = userScaner.nextLine().trim();
                if (nm == null) throw new EmptyIO();
                if (nm.equals("")) throw new EmptyIO();
                if (nm.contains(";")) throw  new WrongFormat();
                houseName = nm;
            }
            catch (EmptyIO e)
            {
                printError("Строка не может быть пустой");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            } catch (WrongFormat wrongFormat) {
                printError("Не используй \";\"");
            }
        }

        long year;
        while (true) {
            try {
                println("Ведите возраст дома: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                year = Long.parseLong(st);
                if (year < 0) throw new WrongFormat();
                break;
            } catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            } catch (WrongFormat e){
                printError("Неверный формат, число должно быть больше 0");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        long numberOfFlors;
        while (true) {
            try {
                println("Ведите количество этажей: ");
                String st = userScaner.nextLine().trim();
                if (st == null) throw new EmptyIO();
                if (st.equals("")) throw new EmptyIO();
                numberOfFlors = Long.parseLong(st);
                if (numberOfFlors < 0) throw new WrongFormat();
                break;
            } catch (EmptyIO e) {
                printError("Строка не может быть пустой");
            } catch (WrongFormat e){
                printError("Неверный формат, число должно быть больше 0");
            }catch (NumberFormatException exception) {
                printError("Должно быть представлено числом!");
            }catch (NoSuchElementException exception) {
                printError("имя не распознано!");
            }
        }

        return new House(houseName, year, numberOfFlors);
    }

    /**
     * Static print error command
     * @param msg Error msg
     */
    public static void printError(String msg)
    {
        System.out.println("Err: " + msg);
    }

    /**
     * Just print command
     * @param msg Message
     */
    public static void println(String msg){
        System.out.println(msg);
    }

}
