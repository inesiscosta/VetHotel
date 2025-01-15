package hva.app.vaccine;

public interface Prompt {
  static String vaccineKey() {
    return "Vaccine ID: ";
  }

  static String vaccineName() {
    return "Vaccine Name: ";
  }

  static String veterinarianKey() {
    return "ID of the Veterinarian administering the Vaccine: ";
  }

  static String listOfSpeciesKeys() {
    return "IDs of the Species that can take the Vaccine: ";
  }
}
