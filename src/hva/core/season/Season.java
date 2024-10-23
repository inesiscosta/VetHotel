package hva.core.season;

/**
 * Enumerates the seasons of the year.
 * The order of the seasons is Spring, Summer, Fall, Winter.
 */
 public enum Season {
  Spring(new Spring()),
  Summer(new Summer()),
  Fall(new Fall()),
  Winter(new Winter());

  private final SeasonState _state;

  /**
   * Creates a new Season.
   * 
   * @param id the Season numerical id
  */
  Season(SeasonState season) {
    this._state = season;
  }

  /**
   * Gets the Season id.
   * 
   * @return the Season numerical id
   */
  public int id() {
    return this._state.id();
  }

  /**
   * Gets the SeasonState associated to each enum.
   * 
   * @return the Seasson state that is associated to each enum.
   */
  public SeasonState getState() {
    return _state;
  }

  /**
   * Returns the next season in the sequence of seasons.
   * 
   * @return the next season
   */
  public Season nextSeason() {
    return _state.nextSeason().getEnum();
  }
}
