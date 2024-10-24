package hva.core;

import java.io.Serial;
import java.util.Arrays;

/**
 * Enumerates the types of employees that can work for a Vet Hotel.
 */
enum EmployeeType {
  VETERINARIAN ("VET"),
  ZOOKEEPER ("TRT");

  @Serial
  private static final long serialVersionUID = 202410242359L;

  private final String _pt;
  
  /**
   * Creates a new EmployeeType.
   * 
   * @param pt the employee type String representation in Portuguese
   */
  EmployeeType(String pt) {
    this._pt = pt;
  }

  /**
   * Gets the employee type in Portuguese.
   * 
   * @return the employee type in Portuguese
   */
  @Override
  public String toString() {
    return _pt;
  }

  /**
   * Converts a string to an EmployeeType.
   * 
   * @param pt the string to convert in Portuguese
   * @return the EmployeeType corresponding to the string
   */
  static EmployeeType stringToEnum(String pt) {
    return Arrays.stream(EmployeeType.values())
    .filter(employeeType -> employeeType.toString().equals(pt))
    .findFirst().orElse(null);
  }
}
