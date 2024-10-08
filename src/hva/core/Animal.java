package hva.core;

/**
 * Represents an Animal in a Vet Hotel.
 */
public class Animal extends NamedEntity {
  private String _healthHistory;
  private Species _species;
  private Habitat _habitat;

  /**
   * Creates a new Animal.
   *
   * @param id the animal's unique identifier
   * @param name the animal's name
   * @param species the species the animal belongs to
   * @param habitat the habitat the animal is in
   */
  public Animal(String id, String name, Species species, Habitat habitat) {
    super(id, name);
    _species = species;
    _habitat = habitat;
    _healthHistory = "VOID";
    /* Adds itself to the TreeSet of all Animals of the same Species that 
    the Class Species holds.*/
    _species.addAnimal(this);
    /*Adds itself to the TreeMap of all Animals in the Habitat that
    the Class Habitat holds.*/
    _habitat.addAnimal(this);
  }

  /**
   * Gets the species the animal belongs to.
   *
   * @return the species the animal belongs to
   */
  Species species() {
    return _species;
  }

  /**
   * Gets the habitat the animal is in.
   *
   * @return the habitat the animal is in
   */
  Habitat habitat() {
    return _habitat;
  }

  /**
   * Gets the Animal object representation as a string containing information
   * that describes the animal.
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
      .append(_healthHistory).append("|")
      .append(_habitat.id());
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
  public int calculateSatisfaction() {
    return 20 
      + 3 * _habitat.getNumAnimalSameSpecies(_species) 
      - 2 * (_habitat.getNumAnimals() - _habitat.getNumAnimalSameSpecies(_species)
      + (_habitat.area() / _habitat.getNumAnimals()) 
      + _habitat.identifyInfluence(_species));
  }

  /**
   * Updates the animal's health history after vaccination
   * with the effect of the vaccine.
   *
   * @param vaccineEffect the health status to add to the history
   */
  void updateHealthHistory(HealthStatus vaccineEffect) {
    if(_healthHistory == "VOID") {
      _healthHistory = vaccineEffect.toString();
      return;
    }
    _healthHistory += "," + vaccineEffect;
  }

  /**
   * Changes the animal to a different habitat.
   *
   * @param newHabitat the new habitat to move the animal to
   * @throws IllegalStateException if the habitat doesn't exist??? MIGUEL CHECK THIS 
   */
  public void changeHabitat(Habitat newHabitat) throws IllegalStateException { 
    _habitat.removeAnimal(this);
    newHabitat.addAnimal(this);
    _habitat = newHabitat;
  }
}
