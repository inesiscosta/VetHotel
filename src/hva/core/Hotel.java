package hva.core;

import hva.core.exception.DuplicateAnimalIdException;
import hva.core.exception.DuplicateEmployeeIdException;
import hva.core.exception.DuplicateHabitatIdException;
import hva.core.exception.DuplicateSpeciesIdException;
import hva.core.exception.DuplicateSpeciesNameException;
import hva.core.exception.DuplicateTreeIdException;
import hva.core.exception.DuplicateVaccineIdException;
import hva.core.exception.EmployeeNotResponsibleException;
import hva.core.exception.ImportFileException;
import hva.core.exception.InvalidEmployeeTypeException;
import hva.core.exception.InvalidTreeTypeException;
import hva.core.exception.UnknownAnimalIdException;
import hva.core.exception.UnknownEmployeeIdException;
import hva.core.exception.UnknownHabitatIdException;
import hva.core.exception.UnknownIdException;
import hva.core.exception.UnknownResponsibilityIdException;
import hva.core.exception.UnknownSpeciesIdException;
import hva.core.exception.UnknownVaccineIdException;
import hva.core.exception.UnrecognizedEntryException;
import hva.core.observers.HotelObserver;
import hva.core.observers.HotelSubject;
import hva.core.season.Season;

import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a Vet Hotel.
 */
public class Hotel implements  HotelSubject {

  @Serial
  private static final long serialVersionUID = 202410242347L;
  
  private Season _currentSeason;
  private Map<String,Habitat> _habitats;
  private Collection<HotelObserver> _hotelObservers;
  private Map<String,Employee> _employees;
  private String _filename;
  private Map<String,Species> _species;
  private Map<String, Species> _speciesByName;
  private Map<String,Vaccine> _vaccines;
  private Collection<VaccinationRecord> _vaccinationRecords;
  private boolean _unsavedChanges;

  /**
   * Creates a new Vet Hotel.
   *
   */
  public Hotel() {
    _filename = null;
    _hotelObservers = new HashSet<>();
    _unsavedChanges = false;
    _currentSeason = Season.Spring; // The hotel starts in the Spring season.
    _habitats = new CaseInsensitiveHashMap<>();
    _species = new CaseInsensitiveHashMap<>();
    _speciesByName = new HashMap<>();
    _employees = new CaseInsensitiveHashMap<>();
    _vaccines = new CaseInsensitiveHashMap<>();
    /* Used array list instead of linkedlist due to less memory overhead
    since we are only adding to the end of the list O(1) complexity anyways*/
    _vaccinationRecords = new ArrayList<>();
  }

  /**
   * Gets the current season of the hotel.
   *
   * @return the current season of the hotel
   */
  public Season currentSeason() {
    return _currentSeason;
  }

  /**
   * Gets the filename associated with this hotel.
   * 
   * @return the associated filename
   */
  String getAssociatedFilename() {
    return _filename;
  }

  /**
   * Gets whether the hotel has unsaved changes or not.
   * 
   * @return the state of the bool unsavedChanges
   */ 
  public boolean getUnsavedChanges() {
    return _unsavedChanges;
  }

  /**
   * Sets the filename of the associated file.
   * 
   * @param filename the new filename for the associated file
   */
  void setAssociatedFilename(String filename) {
    _filename = filename;
  }

  /**
   * Changes the unsavedChanges state of the hotel.
   * 
   * @param state the new state for the hotel, having unsavedChanges or not
   */
  void unsavedChanges(boolean state) {
    _unsavedChanges = state;
  }

  /**
   * Adds the requested observer to the hotel list of observers.
   * 
   * @param observer the observer to be added to the list of hotel observers
   */
  @Override
  public void addHotelObserver(HotelObserver observer) {
    _hotelObservers.add(observer);
  }

  /**
   * Removes the requested observer from the hotel list of observers
   * 
   * @param observer the hotel observer to be removed from the hotel observers
   */
  @Override
  public void removeHotelObserver(HotelObserver observer) {
    _hotelObservers.remove(observer);
  }

