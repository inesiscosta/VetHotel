package hva.core;

/**
 * Enumeration of the possible types of trees.
 */
public enum TreeType {
    DECIDUOUS,
    EVERGREEN;


    /**
     * A method for processing the string input from the App to the enum used internally
     *
     * @param type The input string
     * @return treeType the Enum that corresponds to the string
     */
    public static TreeType stringToEnum(String type) {  //FIXME Ines temos de ver ser fazemos isto ou other way maybe override valueOf? 
        TreeType treeType = null;
        switch (type) {
            case "CAD":
              treeType = DECIDUOUS;
              return treeType;

            case "PER":
                treeType = EVERGREEN;
                return treeType;

            default:
                return treeType;
        }
    }
}