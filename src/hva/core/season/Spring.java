package hva.core.season;

import hva.core.Leaf;

/**
 * Implements the Season Spring with the respective effects in the trees.
 */
public class Spring implements SeasonState{
  private final int _id = 0;

  /**
   * Advances the Season State to the next season.
   * 
   * @return the next season
   */
  @Override
  public SeasonState nextSeason() {
    return new Summer();
  }

  /**
   * Gets the id of the current 
   * 
   * @return the id of the current Season
   */
  @Override
  public int id() {
    return _id;
  }

  /**
   * Gets the Enum of the current season
   * 
   * @return the enum correspondent to the current season
   */
  @Override
  public Season getEnum() {
    return Season.Spring;
  }

  /**
   * Gets the biocycle for the deciduous tree
   * 
   * @return biocycle for the Deciduous tree in Spring
   */
  public Leaf getBioCycleForDeciduous() {
    return Leaf.GENERATING_LEAVES;
  }

  /**
   * Gets the cleaning effort of a Deciduous tree 
   * 
   * @return seasonal effort for the Deciduous tree in Spring
   */
  public int seasonalEffortForDeciduous() {
    return 1;
  }

  /**
   * Gets the biocycle for the Evergreen tree
   * 
   * @return biocycle for the Evergreen tree in Spring
   */
  public Leaf getBioCycleForEvergreen() {
    return Leaf.GENERATING_LEAVES;
  }

  /**
   * Gets the cleaning effort of a Evergreen tree 
   * 
   * @return seasonal effort for the Evergreen tree in Spring
   */
  public int seasonalEffortForEvergreen() {
    return 1;
  }
}
