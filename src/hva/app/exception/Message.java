package hva.app.exception;

public interface Message {
  static String problemOpeningFile(Exception cause) {
    return "Problema ao abrir ficheiro: " + cause.getMessage();
  }
  
  static String unknownAnimalKey(String key) {
    return "O animal '" + key + "' não existe.";
  }
  
  static String duplicateAnimalKey(String key) {
    return "O animal '" + key + "' já existe.";
  }
  
  static String unknownSpeciesKey(String key) {
    return "A espécie '" + key + "' não existe.";
  }
  
  static String unknownEmployeeKey(String key) {
    return "O funcionário '" + key + "' não existe.";
  }

  static String unknownVeterinarianKey(String key) {
    return "O veterinário '" + key + "' não existe.";
  }

  static String duplicateEmployeeKey(String key) {
    return "O funcionário '" + key + "' já existe.";
  }
  
  static String unknownHabitatKey(String key) {
    return "O habitat '" + key + "' não existe.";
  }
  
  static String duplicateHabitatKey(String key) {
    return "O habitat '" + key + "' já existe.";
  }
  
  static String unknownTreeKey(String key) {
    return "A árvore '" + key + "' não existe.";
  }
  
  static String duplicateTreeKey(String key) {
    return "A árvore '" + key + "' já existe.";
  }
  
  static String unknownVaccineKey(String key) {
    return "A vacina '" + key + "' não existe.";
  }
  
  static String duplicateVaccineKey(String key) {
    return "A vacina '" + key + "' já existe.";
  }
  
  static String notAuthorized(String vetKey, String speciesKey) {
    return "O veterinário '" + vetKey + "' não pode ministrar vacinas à espécie '" + speciesKey + "'";
  }

  static String noResponsibility(String employeeKey, String responsibilityKey) {
    return "Responsabilidade (habitat ou espécie) '" + responsibilityKey +
      "' não atribuída ao funcionário '" + employeeKey + "'.";
  }
}
