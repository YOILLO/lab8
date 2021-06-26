package collection;

import data.Flat;
import data.House;
import data.RowFlat;
import database_managers.DatabaseCollectionManager;
import messages.User;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Manager of main collection
 */
public class CollectionManager {
    private java.time.LocalDateTime lastInitTime;
    private java.time.LocalDateTime lastSaveTime;
    private DatabaseCollectionManager databaseCollectionManager;
    private ArrayList<Flat> myCollection = new ArrayList<>();

    public CollectionManager(DatabaseCollectionManager dbclm) {
        lastSaveTime = null;
        databaseCollectionManager = dbclm;

        update();
    }

    /**
     * Load collection from file
     */
    public void update(){
        myCollection = databaseCollectionManager.getCollection();
    }

    /**
     * Add to collection
     * @param fl Flat
     */
    public void addToCollection(RowFlat fl, User user)
    {
        databaseCollectionManager.insertFlat(fl, user);
        update();
    }

    /**
     * Clear collection command
     */
    public void clearCollection(User user)
    {
        databaseCollectionManager.clearCollection(user);
        update();
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
        return myCollection.stream().findFirst().get();
    }

    /**
     * Remove last element of collection
     * @return can delete or not
     */
    public boolean removeLast(){
        if (myCollection.isEmpty()) return false;
        int lastId = 0;
        for (Flat fl : myCollection){
            lastId = fl.getId();
        }
        databaseCollectionManager.deleteFlatById(lastId);
        update();
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
        for (Flat fl : myCollection)
        {
            if(fl.getHouse().equals(hs))
            {
                databaseCollectionManager.deleteFlatById(fl.getId());
                update();
                return true;
            }
        }
        return false;
        //myCollection.removeIf(flat -> flat.getHouse().equals(hs));
        //return true;
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
        databaseCollectionManager.deleteFlatById(id);
        update();
        //myCollection.removeIf(flat -> flat.getId() == id);
        return true;
    }

    /**
     * Delete all greater then get
     * @param flt Flat
     */
    public int deleteGreater(Flat flt)
    {
        for (Flat fl : myCollection)
        {
            if(fl.getPrice() > flt.getPrice())
            {
                databaseCollectionManager.deleteFlatById(fl.getId());
            }
        }
        update();
        return myCollection.size();
    }

    /**
     * Replace element
     * @param ft Element
     */
    public boolean replace(int id, RowFlat ft)
    {
        /*myCollection = myCollection.stream()
                .map(flat -> flat.getId() == ft.getId() ? ft : flat)
                .collect(Collectors.toCollection(Vector::new));*/
        /*for (Flat fl : myCollection)
        {
            if (fl.getId() == ft.getId())
            {
                Collections.replaceAll(myCollection, fl, ft);
                return true;
            }
        }*/
        databaseCollectionManager.updateFloatById(id, ft);
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
            return "Collection is empty";

        return myCollection.stream().reduce("", (sum, m) -> sum += m + "\n\n", (sum1, sum2) -> sum1 + sum2).trim();
    }

    public ArrayList<Flat> getCollection() {
        return myCollection;
    }

    @Override
    public String toString() {
        return "Collection type = Vector<Flat>\n" + "Time = " + lastInitTime+ "\n" +
                "Size = " + myCollection.size();
    }
}