  /**
   * Updates all the observers in the hotel list of observers
   */
  @Override
  public void notifyHotelObservers() {
    _hotelObservers.forEach(observer -> observer.update(true));
  }

  /**
   * Advances the hotel to the next season.
   */
  int nextSeason() {
    _currentSeason = _currentSeason.nextSeason();
    _habitats.values().forEach(habitat -> habitat.nextSeason(_currentSeason));
    notifyHotelObservers();
    return _currentSeason.id();
  }

  /**
   * Checks if the tree alreay exists on one of the habitats in the hotel
   * 
   * @param id the id to check 
   * @return true if it alredy exists fasle if not
  */  
  boolean treeAlreadyExists(String id) {
    return _habitats.values().stream()
    .anyMatch(habitat -> habitat.identifyTree(id) != null);
  }

  /**
   * Identifies a habitat by its id.
   * 
   * @param idHabitat the id of the habitat to identify
   * @return the habitat object with the given id
   * @throws UnknownHabitatIdException if the habitat with the given id
   * is not found
   */
  public Habitat identifyHabitat(String idHabitat)
  throws UnknownHabitatIdException {
    if(!_habitats.containsKey(idHabitat))
      throw new UnknownHabitatIdException(idHabitat);
    return _habitats.get(idHabitat);
  }

  /**
   * Identifies an animal by its id.
   * 
   * @param idAnimal the id of the animal to identify
   * @return the animal object with the given id
   * @throws UnknownAnimalIdException if the animal with the given id
   * is not found
   */
  public Animal identifyAnimal(String idAnimal)
  throws UnknownAnimalIdException {
    for(Habitat habitat : _habitats.values())
      if(habitat.containsAnimal(idAnimal))
        return habitat.identifyAnimal(idAnimal);
    throw new UnknownAnimalIdException(idAnimal);
  }

  /**
   * Identifies a species by its id.
   * 
   * @param idSpecies the id of the species to identify
   * @return the Species object with the given id
   * @throws UnknownSpeciesIdException if the species with the given id
   * is not found
   */
  public Species identifySpecies(String idSpecies)
  throws UnknownSpeciesIdException {
    if(!_species.containsKey(idSpecies))
      throw new UnknownSpeciesIdException(idSpecies);
    return _species.get(idSpecies);
  }

  /**
   * Identifies an employee by its id.
   * 
   * @param idEmployee
   * @return the employee object with the given id
   * @throws UnknownEmployeeIdException if the employee with the given id
   * is not found
   */
  public Employee identifyEmployee(String idEmployee)
  throws UnknownEmployeeIdException {
    if(!_employees.containsKey(idEmployee))
      throw new UnknownEmployeeIdException(idEmployee);
    return _employees.get(idEmployee);
  }

  /**
   * Identifies a veterinarian by its id.
   * 
   * @param idVet the id of the veterinarian to identify
   * @return the veterinarian object with the given id
   * @throws UnknownEmployeeIdException
   * if the veterinarian with the given id is not found
   */
  public Veterinarian identifyVet(String idVet)
  throws UnknownEmployeeIdException {
    Employee employee = identifyEmployee(idVet);
    if (!(employee.type() == EmployeeType.VETERINARIAN))
      throw new UnknownEmployeeIdException(idVet);
    return (Veterinarian) employee;
  }

  /**
   * Identifies a vaccine by its id.
   * 
   * @param idVaccine the id of the vaccine to identify
   * @return the vaccine object with the given id
   * @throws UnknownVaccineIdException if the vaccine with the given id
   * is not found
   */
  public Vaccine identifyVaccine(String idVaccine)
  throws UnknownVaccineIdException {
    if(!_vaccines.containsKey(idVaccine))
      throw new UnknownVaccineIdException(idVaccine);
    return _vaccines.get(idVaccine);
  }

