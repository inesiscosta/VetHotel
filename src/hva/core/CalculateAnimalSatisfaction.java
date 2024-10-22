package hva.core;

import hva.core.satisfaction.AnimalSatisfaction;

/**
 * Calculates the satisfaction level of an animal in a habitat.
 */
public class CalculateAnimalSatisfaction implements AnimalSatisfaction {

  /**
   * Calculates the animal's satisfaction level based on the number of animals
   * of the same species in the habitat, the number of animals in the habitat,
   * the area of the habitat, and the influence of the habitat on the animal's
   * species.
   * 
   * @return the animal's satisfaction level
   */
  public double calculateSatisfaction(Animal animal) {
    Species species = animal.species();
    Habitat habitat = animal.habitat();
    return 20 + (3 * (habitat.getNumAnimalsSameSpecies(species)-1))
    - (2 * (habitat.getNumAnimals() - habitat.getNumAnimalsSameSpecies(species)))
    + (habitat.area() / habitat.getNumAnimals()) + habitat.identifyInfluence(species);
  } 
}
