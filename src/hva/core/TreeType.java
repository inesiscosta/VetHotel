package hva.core;

/**
 * Enumeration of the possible types of trees.
 */
public enum TreeType {
    DECIDUOUS("CAD"),
    EVERGREEN("PER");

    private final String type;

    TreeType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public static TreeType stringToEnum(String type) {
        for (TreeType treeType : TreeType.values()) {
            if (treeType.type().equals(type)) {
                return treeType;
            }
        } 
        return null;
    }
}