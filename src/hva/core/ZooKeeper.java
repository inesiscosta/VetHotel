package hva.core;

import hva.core.exception.UnknownHabitatIdException;
import hva.core.exception.UnknownResponsibilityException;
import hva.core.satisfactionStrategy.Satisfaction;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a ZooKeeper that works in a Vet Hotel.
 */
public class ZooKeeper extends Employee {
  private Set<Habitat> _assignedHabitats;
  private Satisfaction _satisfactionMethod;

  /**
   * Creates a new ZooKeeper.
   *
   * @param idEmployee the zookeeper's unique identifier
   * @param name the zookeeper's name
   */
  public ZooKeeper(String idEmployee, String name, Hotel hotel) {
    super(idEmployee, name, EmployeeType.ZOOKEEPER, hotel);
    _assignedHabitats = new TreeSet<Habitat>();
    _satisfactionMethod = new DefaultCalculateSatisfactionEmployee();
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
    return _satisfactionMethod.calculateSatisfaction(this);
  }

  /**
   * Returns the assing habitats collection it is used in the strategy pattern
   * for calculating the keeper satisfaction.
   * 
   * @return the collection of assign habitats
   */
  Collection<Habitat> getAssingHabitats() {
    return _assignedHabitats;
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
    if (_assignedHabitats.isEmpty())
      return null;
    StringBuilder idResponsibilities = new StringBuilder();
    for (Habitat habitat : _assignedHabitats)
      idResponsibilities.append(habitat.id()).append(",");
    idResponsibilities.setLength(idResponsibilities.length() - 1);
    return idResponsibilities.toString();
  }

  /**
   * Sets the method used to calculate the satisfaction of the ZooKeeper.
   * 
   * @param satisfactionMethod the new method to use for the calculation
   */
  void setSatisfactionMethod(Satisfaction satisfactionMethod) {
    _satisfactionMethod = satisfactionMethod;
  }
}
