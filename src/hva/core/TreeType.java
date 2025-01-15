package hva.core;

import java.io.Serial;
import java.util.Arrays;

/**
 * Enumeration of the possible types of trees.
 */
enum TreeType {
  DECIDUOUS("DECIDUOUS"),
  EVERGREEN("EVERGREEN");

  @Serial
  private static final long serialVersionUID = 202410242355L;

  private final String _string;

  /**
   * Creates a new TreeType.
   * 
   * @param TreeType the tree type String representation.
   */
  TreeType(String TreeType) {
    this._string = TreeType;
  }

  /**
   * String representation of the tree type in Portuguese.
   * 
   * @return the tree type in Portuguese
   */
  @Override
  public String toString() {
    return _string;
  }

  /**
   * Converts a string to an TreeType.
   * 
   * @param type the string to convert in Portuguese
   * @return the TreeType corresponding to the string
   */
  static TreeType stringToEnum(String type) {
    return Arrays.stream(TreeType.values())
    .filter(treeType -> treeType.toString().equals(type))
    .findFirst()
    .orElse(null);
  }
}
