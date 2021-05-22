package data;

/**
 * Transport enum
 */
public enum Transport {
    LITTLE,
    NORMAL,
    ENOUGH;

    /**
     * Return all elements of enum
     * @return List of elements
     */
    public static String nameList() {
        String nameList = "";
        for (Transport transport : values()) {
            nameList += transport.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
