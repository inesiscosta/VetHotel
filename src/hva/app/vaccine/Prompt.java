package hva.app.vaccine;

public interface Prompt {
  static String vaccineKey() {
    return "Identificador da vacina: ";
  }

  static String vaccineName() {
    return "Nome da vacina: ";
  }

  static String veterinarianKey() {
    return "Identficador do veterinário a ministrar a vacina: ";
  }

  static String listOfSpeciesKeys() {
    return "Identificadores das espécies que podem receber a vacina: ";
  }
}
