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

  Leaf (String pt) {
      this._pt = pt;
  }

  public String pt() {
      return _pt;
  }
}
