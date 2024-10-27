package hva.core;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a record of a vaccination event.
 */
class VaccinationRecord implements Serializable {

  @Serial
  private static final long serialVersionUID = 202410242351L;

  private HealthStatus _damage;
  private Vaccine _administeredVaccine;
  private Veterinarian _administeringVet;
  private Animal _vaccinatedAnimal;
  
  /**
   * Creates a new VaccinationRecord.
   *
   * @param vaccine the vaccine administered
   * @param vet the veterinarian who administered the vaccine
   * @param animal the animal vaccinated
   */
  VaccinationRecord(Vaccine vaccine, Veterinarian vet, Animal animal) {
    _administeredVaccine = vaccine;
    _administeringVet = vet;
    _vaccinatedAnimal = animal;
    _damage = vaccine.determineVaccineEffect(animal);
  }

  /**
   * Gets the vaccine administered.
   *
   * @return the vaccine administered
   */
  Vaccine vaccine() {
    return _administeredVaccine;
  }

  /**
   * Gets the damage caused by the vaccine
   * (HEALTHY, CONFUSION, ACCIDENT ,ERROR).
   *
   * @return the damage caused by the vaccine /
   * the effect of the vaccine on the animal's health
   */
  HealthStatus damage() {
    return _damage;
  }

  /**
   * Gets the veterinarian who administered the vaccine.
   *
   * @return the veterinarian who administered the vaccine
   */
  Veterinarian vet() {
    return _administeringVet;
  }

  /**
   * Gets the animal vaccinated.
   *
   * @return the animal vaccinated
   */
  Animal animal() {
    return _vaccinatedAnimal;
  }

  /**
   * Gets the VaccinationRecord object representation as a string containing
   * information that describes the vaccination event.
   *
   * @return the VaccinationRecord object string representation
   */
  @Override
  public String toString() {
    // REGISTO-VACINA|vaccineId|vetId|animalSpeciesId
    StringBuilder result = new StringBuilder();
    return result.append("REGISTO-VACINA|")
    .append(_administeredVaccine.id()).append("|")
    .append(_administeringVet.id()).append("|")
    .append(_vaccinatedAnimal.species().id())
    .toString();
  }
}
