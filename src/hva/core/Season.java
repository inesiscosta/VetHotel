package hva.core;

/**
 * Enumerates the seasons of the year.
 * The order of the seasons is Spring, Summer, Fall, Winter.
 */
public enum Season {
  Spring,
  Summer,
  Fall,
  Winter;

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
