package hva.core;

import java.util.Collection;
import java.util.HashSet;

import hva.core.exception.*;

/**
 * Represents a veterinarian that works in a Vet Hotel.
 */
public class Veterinarian extends Employee {
  private Collection<Species> _knowsHowToVaccinate;

  /**
   * Creates a new Veterinarian.
   *
   * @param idEmployee the veterinarian's unique identifier
   * @param name the veterinarian's name
   */
  public Veterinarian(String idEmployee, String name) {
    super(idEmployee, name, EmployeeType.VETERINARIAN);
    _knowsHowToVaccinate = new HashSet<Species>();
  }
    
  /**
   * Calculates the Vet's satisfaction level which depends on the number of
   * animals it knows how to vaccinate and the number of other veterinarians
   * that know how to vaccinate the same species of animals.
   * 
   * @return the Vet's satisfaction level
   */
  @Override
  public double calculateSatisfaction() {
    int work = 0;
    for (Species species : _knowsHowToVaccinate)
      work += (species.getNumAnimals()) / species.getNumQualifiedVets();
    return 300 - work;
  }

  /**
   * Adds a new species to the list of species the veterinarian
   * knows how to vaccinate.
   * 
   * @param id the species' unique identifier
   * @throws UnknowIdException if the species' identifier is unknown
   */
  @Override
  void addResponsibility(String id) throws UnknownIdException {
    _knowsHowToVaccinate.add(this.hotel().identifySpecies(id));
    this.hotel().identifySpecies(id).addQualifiedVet(this);
  }

  /**
   * Removes a species from the list of species the veterinarian
   * knows how to vaccinate.
   * 
   * @param id the species' unique identifier
   * @throws UnknowIdException if the species' identifier is unknown
   */
  @Override
  void removeResponsibility(String id) throws UnknownIdException {
    _knowsHowToVaccinate.remove(this.hotel().identifySpecies(id));
    this.hotel().identifySpecies(id).removeQualifiedVet(this);
  }

  /**
   * Gets the ids of the species the veterinarian knows how to vaccinate.
   * Used for the String representation of the Veterinarian object.
   * 
   * @return a string with the ids of the species the veterinarian
   * knows how to vaccinate
   */
  @Override
  String getIdResponsibilities() {
    if(_knowsHowToVaccinate.isEmpty())
      return null;
    StringBuilder idResponsibilities = new StringBuilder();
    for (Species species : _knowsHowToVaccinate)
      idResponsibilities.append(species.id()).append(",");
    idResponsibilities.setLength(idResponsibilities.length()-1);
    return idResponsibilities.toString();
  }

  /**
   * Vaccinates a given animal with a given vaccine.
   * 
   * @param vaccine the vaccine to be applied
   * @param animal the animal to be vaccinated
   * @return a new VaccinationRecord object to be added to the list of
   * vaccination records stored in the hotel
   */
  VaccinationRecord vaccinate(Vaccine vaccine, Animal animal) {
    HealthStatus animalHealthStatus = vaccine.determineVaccineEffect(animal);
    animal.updateHealthHistory(animalHealthStatus);
    vaccine.incrementNumApplications();
    return new VaccinationRecord(vaccine, this, animal);
  }
}