  /**
   * Registers a new habitat in the hotel.
   * 
   * @param id the habitat's unique identifier
   * @param name the habitat's name
   * @param area the habitat's area
   * @return the new Habitat object
   * @throws DuplicateHabitatIdException if there is already a Habitat
   * with the same id
   */
  public Habitat registerHabitat(String id, String name, int area)
    throws DuplicateHabitatIdException {
    if (_habitats.putIfAbsent(id, new Habitat(id, name, area)) != null)
      throw new DuplicateHabitatIdException(id);
    notifyHotelObservers();
    return _habitats.get(id);
  }

  /**
   * Registers a new animal in the hotel.
   * 
   * @param idAnimal the animal's unique identifier
   * @param name the animal's name
   * @param idHabitat the habitat's id where the animal is
   * @param idSpecies the species' id of the animal
   * @throws UnknownHabitatIdException if the habitat with the given id
   * is not found
   * @throws DuplicateAnimalIdException if the id is already used
   * @throws UnknownSpeciesIdException if the species with the given id
   * is not found
   */
  public void registerAnimal(String idAnimal, String name, String idSpecies,
  String idHabitat) throws UnknownHabitatIdException,
  DuplicateAnimalIdException, UnknownSpeciesIdException {
    if(animalAlreadyExists(idAnimal))
      throw new DuplicateAnimalIdException(idAnimal);
    Habitat habitat = identifyHabitat(idHabitat);
    Species species = identifySpecies(idSpecies);
    new Animal(idAnimal, name, species, habitat);
    notifyHotelObservers();
  }

  private boolean animalAlreadyExists(String idAnimal) {
    return _habitats.values().stream()
    .anyMatch(habitat -> habitat.containsAnimal(idAnimal));
  }

  /**
   * Checks if a species already exists in the hotel.
   * 
   * @param idSpecies the species' id to check
   * @return true if the species already exists, false otherwise
   */
  public boolean speciesAlreadyExists(String idSpecies) {
    return _species.containsKey(idSpecies);
  }

  /**
   * Registers a new species in the hotel.
   * 
   * @param id the species' unique identifier
   * @param name the species' name
   * @throws DuplicateSpeciesIdException if the id is already used
   * @throws DuplicateSpeciesNameException if this species name is already used
   */
  public void registerSpecies(String id, String name)
  throws DuplicateSpeciesIdException, DuplicateSpeciesNameException {
    if (speciesAlreadyExists(id))
      throw new DuplicateSpeciesIdException(id);
    if (_speciesByName.containsKey(name))
      throw new DuplicateSpeciesNameException(name);
    // Only adds the species if both the id and the name are unique
    Species newSpecies = new Species(id, name);
    _species.put(id, newSpecies);
    _speciesByName.put(name, newSpecies);
    notifyHotelObservers();
  }

  /**
   * Registers a new employee in the hotel.
   * 
   * @param id the employee's unique identifier
   * @param name the employee's name
   * @param type the employee's type (VET or ZKP)
   * @throws InvalidEmployeeTypeException if the type is not valid
   * this exception should never happen
   * @throws DuplicateEmployeeIdException if an employee with the same id
   * alredy exists
   */
  public void registerEmployee(String id, String name, String type) 
  throws DuplicateEmployeeIdException, InvalidEmployeeTypeException {
    if (_employees.containsKey(id))
      throw new DuplicateEmployeeIdException(id);
    Employee employee = null;
    switch (EmployeeType.stringToEnum(type)) {  
      case VETERINARIAN:
        employee = new Veterinarian(id, name, this);
        break;
      case ZOOKEEPER:
        employee = new ZooKeeper(id, name, this);
        break;
      default:
        throw new InvalidEmployeeTypeException(type);
    }
    _employees.put(id, employee);
    notifyHotelObservers();
  }

