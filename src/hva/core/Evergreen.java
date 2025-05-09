package hva.core;

import hva.core.exception.InvalidSeasonException;
import hva.core.season.Season;
import java.io.Serial;

/**
 * Represents an Evergreen Tree planted in a habitat of a Vet Hotel.
 */
class Evergreen extends Tree {

  @Serial
  private static final long serialVersionUID = 202410242345L;

  /**
   * Creates a new Evergreen Tree.
   * 
   * @param idTree the tree's unique identifier
   * @param name the tree's name
   * @param age the tree's age
   * @param baseCleaningDifficulty the tree's base cleaning difficulty
   * (used to calculate how much effort is required by to clean the tree)
   * @param currentSeason the current season in the Hotel the tree belongs to
   */
  Evergreen(String idTree, String name, int age,
  int baseCleaningDifficulty, Season currentSeason) {
    super(idTree, name, age, baseCleaningDifficulty,
    TreeType.EVERGREEN, currentSeason);
  }

  /**
   * Returns the seasonal effort required to maintain/clean an Evergreen tree.
   * 
   * @param currentSeason the current season in the Hotel
   * @return the seasonal effort required to maintain the tree
   * this varies according to the season
   * @throws InvalidSeasonException if the current season is not a valid season
   * (not one of the four seasons Spring, Summer, Fall, Winter)
   * this exception should never happen
   */
  @Override
  int seasonalEffort(Season currentSeason) throws InvalidSeasonException {
    return currentSeason.getState().seasonalEffortForEvergreen();
  }

  /**
   * Returns an Evergreen tree's bio-cycle (the state of the tree's leaves)
   * according to the current season.
   * 
   * @param currentSeason the current season in the Hotel
   * @return the bio-cycle of the tree according to the current season
   * @throws InvalidSeasonException if the current season is not a valid season
   * (not one of the four seasons Spring, Summer, Fall, Winter)
   * this exception should never happen
   */
  @Override
  Leaf getBioCycle(Season currentSeason) throws InvalidSeasonException {
    return currentSeason.getState().getBioCycleForEvergreen();
  }
}
