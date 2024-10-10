package hva.core;

/**
 * Enumeration of the possible types of trees.
 */
public enum TreeType {
    DECIDUOUS("CAD"),
    EVERGREEN("PER");

    private final String _pt;

    /**
     * Creates a new TreeType.
     * 
     * @param pt the tree type String representation in Portuguese
     */
    TreeType(String pt) {
        this._pt = pt;
    }

    /**
     * Gets the tree type in Portuguese.
     * 
     * @return the tree type in Portuguese
     */
    public String pt() {
        return _pt;
    }

    /**
     * Converts a string to an TreeType.
     * 
     * @param pt the string to convert in Portuguese
     * @return the TreeType corresponding to the string
     */
    public static TreeType stringToEnum(String type) {
        for (TreeType treeType : TreeType.values()) {
            if (treeType.pt().equals(type))
                return treeType;
        } 
        return null;
    }
}