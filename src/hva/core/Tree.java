package hva.core;
import static java.lang.Math.log;

/**
 * Represents a generic tree in the Vet Hotel.
 */
public abstract class Tree extends NamedEntity {
  private int _age;
  private int _baseCleaningDifficulty;
  private final TreeType _treeType;
  private final Season _creationSeason;

  /**
   * Creates a new Tree.
   * @param id the tree's unique identifier
   * @param name the tree's name
   * @param age the tree's age
   * @param baseCleaningDifficulty the tree's base cleaning difficulty
   * @param treeType the tree's type (Deciduous or Evergreen)
   * @param currentSeason the current season in the Vet Hotel the tree is in
   */
  public Tree(String id, String name, int age, int baseCleaningDifficulty,
  TreeType treeType , Season currentSeason) {
    super(id, name);
    _age = age;
    _baseCleaningDifficulty = baseCleaningDifficulty;
    _treeType = treeType;
    _creationSeason = currentSeason;
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
   * Gets the tree's base cleaning difficulty.
   * 
   * @return the tree's base cleaning difficulty
   */
  int baseCleaningDifficulty() {
    return _baseCleaningDifficulty;
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
   * Gets the season in which the tree was created.
   * Used to increment the tree's age.
   * 
   * @return the season in which the tree was created
   */
  Season seasonAtCreation() {
    return _creationSeason;
  }

  /**
   * Returns the tree's object representation as a string containing 
   * information that describes said tree.
   * 
   * @param currentSeason the current season in the Vet Hotel
   * @return the string representation of the tree object
   */
  public String toString(Season currentSeason) {
    StringBuilder result = new StringBuilder();
    result.append("ÁRVORE | ")
      .append(id()).append(" | ")
      .append(name()).append(" | ")
      .append(age()).append(" | ")
      .append(baseCleaningDifficulty()).append(" | ")
      .append(treeType()).append(" | ")
      .append(getBioCycle(currentSeason));
    return result.toString();
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
   * Checks if the tree was created in the current season.
   * 
   * @param currentSeason the current season in the Vet Hotel
   * @return true if the tree was created in the current season, false otherwise
   */
  boolean equalsCreationSeason(Season currentSeason) {
    return _creationSeason == currentSeason;
  }

  /**
   * Increments the tree's age by one. Only used when the season changes
   * and it matches the tree's season at creation.
   */
  void incrementAge() {
    _age++;
  }
}
