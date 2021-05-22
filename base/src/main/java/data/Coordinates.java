package data;


import java.io.Serializable;
import java.util.Objects;

/**
 * Coordinates data
 */
public class Coordinates implements Serializable {

    private float x;

    private float y;

    public Coordinates(float X, float Y)
    {
        x = X;
        y = Y;
    }

    public  float GetX()
    {
        return x;
    }

    public float GetY()
    {
        return y;
    }

    /**
     * Convert to CSV data
     * @return CSV data
     */
    public String toCSV(){
        return Float.toString(x) + ";" + Float.toString(y);
    }

    @Override
    public String toString() {
        return  "x=" + x +
                ", y=" + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.x, x) == 0 &&
                Float.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
