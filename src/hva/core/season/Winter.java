package hva.core.season;

import hva.core.Leaf;

import java.io.Serial;

/**
 * Implements the Season Winter with the respective effects in the trees.
 */
public class Winter implements SeasonState {

  @Serial
  private static final long serialVersionUID = 202410250007L;

  private final int _id = 3;

  /**
   * Advances the Season State to the next season.
   * 
   * @return the next season
   */
  @Override
  public SeasonState nextSeason() {
    return new Spring();
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
    return Season.Winter;
  }

  /**
   * Gets the biocycle for the deciduous tree
   * 
   * @return biocycle for the Deciduous tree in Winter
   */
  public Leaf getBioCycleForDeciduous() {
    return Leaf.WITHOUT_LEAVES;
  }

  /**
   * Gets the cleaning effort of a Deciduoud tree 
   * 
   * @return seasonal effort for the Deciduous tree in Winter
   */
  public int seasonalEffortForDeciduous() {
    return 0;
  }

  /**
   * Gets the biocycle for the Evergreen tree
   * 
   * @return biocycle for the Evergreen tree in Winter
   */
  public Leaf getBioCycleForEvergreen() {
    return Leaf.SHEDDING_LEAVES;
  }

  /**
   * Gets the cleaning effort of a Evergreen tree 
   * 
   * @return seasonal effort for the Evergreeen tree in Winter
   */
  public int seasonalEffortForEvergreen() {
    return 2;
  }
}
