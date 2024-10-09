package hva.core;

/**
 * Enumerates the types of employees that can work for a Vet Hotel.
 */
public enum EmployeeType {
  VETERINARIAN,
  ZOOKEEPER;

  /**
     * A method for returning the employee type representation used by the app layer
     *
     * @param type The enum of EmployeeType
     * @return String that correspond to the internal employee type representation
     */
    public static String enumToString(EmployeeType type) {  //FIXME Ines temos de ver ser fazemos isto ou other way maybe override valueOf? 
      String employeeType = null;
      switch (type) {
          case VETERINARIAN:
            employeeType = "VET";
            return employeeType;

          case ZOOKEEPER:
              employeeType = "TRT";
              return employeeType;

          default:
              return employeeType;
      }
  }

}
