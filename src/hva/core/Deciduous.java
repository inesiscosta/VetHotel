package hva.core;

import hva.core.exception.InvalidSeasonException;
import hva.core.season.Season;

import java.io.Serial;

/**
 * Represents a Deciduous Tree planted in a habitat of a Vet Hotel.
 */
class Deciduous extends Tree {

  @Serial
  private static final long serialVersionUID = 202410242343L;

  /**
   * Creates a new Deciduous Tree.
   * 
   * @param idTree the tree's unique identifier
   * @param name the tree's name
   * @param age the tree's age
   * @param baseCleaningDifficulty the tree's base cleaning difficulty
   * (used to calculate how much effort is required to clean the tree)
   * @param currentSeason the current season in the Hotel the tree belongs to
   */
  Deciduous(String idTree, String name, int age,
    int baseCleaningDifficulty, Season currentSeason) {
    super(idTree, name, age, baseCleaningDifficulty,
    TreeType.DECIDUOUS, currentSeason);
  }

  /**
   * Returns the seasonal effort required to maintain / clean a Deciduous tree.
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
    return currentSeason.getState().seasonalEffortForDeciduous();
  }

  /**
   * Returns a Deciduous tree's bio-cycle (the state of the tree's leaves)
   * according to the current season.
   * 
   * @param currentSeason the current season in the Hotel
   * @return the bio-cycle of the tree according to the current season
   * @throws InvalidSeasonException if the current season is not a valid
   * season (not one of the four seasons Spring, Summer, Fall, Winter)
   * this exception should never happen
   */
  @Override
  Leaf getBioCycle(Season currentSeason) throws InvalidSeasonException {
    return currentSeason.getState().getBioCycleForDeciduous();
  }
}
