package hva.core;

import hva.core.exception.UnknownResponsibilityIdException;

/**
 * Represents an employee working in a Vet Hotel.
 */
abstract class Employee extends NamedEntity {
  private final EmployeeType _employeeType;
  private Hotel _hotel;

  /**
   * Creates a new Employee.
   *
   * @param id the employee's unique identifier
   * @param name the employee's name
   * @param employeeType the type of employee (Veterinarian or Zookeeper)
   */
  Employee(String id, String name, EmployeeType employeeType, Hotel hotel) {
    super(id, name);
    _employeeType = employeeType;
    _hotel = hotel;
  }

  /**
   * Gets the employee's specialization (Veterinarian or Zookeeper).
   * 
   * @return the employee type (employee's specialization)
   */
  EmployeeType type() {
    return _employeeType;
  }

  /**
   * Gets the hotel the employee works in.
   * 
   * @return the Hotel object the employee works in
   */
  Hotel hotel() {
    return _hotel;
  }

  /**
   * Calculates the employee's satisfaction level.
   * Calculations differ based on the type of employee.
   * 
   * @return the employee's satisfaction level
   */
  abstract double calculateSatisfaction();

  /**
   * Adds a responsibility to the employee.
   * Responsibility type differs based on the type of employee.
   * 
   * @param id the unique identifier of the responsibility to be added
   * @throws UnknownIdException if the id cannot be used to
   * identify a responsibility
   */
  abstract void addResponsibility(String id)
  throws UnknownResponsibilityIdException;
  
  /**
   * Removes a responsibility from the employee.
   * Responsibility type differs based on the type of employee.
   * 
   * @param id the unique identifier of the responsibility to be removed
   * @throws UnknownIdException if the id cannot be used to identify
   * a responsibility
   */
  abstract void removeResponsibility(String id)
  throws UnknownResponsibilityIdException;
  
  /**
   * Gets the unique identifiers of the employee's responsibilities
   * in a String representation.
   * 
   * @return the unique identifiers of the employee's responsibilities
   */
  abstract String getIdResponsibilities();

  /**
   * Gets the Employee object representation as a string.
   * Contains information that describes said employee.
   * 
   * @return the Employee object string representation
   */
  @Override
  public String toString() {
    // EMPLOYEE|id|name|idResponsibility1,idResponsibility2,...
    StringBuilder result = new StringBuilder();
    result.append(this.type().toString()).append("|")
    .append(this.id()).append("|")
    .append(this.name());
    String responsibilities = this.getIdResponsibilities();
    result.append(responsibilities != null ? "|" + responsibilities : "");
    return result.toString();
  }
}
