package hva.core;

import hva.core.exception.UnknownHabitatIdException;
import hva.core.exception.UnknownResponsibilityException;

import java.util.Collection;
import java.util.HashSet;

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
  public ZooKeeper(String idEmployee, String name, Hotel hotel) {
    super(idEmployee, name, EmployeeType.ZOOKEEPER, hotel);
    _assignedHabitats = new HashSet<Habitat>();
  }

  /**
   * Calculates the ZooKeeper's satisfaction level which depends on the 
   * work effort of the assigned habitats and
   * the number of other keepers in the habitat.
   * 
   * @return the ZooKeeper's satisfaction level
   */
  @Override
  public double calculateSatisfaction() {
    double work = 0;
    for (Habitat habitat : _assignedHabitats)
      work += (workEffort(habitat) / habitat.getNumKeepers());
    return 300 - work;
  }

  /**
   * Calculates the effort it requires to clean / mantain a habitat.
   * 
   * @param habitat the habitat for which to calculate the effort to clean it
   * @return the effort it takes to clean the habitat
   */
  private double workEffort(Habitat habitat) {
    return habitat.area() 
    + 3 * habitat.getNumAnimals() 
    + habitat.cleaningEffort(hotel().currentSeason());
  }

  /**
   * Adds a habitat to the list of habitats the zookeeper is responsible for.
   * 
   * @param id the habitat's unique identifier
   * @throws UnknownResponsibilityException if the habitat's ud is unknown
   */
  @Override
  void addResponsibility(String id) throws UnknownResponsibilityException {
    try {
      _assignedHabitats.add(this.hotel().identifyHabitat(id));
      this.hotel().identifyHabitat(id).addKeeper(this);
    } catch (UnknownHabitatIdException e) {
      throw new UnknownResponsibilityException(id,e);
    }

  }

  /**
   * Removes a habitat from the list of habitats the keeper is responsible for.
   * 
   * @param id the habitat's unique identifier
   * @throws UnknownResponsabilityException if the habitat's id is unknown
   */
  @Override
  void removeResponsibility(String id) throws UnknownResponsibilityException {
    try {
      _assignedHabitats.remove(this.hotel().identifyHabitat(id));
    this.hotel().identifyHabitat(id).removeKeeper(this);
    } catch (UnknownHabitatIdException e) {
      throw new UnknownResponsibilityException(id,e);
    }
    
  }

  /**
   * Gets the ids of the habitats the zookeeper is responsible for.
   * Used for the String representation of the ZooKeeper object.
   * 
   * @return a string with the ids of the habitats
   */
  @Override
  String getIdResponsibilities() {
    if(_assignedHabitats.isEmpty())
      return null;
    StringBuilder idResponsibilities = new StringBuilder();
    for (Habitat habitat : _assignedHabitats)
      idResponsibilities.append(habitat.id()).append(",");
    idResponsibilities.setLength(idResponsibilities.length()-1);
    return idResponsibilities.toString();
  }
}
