package hva.core;

import java.io.Serial;
import java.util.Arrays;

/**
 * Enumerates the types of employees that can work for a Vet Hotel.
 */
enum EmployeeType {
  VETERINARIAN ("VET"),
  ZOOKEEPER ("ZKP");

  @Serial
  private static final long serialVersionUID = 202410242359L;

  private final String _string;
  
  /**
   * Creates a new EmployeeType.
   * 
   * @param EmployeeType the employee type String representation.
   */
  EmployeeType(String EmployeeType) {
    this._string = EmployeeType;
  }

  /**
   * Gets the employee type in Portuguese.
   * 
   * @return the employee type in Portuguese
   */
  @Override
  public String toString() {
    return _string;
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
