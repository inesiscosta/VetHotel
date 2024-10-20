package hva.core;

import hva.core.caseInsensitiveOrder.CaseInsensitiveOrderComparator;
import hva.core.exception.DuplicateTreeIdException;
import hva.core.exception.InvalidTreeTypeException;
import hva.core.exception.UnknownAnimalIdException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Represents a habitat in a Vet Hotel.
 */
public class Habitat extends NamedEntity {
  private int _area;
  private Map<String,Animal> _animals;
  private Collection<ZooKeeper> _assignedKeepers;
  private Collection<Tree> _trees;
  private Map<Species, Integer> _influences;
  
  /**
   * Creates a new Habitat.
   *
   * @param id the habitat's unique identifier
   * @param name the habitat's name
   * @param area the habitat's area
   */
  public Habitat(String id, String name, int area) {
    super(id, name);
    _area = area;
    _animals = new TreeMap<String,Animal>(new CaseInsensitiveOrderComparator()); //String is the id of the Animal.
    _assignedKeepers = new TreeSet<ZooKeeper>();
    _trees = new TreeSet<Tree>();
    _influences = new HashMap<>();
  }

  /**
   * Gets the habitat's area.
   *
   * @return the habitat's area
   */
  int area() {
    return _area;
  }

  /**
   * Gets the number of animals in the habitat.
   *
   * @return the number of animals in the habitat
   */
  int getNumAnimals() {
    return _animals.size();
  }

  /**
   * Gets the number of keepers assigned to the habitat.
   *
   * @return the number of keepers assigned to the habitat
   */
  int getNumKeepers() {
    return _assignedKeepers.size();
  }

