package hva.core;
import hva.core.exception.InvalidSeasonException;

/**
 * Represents a Deciduous Tree planted in a habitat of a Vet Hotel.
 */
public class Deciduous extends Tree {
  /**
   * Creates a new Deciduous Tree.
   * @param idTree the tree's unique identifier
   * @param name the tree's name
   * @param age the tree's age
   * @param baseCleaningDifficulty the tree's base cleaning difficulty (used to calculate how much effort is required by the employee to clean the tree)
   * @param currentSeason the current season in the Hotel the tree belongs to
   */
  public Deciduous(String idTree, String name, int age, int baseCleaningDifficulty, Season currentSeason){
    super(idTree, name, age, baseCleaningDifficulty, TreeType.DECIDUOUS, currentSeason);
  }

  /**
   * Returns the seasonal effort required to maintain / clean a Deciduous tree.
   * 
   * @param currentSeason the current season in the Hotel
   * @return the seasonal effort required to maintain the tree which varies according to the season
   * @throws InvalidSeasonException if the current season is not a valid season (not one of the four seasons Spring, Summer, Fall, Winter)
   */
  @Override
  int seasonalEffort(Season currentSeason) throws InvalidSeasonException{
    switch (currentSeason) {
      case Spring:
        return 1;
      case Summer:
        return 2;
      case Fall:
        return 5;
      case Winter:
        return 0;
      default:
        throw new InvalidSeasonException(currentSeason);
    }
  }

  /**
   * Returns a Deciduous tree's bio-cycle (the state of the tree's leaves) according to the current season.
   * 
   * @param currentSeason the current season in the Hotel
   * @return the bio-cycle of the tree according to the current season
   * @throws InvalidSeasonException if the current season is not a valid season (not one of the four seasons Spring, Summer, Fall, Winter)
   */
  @Override
  Leaf getBioCycle(Season currentSeason) throws InvalidSeasonException{
    switch (currentSeason) {
      case Spring:
        return Leaf.GENERATING_LEAVES;
      case Summer:
        return Leaf.WITH_LEAVES;
      case Fall:
        return Leaf.SHEDDING_LEAVES;
      case Winter:
        return Leaf.WITHOUT_LEAVES;
      default:
        throw new InvalidSeasonException(currentSeason);
    }
  }
}
