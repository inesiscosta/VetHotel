package hva.app.animal;

public interface Prompt {
  static String animalKey() {
    return "Identificador único do animal: ";
  }

  static String animalName() {
    return "Nome do animal: ";
  }
  
  static String speciesKey() {
    return "Identificador único da espécie: ";
  }
  
  static String speciesKeys() {
    return "Lista de identificadores de espécies: ";
  }

  static String speciesName() {
    return "Nome da espécie: ";
  }
}
