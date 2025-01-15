package hva.app.exception;

public interface Message {
  static String problemOpeningFile(Exception cause) {
    return "Problem Opening File: " + cause.getMessage();
  }
  
  static String unknownAnimalKey(String key) {
    return "The Animal'" + key + "' doesn't exist.";
  }
  
  static String duplicateAnimalKey(String key) {
    return "The Animal'" + key + "' already exists.";
  }
  
  static String unknownSpeciesKey(String key) {
    return "The Species '" + key + "' doesn't exist.";
  }
  
  static String unknownEmployeeKey(String key) {
    return "The Employee'" + key + "' doesn't exist.";
  }

  static String unknownVeterinarianKey(String key) {
    return "The Veterinarian '" + key + "' doesn't exist.";
  }

  static String duplicateEmployeeKey(String key) {
    return "The Employee'" + key + "' already exists.";
  }
  
  static String unknownHabitatKey(String key) {
    return "The Habitat '" + key + "' doesn't exist.";
  }
  
  static String duplicateHabitatKey(String key) {
    return "The Habitat '" + key + "' already exists.";
  }
  
  static String unknownTreeKey(String key) {
    return "The Tree '" + key + "' doesn't exist.";
  }
  
  static String duplicateTreeKey(String key) {
    return "The Tree '" + key + "' already exists.";
  }
  
  static String unknownVaccineKey(String key) {
    return "Vaccine '" + key + "' doesn't exist.";
  }
  
  static String duplicateVaccineKey(String key) {
    return "Vaccine '" + key + "' already exists.";
  }
  
  static String notAuthorized(String vetKey, String speciesKey) {
    return "The Veterinarian '" + vetKey + "' can't administer Vaccines to the Species'" + speciesKey + "'";
  }

  static String noResponsibility(String employeeKey, String responsibilityKey) {
    return "Responsibility (Habitat or Species) '" + responsibilityKey +
      "' not attributed to Employee '" + employeeKey + "'.";
  }
}
