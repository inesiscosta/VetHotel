package hva.core;

import hva.core.exception.UnknownIdException;
import hva.core.exception.UnknownResponsibilityException;
import hva.core.exception.UnknownResponsibilityException;

/**
 * Represents an employee working in a Vet Hotel.
 */
public abstract class Employee extends NamedEntity {
  private final EmployeeType _employeeType;
  private Hotel _hotel;

  /**
   * Creates a new Employee.
   *
   * @param id the employee's unique identifier
   * @param name the employee's name
   * @param employeeType the type of employee (Zookeeper or Veterinarian)
   */
  public Employee(String id, String name, EmployeeType employeeType) {
    super(id, name);
    _employeeType = employeeType;
  }

  /**
   * Gets the employee's specialization (Zookeeper or Veterinarian).
   * 
   * @return the employee type
   */
  EmployeeType type() {
    return _employeeType;
  }

  /**
   * Gets the hotel the employee works in.
   * 
   * @return the hotel the employee works in
   */
  public Hotel hotel() {
    return _hotel;
  }

  /**
   * Gets the Employee object representation as a string containing 
   * information that describes said employee.
   *  
   * @return the Employee object string representation
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append(EmployeeType.enumToString(this.type()))
      .append("|")
      .append(this.id())
      .append("|")
      .append(this.name());
    String responsibilities = this.getIdResponsibilities();
    if (responsibilities != null)
      result.append("|").append(responsibilities);
    return result.toString();
  }

  /**
   * Calculates the employee's satisfaction level. 
   * Calculations differ based on the type of employee.
   * 
   * @return the employee's satisfaction level
   */
  public abstract double calculateSatisfaction();

  /**
   * Adds a responsibility to the employee. 
   * Responsibility type differs based on the type of employee.
   * 
   * @param id the unique identifier of the responsibility to be added
   * @throws UnknownIdException if the id cannot be used to identify a responsibility
   */
  abstract void addResponsibility(String id) throws UnknownResponsibilityException;
  
  /**
   * Removes a responsibility from the employee. 
   * Responsibility type differs based on the type of employee.
   * 
   * @param id the unique identifier of the responsibility to be removed
   * @throws UnknownIdException if the id cannot be used to identify a responsibility
   */
  abstract void removeResponsibility(String id) throws UnknownResponsibilityException;
  
  /**
   * Gets the unique identifiers of the employee's responsibilities
   * in a String representation.
   * 
   * @return the unique identifiers of the employee's responsibilities
   */
  abstract String getIdResponsibilities();
}
