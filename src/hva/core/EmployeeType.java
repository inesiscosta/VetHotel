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
}
