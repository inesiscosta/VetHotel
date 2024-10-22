package hva.core;

import java.util.ArrayList;
import java.util.List;

import hva.core.satisfaction.AnimalSatisfaction;

/**
 * Represents an Animal in a Vet Hotel.
 */
public class Animal extends NamedEntity {
  private List<HealthStatus> _healthHistory;
  private Species _species;
  private Habitat _habitat;
  private AnimalSatisfaction _satisfactionMethod;

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
    _habitat = habitat;
    _healthHistory = new ArrayList<>();
    /* Adds itself to the TreeSet of all Animals of the same species that 
    the class Species holds.*/
    _species.addAnimal(this);
    /*Adds itself to the TreeMap of all Animals in the habitat that
    the class Habitat holds.*/
    _habitat.addAnimal(this);
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
    for (HealthStatus healthStatus : _healthHistory)
      result.append(healthStatus.toString()).append(",");
    result.setLength(result.length() - 1); //Removes the last comma
    return result.toString();
  }

  /**
   * Calculates the animal's satisfaction level based on the number of animals
   * of the same species in the habitat, the number of animals in the habitat,
   * the area of the habitat, and the influence of the habitat on the animal's
   * species.
   * 
   * @return the animal's satisfaction level
   */
  public double calculateSatisfaction() {
    return _satisfactionMethod.calculateSatisfaction(this);
  }

  /**
   * Updates the animal's health history after vaccination
   * with the effect of the vaccine.
   *
   * @param vaccineEffect the health status to add to the history
   */
  void updateHealthHistory(HealthStatus vaccineEffect) {
    _healthHistory.add(vaccineEffect);
  }

  /**
   * Changes the animal to a different habitat. 
   * If the animal already is in the destination habitat it doesnt 
   * change anything.
   * 
   * @param newHabitat the new habitat to move the animal to
   */
  public void changeHabitat(Habitat newHabitat) { 
    if(_habitat.equals(newHabitat))
      return;
    _habitat.removeAnimal(this);
    newHabitat.addAnimal(this);
    _habitat = newHabitat;
  }

  /**
   * Sets the method used to calculate the satisfaction of the animal.
   * 
   * @param satisfactionMethod the new method to use for the calculation
   */
  void setSatisfactionMethod(AnimalSatisfaction satisfactionMethod) {
    _satisfactionMethod = satisfactionMethod;
  }
}
