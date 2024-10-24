package hva.core;

import hva.core.observers.TreeObserver;
import hva.core.season.Season;
import static java.lang.Math.log;

/**
 * Represents a generic tree in the Vet Hotel.
 */
abstract class Tree extends NamedEntity implements TreeObserver {
  private int _age;
  private int _baseCleaningDifficulty;
  private final TreeType _treeType;
  private final Season _creationSeason;
  private Season _currentSeason;

  /**
   * Creates a new Tree.
   * 
   * @param id the tree's unique identifier
   * @param name the tree's name
   * @param age the tree's age
   * @param baseCleaningDifficulty the tree's base cleaning difficulty
   * @param treeType the tree's type (Deciduous or Evergreen)
   * @param currentSeason the current season in the Vet Hotel the tree is in
   */
  Tree(String id, String name, int age, int baseCleaningDifficulty,
  TreeType treeType , Season currentSeason) {
    super(id, name);
    _age = age;
    _baseCleaningDifficulty = baseCleaningDifficulty;
    _treeType = treeType;
    _creationSeason = currentSeason;
    _currentSeason = currentSeason;
  }

  /**
   * Gets the tree's type (Deciduous or Evergreen).
   * 
   * @return the tree's type
   */
  TreeType treeType() {
    return _treeType;
  }

  /**
   * Gets the tree's age.
   * 
   * @return the tree's age
   */
  int age() {
    return _age;
  }

  /**
   * Checks if the tree as aged one year
   * (it happens when the tree experiences the four seasons of the year)
   */
  @Override
  public void updateAge() {
    if (equalsCreationSeason(_currentSeason))
    _age++;
  }

  /**
   * Advances the tree season, when the observer is notified
   * 
   * @param currentSeason the new season
   */
  @Override
  public void advanceSeason(Season currentSeason) {
    _currentSeason = currentSeason;
  }

  /**
   * Gets the tree's current season that is
   * synced with the hotel
   * 
   * @return the tree's current season
   */
  Season currentSeason() {
    return _currentSeason;
  }

  /**
   * Gets the season in which the tree was created.
   * Used to increment the tree's age.
   * 
   * @return the season in which the tree was created
   */
  Season seasonAtCreation() {
    return _creationSeason;
  }

  private boolean equalsCreationSeason(Season currentSeason) {
    return _creationSeason == currentSeason;
  }

  /**
   * Calculates the effort required to clean the tree.
   * Used to determine the satisfaction level of the ZooKeepers.
   * 
   * @param currentSeason the current season in the Vet Hotel
   * @return the effort required to clean the tree
   */
  double calculateCleaningEffort(Season currentSeason) {
    return _baseCleaningDifficulty * seasonalEffort(currentSeason)
    * log(age() + 1);
  }

  /**
   * Gets the tree's base cleaning difficulty.
   * 
   * @return the tree's base cleaning difficulty
   */
  int baseCleaningDifficulty() {
    return _baseCleaningDifficulty;
  }

  /**
   * Returns the seasonal effort required to maintain /
   * clean a tree which varies with the season.
   * 
   * @param currentSeason the current season in the Vet Hotel
   * @return the seasonal effort required to maintain the tree
   */
  abstract int seasonalEffort(Season currentSeason);

  /**
   * Returns the tree's bio-cycle (the state of the tree's leaves)
   * according to the current season.
   * 
   * @param currentSeason the current season in the Vet Hotel
   * @return the bio-cycle of the tree according to the current season
   */
  abstract Leaf getBioCycle(Season currentSeason);

  /**
   * Returns the tree's object representation as a string containing 
   * information that describes said tree.
   * 
   * @return the string representation of the tree object
   */
  public String toString() {
    // ÁRVORE|id|name|age|baseCleaningDifficulty|type|bioCycle
    StringBuilder result = new StringBuilder();
    return result.append("ÁRVORE|")
    .append(id()).append("|")
    .append(name()).append("|")
    .append(age()).append("|")
    .append(baseCleaningDifficulty()).append("|")
    .append(treeType()).append("|")
    .append(getBioCycle(currentSeason()).toString())
    .toString();
  }
}
