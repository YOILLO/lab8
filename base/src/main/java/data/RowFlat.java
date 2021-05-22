package data;

public class RowFlat {

    private String name; //Поле не может быть null, Строка не может быть пустой

    private Coordinates coordinates; //Поле не может быть null

    private Integer area; //Значение поля должно быть больше 0

    private Integer numberOfRooms; //Максимальное значение поля: 17, Значение поля должно быть больше 0

    private Float price; //Максимальное значение поля: 191928932, Значение поля должно быть больше 0

    private Furnish furnish; //Поле не может быть null

    private Transport transport; //Поле может быть null

    private House house; //Поле не может быть null

    public RowFlat (String nm, Coordinates cd, Integer ea, Integer nOR, Float pc, Furnish fsh,
                 Transport ts, House hs)
    {
        name = nm;
        coordinates = cd;
        area = ea;
        numberOfRooms = nOR;
        price = pc;
        furnish = fsh;
        transport = ts;
        house = hs;
    }

    public String getName()
    {
        return name;
    }

    public Coordinates getCoordinates()
    {
        return coordinates;
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


}
