package hva.core;

/**
 * Enumeration of the possible states of the leaves on a tree.
 */
public enum Leaf {
  WITH_LEAVES("COMFOLHAS"),
  WITHOUT_LEAVES("SEMFOLHAS"),
  GENERATING_LEAVES("GERARFOLHAS"),
  SHEDDING_LEAVES("LARGARFOLHAS");

  private final String _pt;

  /**
   * Creates a new Leaf.
   * 
   * @param pt the leaf type String representation in Portuguese
   */
  Leaf (String pt) {
      this._pt = pt;
  }

  /**
   * Gets the leaf in Portuguese.
   * 
   * @return the leaf in Portuguese
   */
  public String pt() {
      return _pt;
  }
}