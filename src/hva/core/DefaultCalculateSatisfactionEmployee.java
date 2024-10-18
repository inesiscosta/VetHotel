package hva.core;

import hva.core.satisfactionStrategy.Satisfaction;

/**
 * Default methods used to calculate the satisfaction level of the diferent
 * types of employees.
 * 
 */
public class DefaultCalculateSatisfactionEmployee implements Satisfaction{
    
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

  /**
   * Calculates the effort it requires to clean / mantain a habitat.
   * 
   * @param habitat the habitat for which to calculate the effort to clean it
   * @return the effort it takes to clean the habitat
   */
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
    for (Species species : vet.getKnowSpecies())
      work += (species.getNumAnimals()) / species.getNumQualifiedVets();
    return 300 - work;
  }
}