  /**
   * Registers a new vaccine in the hotel.
   * 
   * @param idVaccine the vaccine's unique identifier
   * @param name the vaccine's name
   * @param speciesIds the species' ids that the vaccine is suitable for
   * @throws UnknownSpeciesIdException if the species with the given id
   * is not found
   * @throws DuplicateVaccineIdException if a vaccine with the same id
   * already exists
   */
  public void registerVaccine(String idVaccine, String name, String[] speciesIds)
  throws UnknownSpeciesIdException, DuplicateVaccineIdException {
    List<Species> speciesList = new ArrayList<>();
    for (String id : speciesIds)
      speciesList.add(identifySpecies(id));
    if (_vaccines.putIfAbsent(idVaccine,
    new Vaccine(idVaccine, name, speciesList)) != null)
      throw new DuplicateVaccineIdException(idVaccine);
    notifyHotelObservers();
  }

  /**
   * Adds a new vaccination record to the hotel and vaccinates the animal.
   * 
   * @param vet the veterinarian that is going to vaccinate the animal
   * @param animal the animal to vaccinate
   * @param vaccine the vaccine to apply
   */
  public boolean addVaccinationRecord(String idVaccine, String idVet,
  String idAnimal) throws EmployeeNotResponsibleException, 
  UnknownVaccineIdException, UnknownEmployeeIdException,
  UnknownAnimalIdException {
    Vaccine vaccine = identifyVaccine(idVaccine);
    Veterinarian vet = identifyVet(idVet);
    Animal animal = identifyAnimal(idAnimal);
    _vaccinationRecords.add(vet.vaccinate(identifyVaccine(idVaccine), animal));
    notifyHotelObservers();
    return vaccine.isSpeciesAppropriate(animal.species());
  }

  /**
   * 
   * @param idAnimal
   * @return
   */
  public String idSpeciesAnimal(String idAnimal) {
    try {
      Animal animal = identifyAnimal(idAnimal);
      return animal.species().id();
    } catch (UnknownAnimalIdException e) {
      /* This should never happen since we already checked
      if the habitat contains the animal*/
      return null;
    }
  }

  /**
   * Adds a responsibility to an employee.
   * 
   * @param idEmployee the employee's unique identifier
   * @param idResponsibility the responsibility's unique identifier
   * @throws UnknownEmployeeIdException if the employee with the given id is
   * not found
   * @throws UnknownResponsibilityException if responsibility with the given id
   * is not found
   */
  public void addResponsibility(String idEmployee, String idResponsibility)
  throws UnknownEmployeeIdException, UnknownResponsibilityIdException {
    identifyEmployee(idEmployee).addResponsibility(idResponsibility);
    notifyHotelObservers();
  }

  /**
   * Removes a responsibility of an employee.
   * 
   * @param idEmployee the employee's unique identifier
   * @param idResponsibility the responsibility's unique identifier
   * @throws UnknownIdException if the employee with the given id is not found
   * @throws UnknownResponsibilityException if responsibility with the given id
   * is not found
   */
  public void removeResponsibility(String idEmployee, String idResponsibility)
  throws EmployeeNotResponsibleException, UnknownEmployeeIdException,
  UnknownResponsibilityIdException {
    identifyEmployee(idEmployee).removeResponsibility(idResponsibility);
    notifyHotelObservers();
  }

  /**
   * Adds a Tree to an Habitat
   * 
   * @param idHabitat the habitat to wich to add a tree
   * @param id the id of the tree to be added
   * @param name the name of the tree to be added
   * @param age the age of the tree to be added
   * @param difficulty the cleaning difficulty of the tree to be added
   * @param type the type of the to be added
   * @return the new added if it was added without any exception
   * @throws UnknownHabitatIdException if the habitat doesnt exist
   * @throws DuplicateTreeIdException if the id of the new tree already exist
   * in one habitat
   * @throws InvalidTreeTypeException if the type of the new tree doesnt exist
   */
  public Tree addTreeToHabitat(String idHabitat, String id, String name,
  int age, int difficulty, String type) throws UnknownHabitatIdException,
  DuplicateTreeIdException, InvalidTreeTypeException {
    return identifyHabitat(idHabitat).plantTree(id, name, age, difficulty,
    type, _currentSeason, this);
    // Plant tree notifies the hotel observers and adds a new tree observer.
  }

