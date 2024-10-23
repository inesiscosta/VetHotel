package hva.core;

import hva.core.exception.UnknownHabitatIdException;
import hva.core.exception.UnknownResponsibilityIdException;
import hva.core.satisfaction.ZooKeeperSatisfaction;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents a ZooKeeper that works in a Vet Hotel.
 */
public class ZooKeeper extends Employee {
  private Map<String, Habitat> _assignedHabitats;
  private ZooKeeperSatisfaction _satisfactionMethod;

  /**
   * Creates a new ZooKeeper.
   *
   * @param idEmployee the zookeeper's unique identifier
   * @param name the zookeeper's name
   */
  ZooKeeper(String idEmployee, String name, Hotel hotel) {
    super(idEmployee, name, EmployeeType.ZOOKEEPER, hotel);
    _assignedHabitats = new HashMap<>();
    _satisfactionMethod = new CalculateEmployeeSatisfaction();
  }

  /**
   * Calculates the ZooKeeper's satisfaction level which depends on the 
   * work effort of the assigned habitats and
   * the number of other keepers in the habitat.
   * 
   * @return the ZooKeeper's satisfaction level
   */
  @Override
  double calculateSatisfaction() {
    return _satisfactionMethod.calculateSatisfaction(this);
  }

  /**
   * Returns the assing habitats collection it is used in the strategy pattern
   * for calculating the keeper satisfaction.
   * 
   * @return the collection of assign habitats
   */
  Collection<Habitat> getAssingHabitats() {
    return _assignedHabitats.values();
  } 

  /**
   * Adds a habitat to the list of habitats the zookeeper is responsible for.
   * 
   * @param id the habitat's unique identifier
   * @throws UnknownResponsibilityException if the habitat's ud is unknown
   */
  @Override
  void addResponsibility(String id) throws UnknownResponsibilityIdException {
    try {
      _assignedHabitats.put(id,this.hotel().identifyHabitat(id));
      this.hotel().identifyHabitat(id).addKeeper(this);
    } catch (UnknownHabitatIdException e) {
      throw new UnknownResponsibilityIdException(id,e);
    }
  }

  /**
   * Removes a habitat from the list of habitats the keeper is responsible for.
   * 
   * @param id the habitat's unique identifier
   * @throws UnknownResponsabilityException if the habitat's id is unknown
   */
  @Override
  void removeResponsibility(String id) throws UnknownResponsibilityIdException {
    try {
      _assignedHabitats.remove(id);
    this.hotel().identifyHabitat(id).removeKeeper(this);
    } catch (UnknownHabitatIdException e) {
      throw new UnknownResponsibilityIdException(id,e);
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
    return _assignedHabitats.keySet().stream()
    .sorted()
    .reduce((id1, id2) -> id1 + "," + id2)
    .orElse(null);
  }

  /**
   * Sets the method used to calculate the satisfaction of the ZooKeeper.
   * 
   * @param satisfactionMethod the new method to use for the calculation
   */
  void setSatisfactionMethod(ZooKeeperSatisfaction satisfactionMethod) {
    _satisfactionMethod = satisfactionMethod;
  }
}
