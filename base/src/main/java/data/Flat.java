package data;


import messages.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Flat data
 */
public class Flat implements Serializable {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private String name; //Поле не может быть null, Строка не может быть пустой

    private Coordinates coordinates; //Поле не может быть null

    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private Integer area; //Значение поля должно быть больше 0

    private Integer numberOfRooms; //Максимальное значение поля: 17, Значение поля должно быть больше 0

    private Float price; //Максимальное значение поля: 191928932, Значение поля должно быть больше 0

    private Furnish furnish; //Поле не может быть null

    private Transport transport; //Поле может быть null

    private House house; //Поле не может быть null

    private User user;

    public Flat (int Id, String nm, Coordinates cd, java.time.LocalDate crD, Integer ea, Integer nOR, Float pc, Furnish fsh,
                 Transport ts, House hs, User us)
    {
        id = Id;
        name = nm;
        coordinates = cd;
        creationDate = crD;
        area = ea;
        numberOfRooms = nOR;
        price = pc;
        furnish = fsh;
        transport = ts;
        house = hs;
        user = us;
    }

    public Flat (RowFlat rowFlat, int Id, User us){
        id = Id;
        name = rowFlat.getName();
        coordinates = rowFlat.getCoordinates();
        creationDate = LocalDate.now();
        area = rowFlat.getArea();
        numberOfRooms = rowFlat.getNumberOfRooms();
        price = rowFlat.getPrice();
        furnish = rowFlat.getFurnish();
        transport = rowFlat.getTransport();
        house = rowFlat.getHouse();
        user = us;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Coordinates getCoordinates()
    {
        return coordinates;
    }
    public java.time.LocalDate getCreationDate()
    {
        return creationDate;
    }
    public Integer getArea()
    {
        return area;
    }
    public Integer getNumberOfRooms()
    {
        return numberOfRooms;
    }

    public Float getPrice()
    {
        return price;
    }
    public Furnish getFurnish()
    {
        return furnish;
    }
    public Transport getTransport()
    {
        return transport;
    }
    public House getHouse()
    {
        return house;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int compareTo(Flat flat)
    {
        return Integer.compare(id, flat.getId());
    }
    /**
     * Convert to CSV data
     * @return CSV data
     */
    public String toCSV(){
        return Integer.toString(id) + ";" +
                name + ";" +
                coordinates.toCSV() + ";" +
                creationDate.toString() + ";" +
                area.toString() + ";" +
                numberOfRooms.toString() + ";" +
                price.toString() + ";" +
                furnish.toString() + ";" +
                transport.toString() + ";" +
                house.toCSV();
    }

    @Override
    public String toString() {
        return "Flat{\n" +
                "id=" + id + ",\n" +
                "name='" + name + '\'' + ",\n" +
                "coordinates: " + coordinates + ",\n" +
                "creationDate=" + creationDate + ",\n" +
                "area=" + area + ",\n" +
                "numberOfRooms=" + numberOfRooms + ",\n" +
                "price=" + price + ",\n" +
                "furnish=" + furnish + ",\n" +
                "transport=" + transport + ",\n" +
                "house: " + house + ",\n" +
                "owner: " + user.getUsername() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return id == flat.id &&
                Objects.equals(name, flat.name) &&
                Objects.equals(coordinates, flat.coordinates) &&
                Objects.equals(creationDate, flat.creationDate) &&
                Objects.equals(area, flat.area) &&
                Objects.equals(numberOfRooms, flat.numberOfRooms) &&
                Objects.equals(price, flat.price) &&
                furnish == flat.furnish &&
                transport == flat.transport &&
                Objects.equals(house, flat.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, area, numberOfRooms, price, furnish, transport, house);
    }
}