  /**
   * Change the area of an habitat
   * 
   * @param id the habitat for the area to be changed
   * @param area the new area of the habitat
   * @throws UnknownHabitatIdException if the habitat doesnt exist in
   * the hotel 
   */
  public void changeHabitatArea(String id, int area)
  throws UnknownHabitatIdException {
    identifyHabitat(id).changeArea(area);
    notifyHotelObservers();
  }

  /**
   * Change the influence that a habitat has over a species
   * 
   * @param idHabitat the habitat to add the influence
   * @param idSpecies the species it will affected
   * @param influence the new influence the habitat will have over the
   * species in question
   * @throws UnknownHabitatIdException if the habitat doesn't exist in the hotel
   * @throws UnknownSpeciesIdException if the species doesn't exist
   */
  public void changeHabitatInfluence(String idHabitat, String idSpecies,
  String influence) throws UnknownHabitatIdException,
  UnknownSpeciesIdException {
    identifyHabitat(idHabitat).changeInfluence(identifySpecies(idSpecies),
    Influence.stringToEnum(influence));
    notifyHotelObservers();
  }

  /**
   * Transfer an aninmal from one habitat to another one in the hotel.
   * 
   * @param idAnimal the animal to transfer to another habitat
   * @param idHabitat the destination habitat
   * @throws UnknownAnimalIdException if the animal doesn't exist in any of
   * the habitats in the hotel
   * @throws UnknownHabitatIdException if the destination habitat doesnt exist
   * in the hotel
   */
  public void transferAnimalToHabitat(String idAnimal, String idHabitat)
  throws UnknownAnimalIdException, UnknownHabitatIdException {
    identifyAnimal(idAnimal).changeHabitat(identifyHabitat(idHabitat));
    notifyHotelObservers();
  }

  /**
   * Lists all habitats in the hotel in an immutable list containing 
   * information about each habitat.
   * 
   * @return an immutable List containing the Habitat object string
   * representation of all habitats in the hotel
   */
  public Collection<NamedEntity> listHabitats() {
    return Collections.unmodifiableCollection(_habitats.values());
  }

  /**
   * Lists all animals in the hotel in an immutable list containing
   * information about each animal.
   * 
   * @return an immutable Collection containing the Animal object string
   * representation of all animals in the hotel
   */
  public Collection<Animal> listAnimals() {
    return _habitats.values().stream()
    .flatMap(habitat -> habitat.listAnimals().stream())
    .collect(Collectors.toUnmodifiableList());
  }

  /**
   * Lists all employees in the hotel in an unmodifiable Collection containing
   * information about each employee.
   * 
   * @return an unmodifiable Collection containing the Employee object string
   * representation of all employees in the hotel
   */
  public Collection<Employee> listEmployees() {
    return Collections.unmodifiableCollection(_employees.values());
  }

  /**
   * List the all trees from one habitat
   * 
   * @param idHabitat the habitat to list all trees from
   * @return an unmodifiableCollection of all the trees from the habitat
   * @throws UnknownHabitatIdException if the habitat doesnt exist in this
   * hotel
   */
  public Collection<Tree> listAllTreesHabitat(String idHabitat)
  throws UnknownHabitatIdException {
    return identifyHabitat(idHabitat).listTrees();
  }

  /**
   * List all animals from one habitat
   * 
   * @param idHabitat the habitat to list all animals from
   * @return an unmodifiableCollection of all animals from the habitat
   * @throws UnknownHabitatIdException if the habitat doesn't exist in this
   * hotel
   */
  public Collection<Animal> listAnimalsInHabitat(String idHabitat)
  throws UnknownHabitatIdException {
    return identifyHabitat(idHabitat).listAnimals();
  }

