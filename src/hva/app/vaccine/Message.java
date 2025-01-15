package hva.app.vaccine;

public interface Message {
  static String wrongVaccine(String vaccineKey, String animalKey) {
    return "Vaccine '" + vaccineKey + "' is not adapted to Animal'" + animalKey + "'.";
  }
}
