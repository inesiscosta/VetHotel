package hva.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Represents a species of animals found in a Vet Hotel.
 */
public class Species extends NamedEntity {
  private Collection<Animal> _animals;
  private Collection<Veterinarian> _qualifiedVets;
    
  /**
   * Creates a new Species.
   *
   * @param id the species' unique identifier
   * @param name the species' name
   */
  public Species(String id, String name) {
    super(id, name);
    _animals = new TreeSet<Animal>();
    _qualifiedVets = new HashSet<Veterinarian>();
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
   * Gets the number of veterinarians qualified to treat the species.
   * Used to calculate vet satisfaction.
   *
   * @return the number of veterinarians qualified to treat the species
   */
  int getNumQualifiedVets() {
    return _qualifiedVets.size();
  }

  /**
   * Adds an animal to the collection of animals of the species.
   *
   * @param animal the animal to add to the collection
   */
  void addAnimal(Animal animal) {
    _animals.add(animal);
  }

  /**
   * Adds a veterinarian to the collection of veterinarians
   * qualified to treat the species.
   *
   * @param vet the veterinarian to add as a qualified vet
   */
  void addQualifiedVet(Veterinarian vet) {
    _qualifiedVets.add(vet);
  }

  /**
   * Removes a veterinarian from the collection of veterinarians
   * qualified to treat the species.
   * 
   * @param vet the veterinarian to remove as a qualified vet
   */
  void removeQualifiedVet(Veterinarian vet) {
    _qualifiedVets.remove(vet);
  }
}