  /**
   * Lists all vaccines in the hotel in an unmodifiable Collection containing
   * information about each vaccine.
   * 
   * @return an unmodifiable Collection containing the Vaccine objects of
   * all vaccines in the hotel
   */
  public Collection<Vaccine> listVaccines() {
    return Collections.unmodifiableCollection(_vaccines.values());
  }

  /**
   * Lists all vaccination records in an unmodifiable list containing
   * information about each record.
   * 
   * @return an unmodifiable List containing the VaccinationRecord objects
   */
  public Collection<VaccinationRecord> listVaccinationRecords() {
    return Collections.unmodifiableCollection(_vaccinationRecords);
  }

  /**
   * Lists all vaccination records of vaccines given to a given
   * animal in a string containing information about each record.
   * 
   * @param id the id of the animal to list the vaccination records of
   * @return an unmodifiable List containing the VaccinationRecord objects
   * of a specific animal
   */
  public Collection<VaccinationRecord> listAnimalVaccinationHistory(String id)
  throws UnknownAnimalIdException {
    Animal animal = identifyAnimal(id);
    return _vaccinationRecords.stream()
    .filter(record -> record.animal().equals(animal))
    .collect(Collectors.toUnmodifiableList());
  }

  /** 
   * Lists all vaccination records of vaccines administered by a given vet
   * in a string containing information about each record.
   * 
   * @param id the id of the veterinarian to list the vaccination records of
   * @return an unmodifiable List containing the VaccinationRecord object
   * of all vaccination records of vaccines administered by the given vet
   */
  public Collection<VaccinationRecord> listVetVaccinationRecords(String id)
  throws UnknownEmployeeIdException {
    Veterinarian vet = identifyVet(id);
    return _vaccinationRecords.stream()
    .filter(record -> record.vet().equals(vet))
    .collect(Collectors.toUnmodifiableList());
  }

  /**
   * Lists all erroneous vaccination records in a string containing
   * information about each record.
   * 
   * @return a String containing the VaccinationRecord object string
   * representation of all erroneous vaccination records
   */
  public Collection<VaccinationRecord> listErroneousVaccinations() {
    return _vaccinationRecords.stream()
    .filter(record -> record.damage() != HealthStatus.NORMAL)
    .collect(Collectors.toUnmodifiableList());
  }

  /**
   * Calculates the satisfaction of the specified animal
   * 
   * @param idAnimal the animal to calculate the satisfaction of
   * @return the satisfaction of the animal
   * @throws UnknownAnimalIdException if the animal id doesnt exist in any of
   * the habitats of the hotel
   */
  public double calculateAnimalSatisfaction(String idAnimal)
  throws UnknownAnimalIdException {
    return identifyAnimal(idAnimal).calculateSatisfaction();
  }

  /**
   * Calculates the satisfaction of an determinate employee
   * 
   * @param idEmployee the employee to calculate the satisfaction
   * @return the double that corresponds to the satisfaction of the employee
   * @throws UnknownEmployeeIdException if the employee doesnt exist in
   * the hotel
   */
  public double calculateEmployeeSatisfaction(String idEmployee)
  throws UnknownEmployeeIdException {
    return identifyEmployee(idEmployee).calculateSatisfaction();
  }

  /**
   * Calculates the global satisfaction level of the hotel by summing
   * the satisfaction levels of all employees and animals.
   * 
   * @return the global satisfaction level of the hotel
   */
  public double calculateGlobalSatisfaction() {
    return _employees.values().stream()
    .mapToDouble(Employee::calculateSatisfaction)
    .sum() + _habitats.values().stream()
    .mapToDouble(Habitat::calculateAnimalsSatisfaction)
    .sum();
  }

  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO error while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException,
  IOException, ImportFileException {
    new Parser(this).parseFile(filename);
    notifyHotelObservers();
  }
}
