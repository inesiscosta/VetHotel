package hva.app.vaccine;

public interface Message {
  static String wrongVaccine(String vaccineKey, String animalKey) {
    return "A vacina '" + vaccineKey + "' não é apropiada para o animal '" + animalKey + "'.";
  }
}
