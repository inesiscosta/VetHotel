package hva.app.animal;

public interface Prompt {
  static String animalKey() {
    return "Animal ID: ";
  }

  static String animalName() {
    return "Animal Name: ";
  }
  
  static String speciesKey() {
    return "Species ID: ";
  }
  
  static String speciesKeys() {
    return "List of Species IDs: ";
  }

  static String speciesName() {
    return "Name Species: ";
  }
}
