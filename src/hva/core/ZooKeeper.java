package hva.core;
import java.util.Collection;
import java.util.HashSet;

import hva.core.exception.UnknowIdException;

/**
 * Represents a ZooKeeper that works in a Vet Hotel.
 */
public class ZooKeeper extends Employee {
  private Collection<Habitat> _assignedHabitats;

  /**
   * Creates a new ZooKeeper.
   *
   * @param idEmployee the zookeeper's unique identifier
   * @param name the zookeeper's name
   */
  public ZooKeeper(String idEmployee, String name) {
    super(idEmployee, name, EmployeeType.ZOOKEEPER);
    _assignedHabitats = new HashSet<Habitat>();
  }

  /**
   * Calculates the ZooKeeper's satisfaction level which depends on the work effort of the assigned habitats and the number of other keepers in the habitat.
   * 
   * @return the ZooKeeper's satisfaction level
   */
  @Override
  double calculateSatisfaction() {
    double work = 0;
    for (Habitat habitat : _assignedHabitats)
      work += (workEffort(habitat) / habitat.getNumKeepers()); // Check if the getNumKeepers should count with the Keeper itself
    return 300 - work;
  }

  /**
   * Calculates the effort it requires to clean a habitat.
   * 
   * @param habitat the habitat for which to calculate the effort to clean / mantain it
   * @return the effort it takes to clean the habitat
   */
  private double workEffort(Habitat habitat) {
    return habitat.area() 
    + 3 * habitat.getNumAnimals() 
    + habitat.cleaningEffort(hotel().currentSeason());
  }

  /**
   * Adds a new habitat to the list of habitats the zookeeper is responsible for.
   * 
   * @param id the habitat's unique identifier
   * @throws UnknowIdException if the habitat's identifier is unknown
   */
  @Override
  void addResponsibility(String id) throws UnknowIdException {
    _assignedHabitats.add(this.hotel().identifyHabitat(id));
  }

  /**
   * Removes a habitat from the list of habitats the zookeeper is responsible for.
   * 
   * @param id the habitat's unique identifier
   * @throws UnknowIdException if the habitat's identifier is unknown
   */
  @Override
  void removeResponsibility(String id) throws UnknowIdException {
    _assignedHabitats.remove(this.hotel().identifyHabitat(id));
  }

  /**
   * Gets the ids of the habitats the zookeeper is responsible for. Used for the String representation of the ZooKeeper object.
   * 
   * @return a string with the ids of the habitats the zookeeper is responsible for
   */
  @Override
  String getIdResponsibilities() {
    StringBuilder idResponsibilities = new StringBuilder();
    for (Habitat habitat : _assignedHabitats)
      idResponsibilities.append(habitat.id());
    return idResponsibilities.toString();
  }
}
