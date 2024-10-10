package hva.core;

/**
 * Enumerates the types of employees that can work for a Vet Hotel.
 */
public enum EmployeeType {
  VETERINARIAN ("VET"),
  ZOOKEEPER ("TRT");

  private final String _pt;

  EmployeeType(String pt) {
    this._pt = pt;
  }

  public String pt() {
    return _pt;
  }

  public static EmployeeType stringToEnum(String pt) {
    for (EmployeeType employeeType : EmployeeType.values()) {
      if (employeeType.pt().equals(pt)) {
        return employeeType;
      }
    }
    return null;
  }
}
