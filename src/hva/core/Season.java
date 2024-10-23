package hva.core;

/**
 * Enumerates the seasons of the year.
 * The order of the seasons is Spring, Summer, Fall, Winter.
 */
//TODO: Subclasses no ENUM. MAKE THIS A STATE
 public enum Season {
  Spring(0),
  Summer(1),
  Fall(2),
  Winter(3);

  private final int _id;

  /**
   * Creates a new Season.
   * 
   * @param id the Season numerical id
  */
  Season(int id) {
      this._id = id;
  }

  /**
   * Gets the Season id.
   * 
   * @return the Season numerical id
   */
  public int id() {
      return _id;
  }

  /**
   * Returns the next season in the sequence of seasons.
   * 
   * @return the next season
   */
  public Season nextSeason() {
    Season[] seasonsArray = Season.values();
    return seasonsArray[(this.ordinal() + 1) % seasonsArray.length];
  }
}
