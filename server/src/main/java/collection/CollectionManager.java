package collection;

import data.Flat;
import data.House;
import io.FileManager;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Manager of main collection
 */
public class CollectionManager {
    private java.util.Vector<Flat> myCollection = new java.util.Vector<>();
    private java.time.LocalDateTime lastInitTime;
    private java.time.LocalDateTime lastSaveTime;
    private FileManager flManager;

    public CollectionManager(FileManager fl) {
        flManager = fl;
        lastSaveTime = null;

        Load();
    }

    /**
     * Load collection from file
     */
    public void Load()
    {
        myCollection = flManager.readCollection();
        lastInitTime = java.time.LocalDateTime.now();
    }

    /**
     * Save collection in file
     */
    public void Save()
    {
        flManager.WriteCollection(myCollection);
    }

    /**
     * Add to collection
     * @param fl Flat
     */
    public void addToCollection(Flat fl)
    {
        myCollection.add(fl);
    }

    /**
     * Clear collection command
     */
    public void clearCollection()
    {
        myCollection.clear();
    }

    /**
     * Generate ID for new element
     * @return ID
     */
    public int generateNextId() {
        if (myCollection.isEmpty()) return 1;
        return myCollection.lastElement().getId() + 1;
    }

    /**
     * Get collection size
     * @return collection size
     */
    public int collectoinSize()
    {
        return myCollection.size();
    }

    /**
     * Get first element of collection
     * @return Element
     */
    public Flat getFirst(){
        if (myCollection.isEmpty()) return null;
        return myCollection.firstElement();
    }

    /**
     * Remove last element of collection
     * @return can delete or not
     */
    public boolean removeLast(){
        if (myCollection.isEmpty()) return false;
        myCollection.remove(myCollection.lastElement());
        return true;
    }

    /**
     * Filter by pattern name
     * @param str Pattern
     * @return List of elements
     */
    public  String nameFillteredInfo(String str)
    {
        String info = "";
        for (Flat fl : myCollection)
        {
            if(Pattern.matches(str, fl.getName()))
                info += fl.toString() + "\n";
        }
        return info;
    }

    /**
     * Delete by house
     * @param hs House
     */
    public boolean deleteByHouse(House hs)
    {
        /*for (Flat fl : myCollection)
        {
            if(fl.getHouse().equals(hs))
            {
                myCollection.remove(fl);
                return true;
            }
        }
        return false;*/
        myCollection.removeIf(flat -> flat.getHouse().equals(hs));
        return true;
    }

    /**
     * Delite by ID
     * @param id ID
     */
    public boolean deleteByID(int id)
    {
        /*for (Flat fl : myCollection)
        {
            if(fl.getId() == id)
            {
                myCollection.remove(fl);
                return true;
            }
        }
        return false;*/
        myCollection.removeIf(flat -> flat.getId() == id);
        return true;
    }

    /**
     * Delete all greater then get
     * @param flt Flat
     */
    public int deleteGreater(Flat flt)
    {
        Vector<Flat> buffer = new Vector<Flat>();
        for (Flat fl : myCollection)
        {
            if(fl.getId() > flt.getId())
            {
                buffer.add(fl);
            }
        }
        myCollection.removeAll(buffer);
        return buffer.size();
    }

    /**
     * Replace element
     * @param ft Element
     */
    public boolean replace(Flat ft)
    {
        myCollection = myCollection.stream()
                .map(flat -> flat.getId() == ft.getId() ? ft : flat)
                .collect(Collectors.toCollection(Vector::new));
        /*for (Flat fl : myCollection)
        {
            if (fl.getId() == ft.getId())
            {
                Collections.replaceAll(myCollection, fl, ft);
                return true;
            }
        }*/
        return true;
    }

    /**
     * Print sorted element
     * @return List of elements
     */
    public String printSort()
    {
        Vector<Flat> temp = new Vector<Flat>(myCollection);
        temp.sort(Comparator.comparingInt(Flat::getId));
        String out = "";
        for (Flat fl : temp)
        {
            out += fl.toString();
            out += "\n\n";
        }
        return out;
    }

    /**
     * Print just collection
     * @return List of elements
     */
    public String printNormal()
    {
        /*String out = "";
        for (Flat fl : myCollection)
        {
            out += fl.toString();
            out += "\n\n";
        }*/
        if (myCollection.isEmpty())
            return "Коллекция пуста";

        return myCollection.stream().reduce("", (sum, m) -> sum += m + "\n\n", (sum1, sum2) -> sum1 + sum2).trim();
    }

    @Override
    public String toString() {
        return "Тип коллекции = Vector<Flat>\n" + "Время = " + lastInitTime+ "\n" +
                "Количество элементов = " + myCollection.size();
    }
}
