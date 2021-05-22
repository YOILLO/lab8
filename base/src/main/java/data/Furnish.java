package data;

/**
 * Furnish enum
 */
public enum Furnish {
    DESIGNER,
    FINE,
    BAD,
    LITTLE;

    /**
     * Get list of all element of enum
     * @return List of all elements
     */
    public static String nameList() {
        String nameList = "";
        for (Furnish furnish : values()) {
            nameList += furnish.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