  /**
   * Gets the Habitat object representation as a string containing
   * information that describes the habitat.
   *
   * @param currentSeason the current season in the Vet Hotel
   * @return the Habitat object string representation
   */
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("HABITAT|")
      .append(this.id()).append("|")
      .append(this.name()).append("|")
      .append(this.area()).append("|")
      .append(_trees.size());  
    return result.toString();
  }

  /**
   * Gets an Animal object if the animal is in the habitat from it's id.
   * 
   * @param id the animal's unique identifier
   * @return the Animal object if it's in the habitat, null otherwise
   */
  Animal identifyAnimal(String id) throws UnknownAnimalIdException {
    if (_animals.containsKey(id))
      return _animals.get(id);
    throw new UnknownAnimalIdException(id);
  }
  
  /**
   * Checks if the animal id already exists in the habitat
   *  
   * @param id the id of the animal
   * @return true if the id already exists false if not
   */
  boolean containsAnimal(String id) {
    return _animals.containsKey(id);
  }

  /**
   * Gets the influence of the habitat on a species. 
   * Used to calculate an animal's satisfaction.
   *
   * @param species the species to get the influence of the habitat on
   * @return the influence of the habitat on the species
   */
  int identifyInfluence(Species species) {
    //Returns 0 if the species isn't in the map, indicating neutral influence.
    return _influences.getOrDefault(species, 0);
  }

  /**
   * Adds an animal to the habitat.
   * 
   * @param animal the animal to be added to the habitat
   */
  void addAnimal(Animal animal) {
    _animals.put(animal.id(), animal);
  }

  /**
   * Removes an animal from the habitat.
   * 
   * @param animal the animal to be removed from the habitat
   */
  void removeAnimal(Animal animal) {
    _animals.remove(animal.id());
  }

  /**
   * Adds a new keeper to the list of keepers assigned to the habitat.
   * 
   * @param keeper the keeper to be added to the habitat
   */
  void addKeeper(ZooKeeper keeper) {
    _assignedKeepers.add(keeper);
  }
  
  /**
   * Removes a keeper from the list of keepers assigned to the habitat.
   * 
   * @param keeper the keeper to be removed from the habitat
   */
  void removeKeeper(ZooKeeper keeper) {
    _assignedKeepers.remove(keeper);
  }

  /**
   * Adds the influence value a habitat has on a given species.
   * 
   * @param species the species influenced (POS, NEG, NEU) by the habitat
   * @param influence the influence value the habitat has on the species
   */
  void addInfluence(Species species, int influence) {
    _influences.put(species, influence);
  }

  /**
   * Changes the influence value a habitat has on a given species.
   * 
   * @param species the species influenced (POS, NEG, NEU) by the habitat
   * @param newInfluence the new influence value the habitat has on the
   * species
   */
  void changeHabitatInflunece(Species species, int newInfluence) {
    _influences.put(species, newInfluence);
  }

  /**
   * Changes the area of the habitat.
   * 
   * @param newArea the new area of the habitat
   */
  public void changeArea(int newArea) {
    _area = newArea;
  }

  /**
   * Adds a new tree to the habitat.
   * 
   * @param id the tree's unique identifier
   * @param name the tree's name
   * @param age the tree's age
   * @param baseCleaningDifficulty the tree's base cleaning difficulty
   * (used to calculate how much effort is required by to clean the tree)
   * @param treeType the type of tree (Evergreen or Deciduous)
   * @param currentSeason the current season in the Vet Hotel
   * @throws InvalidTypeException if the tree type is not a valid type
   * (not one of the two types Evergreen or Deciduous)
   */
  public Tree plantTree(String id, String name, int age,
  int baseCleaningDifficulty, String treeType, Season currentSeason,
  Hotel hotel) throws DuplicateTreeIdException, InvalidTreeTypeException {
    Tree tree;
    if(hotel.treeAlreadyExists(id))
      throw new DuplicateTreeIdException(id);
    switch (TreeType.stringToEnum(treeType)) {
      case EVERGREEN:
        tree = new Evergreen(id, name, age, baseCleaningDifficulty,
        currentSeason);
        break;
      case DECIDUOUS:
        tree = new Deciduous(id, name, age, baseCleaningDifficulty,
        currentSeason);
        break;
      default:
        throw new InvalidTreeTypeException(treeType);
    }
    _trees.add(tree);
    return tree;
  }

  /**
   * Gets the tree if it exists in the habitat
   * 
   * @param id the id of the tree
   * @return the tree object if it is found and null if not
   */
  Tree identifyTree(String id) {
    for(Tree tree : _trees) {
      if(tree.id().equalsIgnoreCase(id)) //TODO I dont like this it should use methods from NamedEntity chekc this Inês?
        return tree;
    }
    return null;
  }

  /**
   * Advances the season of all trees in the habitat. 
   */
  void nextSeason(Season currentSeason) {
    for(Tree tree : _trees) {
      if (tree.equalsCreationSeason(currentSeason))
        tree.incrementAge();
    }
  }

  /**
   * Lists all animals in the habitat. 
   * 
   * @return an unmodifiable list containing the Animal object string
   * representation of all animals in the habitat
   */
  public List<Animal> listAnimals() {
    return Collections.unmodifiableList(new ArrayList<>(_animals.values()));
  }

  /**
   * Lists all trees in the habitat in a string
   * 
   * containing information about each tree.
   * @param currentSeason the current season in the Vet Hotel
   * @return a String containing the Tree object string representation
   * of all trees in the habitat
   */
  List<String> listTrees(Season currentSeason) { 
    List<String> allTreesString = new ArrayList<>();
    for(Tree tree : _trees) {
      allTreesString.add(tree.toString(currentSeason));
    }
    return Collections.unmodifiableList(allTreesString);
  }

  /**
   * Calculates the total effort required to clean all trees in the habitat.
   * 
   * @param currentSeason the current season in the Vet Hotel
   * @return the total effort required to clean all trees in the habitat
   */
  double cleaningEffort(Season currentSeason) {
    double cleaningEffort = 0;
    for(Tree tree : _trees)
      cleaningEffort += tree.calculateCleaningEffort(currentSeason);
    return cleaningEffort;
  }

  /**
   * Calculates the satisfaction of all animals in the habitat.
   * 
   * @return the sum of the satisfaction of each animals in the habitat
   */
  int calculateAnimalsSatisfaction() {
    int satisfaction = 0;
    for (Animal animal : _animals.values())
      satisfaction += animal.calculateSatisfaction();
    return satisfaction;
  }

  /**
   * Counts the number of animals of a given species in a habitat.
   * 
   * @param species the species to count the number of animals of
   * @return the total number of animals of the given species in the habitat
   */
  int getNumAnimalsSameSpecies(Species species) {
    int numAnimalsSameSpecies = 0;
    for(Animal animal : _animals.values()) {
      if(animal.species().equals(species))  
        numAnimalsSameSpecies++;
    }
    return numAnimalsSameSpecies;
  }
}
