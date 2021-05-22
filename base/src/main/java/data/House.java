package data;
/**
 * House data
 */

import java.io.Serializable;
import java.util.Objects;

public class House implements Serializable {

    private String name; //Поле не может быть null

    private long year; //Значение поля должно быть больше 0

    private long numberOfFloors; //Значение поля должно быть больше 0

    public House(String nm, long ya, long numOF)
    {
        name = nm;
        year = ya;
        numberOfFloors = numOF;
    }

    public String getName() {
        return name;
    }

    public long getYear() {
        return year;
    }

    public long getNumberOfFloors() {
        return numberOfFloors;
    }
    /**
     * Convert to CSV data
     * @return CSV data
     */
    public String toCSV(){
        return name + ";" + Long.toString(year) + ";" + Long.toString(numberOfFloors);
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", year=" + year +
                ", numberOfFloors=" + numberOfFloors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return year == house.year &&
                numberOfFloors == house.numberOfFloors &&
                Objects.equals(name, house.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, numberOfFloors);
    }
}
