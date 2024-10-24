package hva.core;

import hva.core.caseInsensitiveOrder.CaseInsensitiveHashMap;
import java.util.Map;

/**
 * Represents a species of animals found in a Vet Hotel.
 */
class Species extends NamedEntity {
  private Map<String, Animal> _animals;
  private Map<String, Veterinarian> _qualifiedVets;
    
  /**
   * Creates a new Species.
   *
   * @param id the species' unique identifier
   * @param name the species' name
   */
  Species(String id, String name) {
    super(id, name);
    _animals = new CaseInsensitiveHashMap<>();
    _qualifiedVets = new CaseInsensitiveHashMap<>();
  }

  /**
   * Gets the number of animals of the species.
   *
   * @return the total number of animals of the species
   */
  int getNumAnimals() {
    return _animals.size();
  }

  /**
   * Adds an animal to the collection of animals of the species.
   *
   * @param animal the animal to add to the collection
   */
  void addAnimal(Animal animal) {
    _animals.put(animal.id(), animal);
  }

  /**
   * Gets the number of veterinarians qualified to treat the species.
   * Used to calculate vet satisfaction.
   *
   * @return the number of veterinarians qualified to treat the species
   */
  int getNumQualifiedVets() {
    return _qualifiedVets.size();
  }

  /**
   * Adds a veterinarian to the collection of veterinarians
   * qualified to treat the species.
   *
   * @param vet the veterinarian to add as a qualified vet
   */
  void addQualifiedVet(Veterinarian vet) {
    _qualifiedVets.put(vet.id(), vet);
  }

  /**
   * Removes a veterinarian from the collection of veterinarians
   * qualified to treat the species.
   * 
   * @param vet the veterinarian to remove as a qualified vet
   */
  void removeQualifiedVet(Veterinarian vet) {
    _qualifiedVets.remove(vet.id());
  }
}
