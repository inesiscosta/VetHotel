package hva.core;

import hva.core.exception.EmployeeNotResponsibleException;
import hva.core.exception.UnknownResponsibilityIdException;
import hva.core.exception.UnknownSpeciesIdException;
import hva.core.satisfaction.VeterinarianSatisfaction;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a veterinarian that works in a Vet Hotel.
 */
public class Veterinarian extends Employee {
  private Set<Species> _knowsHowToVaccinate;
  private VeterinarianSatisfaction _satisfactionMethod;

  /**
   * Creates a new Veterinarian.
   *
   * @param idEmployee the veterinarian's unique identifier
   * @param name the veterinarian's name
   */
  Veterinarian(String idEmployee, String name, Hotel hotel) {
    super(idEmployee, name, EmployeeType.VETERINARIAN, hotel);
    _knowsHowToVaccinate = new TreeSet<Species>();
    _satisfactionMethod = new CalculateEmployeeSatisfaction();
  }
    
  /**
   * Calculates the Vet's satisfaction level which depends on the number of
   * animals it knows how to vaccinate and the number of other veterinarians
   * that know how to vaccinate the same species of animals.
   * 
   * @return the Vet's satisfaction level
   */
  @Override
  double calculateSatisfaction() {
    return _satisfactionMethod.calculateSatisfaction(this);
  }

  /**
   * Returns the assing habitats collection it is used in the strategy pattern
   * for calculating the keeper satisfaction.
   * 
   * @return the collection of assign habitats
   */
  Collection<Species> getKnownSpecies() {
    return _knowsHowToVaccinate;
  } 

  /**
   * Adds a new species to the list of species the veterinarian
   * knows how to vaccinate.
   * 
   * @param id the species' unique identifier
   * @throws UnknownResponsabilityException if the species' id is unknown
   */
  @Override
  void addResponsibility(String id) throws UnknownResponsibilityIdException {
    try {
      _knowsHowToVaccinate.add(this.hotel().identifySpecies(id));
    this.hotel().identifySpecies(id).addQualifiedVet(this);
    } catch (UnknownSpeciesIdException e) {
      throw new UnknownResponsibilityIdException(id, e);
    }
  }

  /**
   * Removes a species from the list of species the veterinarian
   * knows how to vaccinate.
   * 
   * @param id the species' unique identifier
   * @throws UnknownResponsabilityException if the species' id is unknown
   */
  @Override
  void removeResponsibility(String id) throws UnknownResponsibilityIdException {
    try {
      _knowsHowToVaccinate.remove(this.hotel().identifySpecies(id));
      this.hotel().identifySpecies(id).removeQualifiedVet(this);
    } catch (UnknownSpeciesIdException e) {
      throw new UnknownResponsibilityIdException(id, e);
    }
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
  VaccinationRecord vaccinate(Vaccine vaccine, Animal animal)
  throws EmployeeNotResponsibleException {
    if(!_knowsHowToVaccinate.contains(animal.species()))
      throw new EmployeeNotResponsibleException(animal.species().id());
    HealthStatus animalHealthStatus = vaccine.determineVaccineEffect(animal);
    animal.updateHealthHistory(animalHealthStatus);
    vaccine.incrementNumApplications();
    return new VaccinationRecord(vaccine, this, animal);
  }

   /**
   * Sets the method used to calculate the satisfaction of the Veterinarian.
   * 
   * @param satisfactionMethod the new method to use for the calculation
   */
  void setSatisfactionMethod(VeterinarianSatisfaction satisfactionMethod) {
    _satisfactionMethod = satisfactionMethod;
  }
}
