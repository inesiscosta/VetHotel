package hva.core.season;

import hva.core.Leaf;

/**
 * Implements the Season Fall with the respective effects in the trees.
 */
public class Fall implements SeasonState {
  private final int _id = 2;

  /**
   * Advances the Season State to the next season.
   * 
   * @return the next season
   */
  @Override
  public SeasonState nextSeason() {
    return new Winter();
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
    return Season.Fall;
  }

  /**
   * Gets the biocycle for the deciduous tree
   * 
   * @return biocycle for the Deciduous tree in Fall
   */
  public Leaf getBioCycleForDeciduous() {
    return Leaf.SHEDDING_LEAVES;
  }

  /**
   * Gets the cleaning effort of a Deciduous tree 
   * 
   * @return seasonal effort for the Deciduous tree in Fall
   */
  public int seasonalEffortForDeciduous() {
    return 5;
  }

  /**
   * Gets the biocycle for the Evergreen tree
   * 
   * @return biocycle for the Evergreen tree in Fall
   */
  public Leaf getBioCycleForEvergreen() {
    return Leaf.WITH_LEAVES;
  }

  /**
   * Gets the cleaning effort of a Evergreen tree 
   * 
   * @return seasonal effort for the Evergreen tree in Fall
   */
  public int seasonalEffortForEvergreen() {
    return 1;
  }
}
