package hva.core;

import hva.core.satisfaction.VeterinarianSatisfaction;
import hva.core.satisfaction.ZooKeeperSatisfaction;

/**
 * Default methods used to calculate the satisfaction level of the diferent
 * types of employees.
 * 
 */
class CalculateEmployeeSatisfaction implements VeterinarianSatisfaction, ZooKeeperSatisfaction {
    
  /**
   * Calculates the ZooKeeper's satisfaction level which depends on the 
   * work effort of the assigned habitats and
   * the number of other keepers in the habitat.
   * 
   * @return the ZooKeeper's satisfaction level
   */
  @Override
  public double calculateSatisfaction(ZooKeeper keeper) {
    double work = 0;
    for (Habitat habitat : keeper.getAssingHabitats())
      work += (workEffort(habitat, keeper) / habitat.getNumKeepers());
    return 300 - work;
  }

  private double workEffort(Habitat habitat, ZooKeeper keeper) {
    return habitat.area() 
    + 3 * habitat.getNumAnimals() 
    + habitat.cleaningEffort(keeper.hotel().currentSeason());
  }

  /**
   * Calculates the Vet's satisfaction level which depends on the number of
   * animals it knows how to vaccinate and the number of other veterinarians
   * that know how to vaccinate the same species of animals.
   * 
   * @return the Vet's satisfaction level
   */
  @Override
  public double calculateSatisfaction(Veterinarian vet) {
    int work = 0;
    for (Species species : vet.getKnownSpecies())
      work += (species.getNumAnimals()) / species.getNumQualifiedVets();
    return 20 - work;
  }
}
