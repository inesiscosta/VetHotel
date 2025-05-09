package hva.core;

import hva.core.exception.EmployeeNotResponsibleException;
import hva.core.exception.UnknownResponsibilityIdException;
import hva.core.exception.UnknownSpeciesIdException;
import hva.core.satisfaction.VeterinarianSatisfaction;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * Represents a veterinarian that works in a Vet Hotel.
 */
public class Veterinarian extends Employee {

  @Serial
  private static final long serialVersionUID = 202410242353L;

  private Collection<Species> _knowsHowToVaccinate;
  private VeterinarianSatisfaction _satisfactionMethod;

  /**
   * Creates a new Veterinarian.
   *
   * @param idEmployee the veterinarian's unique identifier
   * @param name the veterinarian's name
   */
  Veterinarian(String idEmployee, String name, Hotel hotel) {
    super(idEmployee, name, EmployeeType.VETERINARIAN, hotel);
    _knowsHowToVaccinate = new HashSet<>();
    _satisfactionMethod = new CalculateEmployeeSatisfaction();
  }

  /**
   * Returns the assing habitats collection it is used in the strategy pattern
   * for calculating the keeper satisfaction.
   * 
   * @return the collection of assign habitats
   */
  Collection<Species> getKnownSpecies() {
    return Collections.unmodifiableCollection(_knowsHowToVaccinate);
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
      throw new UnknownResponsibilityIdException(id, this.id(), e);
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
  void removeResponsibility(String id) throws EmployeeNotResponsibleException, UnknownResponsibilityIdException {
    try {
      Species species = this.hotel().identifySpecies(id);
      if (!_knowsHowToVaccinate.contains(species))
        throw new EmployeeNotResponsibleException(species.id(), this.id());
      _knowsHowToVaccinate.remove(species);
      species.removeQualifiedVet(this);
    } catch (UnknownSpeciesIdException e) {
      throw new UnknownResponsibilityIdException(id, this.id(), e);
    }
  }

  /**
   * Gets a collection of the veterinarian's responsibilities.
   * 
   * @return the veterinarian's responsibilities
   */
  @Override
  Collection<NamedEntity> responsibilities() {
    return Collections.unmodifiableCollection(_knowsHowToVaccinate);
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
      throw new EmployeeNotResponsibleException(animal.species().id(), this.id());
    animal.updateHealthHistory(vaccine.determineVaccineEffect(animal));
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
