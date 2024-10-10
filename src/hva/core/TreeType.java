package hva.core;

/**
 * Enumeration of the possible types of trees.
 */
public enum TreeType {
    DECIDUOUS("CAD"),
    EVERGREEN("PER");

    private final String _pt;

    TreeType(String pt) {
        this._pt = pt;
    }

    public String pt() {
        return _pt;
    }

    public static TreeType stringToEnum(String type) {
        for (TreeType treeType : TreeType.values()) {
            if (treeType.pt().equals(type)) {
                return treeType;
            }
        } 
        return null;
    }
}