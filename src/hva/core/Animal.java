package hva.core;
import hva.core.satisfaction.AnimalSatisfaction;

import java.util.Map;
import java.util.HashMap;

/**
 * Represents an Animal in a Vet Hotel.
 */
public class Animal extends NamedEntity {
  private Habitat _habitat;
  private Map<Integer, HealthStatus> _healthHistory;
  private int _healthStatusId;
  private AnimalSatisfaction _satisfactionMethod;
  private Species _species;

  /**
   * Creates a new Animal.
   * 
   * @param id the animal's unique identifier
   * @param name the animal's name
   * @param species the species the animal belongs to
   * @param habitat the habitat the animal is in
   */
  Animal(String id, String name, Species species, Habitat habitat) {
    super(id, name);
    _species = species;
    /* Adds itself to the TreeSet of all Animals of the same species that
    the class Species holds.*/
    _species.addAnimal(this);
    _habitat = habitat;
    /*Adds itself to the TreeMap of all Animals in the habitat that
    the class Habitat holds.*/
    _habitat.addAnimal(this);
    _healthHistory = new HashMap<>();
    _healthStatusId = 0;
    _satisfactionMethod = new CalculateAnimalSatisfaction();
  }

  /**
   * Gets the species the animal belongs to.
   *
   * @return the Species object the animal belongs to
   */
  Species species() {
    return _species;
  }

  /**
   * Gets the habitat the animal is in.
   *
   * @return the Habitat object the animal is in
   */
  Habitat habitat() {
    return _habitat;
  }

  /**
   * Gets the Animal object representation as a string. 
   * Contains information that describes the animal.
   *
   * @return the Animal object string representation
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("ANIMAL|")
      .append(this.id()).append("|")  
      .append(this.name()).append("|")
      .append(_species.id()).append("|")
      .append(healthHistoryToString())
      .append("|").append(_habitat.id());
    return result.toString();
  }

  private String healthHistoryToString() {
    if (_healthHistory.isEmpty())
      return "VOID";
    StringBuilder result = new StringBuilder();
    for (Map.Entry<Integer, HealthStatus> entry : _healthHistory.entrySet())
      result.append(entry.getValue().toString()).append(",");
    result.setLength(result.length() - 1); // Removes the last comma
    return result.toString();
  }

  /**
   * Sets the method used to calculate the satisfaction of the animal.
   * 
   * @param satisfactionMethod the new method to use for the calculation
   */
  void setSatisfactionMethod(AnimalSatisfaction satisfactionMethod) {
    _satisfactionMethod = satisfactionMethod;
  }

  /**
   * Calculates the animal's satisfaction level based on the number of animals
   * of the same species in the habitat, the number of animals in the habitat,
   * the area of the habitat, and the influence of the habitat on the animal's
   * species.
   * 
   * @return the animal's satisfaction level
   */
  double calculateSatisfaction() {
    return _satisfactionMethod.calculateSatisfaction(this);
  }

  /**
   * Updates the animal's health history after vaccination
   * with the effect of the vaccine.
   *
   * @param vaccineEffect the health status to add to the history
   */
  void updateHealthHistory(HealthStatus vaccineEffect) {
    _healthHistory.put(_healthStatusId++,vaccineEffect);
  }

  /**
   * Changes the animal to a different habitat. 
   * If the animal already is in the destination habitat it doesnt 
   * change anything.
   * 
   * @param newHabitat the new habitat to move the animal to
   */
  void changeHabitat(Habitat newHabitat) { 
    if(_habitat.equals(newHabitat))
      return;
    _habitat.removeAnimal(this);
    newHabitat.addAnimal(this);
    _habitat = newHabitat;
  }
}
