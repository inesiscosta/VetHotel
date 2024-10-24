package hva.core;

import java.io.Serial;

/**
 * Enumeration of the possible states of the leaves on a tree.
 */
public enum Leaf {
  WITH_LEAVES("COMFOLHAS"),
  WITHOUT_LEAVES("SEMFOLHAS"),
  GENERATING_LEAVES("GERARFOLHAS"),
  SHEDDING_LEAVES("LARGARFOLHAS");

  @Serial
  private static final long serialVersionUID = 202410242356L;

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
   * String representation of the state of the leaves in Portuguese.
   * 
   * @return the leaf in Portuguese
   */
  @Override
  public String toString() {
    return _pt;
  }
}
