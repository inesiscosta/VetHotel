package hva.core;

import hva.core.exception.DuplicateAnimalIdException;
import hva.core.exception.DuplicateEmployeeIdException;
import hva.core.exception.DuplicateHabitatIdException;
import hva.core.exception.DuplicateSpeciesIdException;
import hva.core.exception.DuplicateSpeciesNameException;
import hva.core.exception.DuplicateVaccineIdException;
import hva.core.exception.EmployeeNotResponsibleException;
import hva.core.exception.ImportFileException;
import hva.core.exception.InvalidEmployeeTypeException;
import hva.core.exception.UnknownAnimalIdException;
import hva.core.exception.UnknownEmployeeIdException;
import hva.core.exception.UnknownVaccineIdException;
import hva.core.exception.UnknownResponsibilityException;
import hva.core.exception.UnknownHabitatIdException;
import hva.core.exception.UnknownSpeciesIdException;
import hva.core.exception.UnrecognizedEntryException;
import hva.core.modificationObserver.HotelObserver;
import hva.core.modificationObserver.HotelSubject;
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a Vet Hotel.
 */
public class Hotel implements  HotelSubject {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  private Season _currentSeason;
  private Map<String,Habitat> _habitats;
  private Map<String,Employee> _employees;
  private Map<String,Species> _species;
  private Map<String, Species> _speciesByName;
  private Map<String,Vaccine> _vaccines;
  private List<VaccinationRecord> _vaccinationRecords;
  private List<HotelObserver> _hotelObservers;
  private boolean _unsavedChanges;
  private String _filename;

  /**
   * Creates a new Vet Hotel.
   *
   */
  public Hotel() {
    _filename = null;
    _hotelObservers = new ArrayList<>();
    _unsavedChanges = false;
    _currentSeason = Season.Spring; //The hotel starts in the Spring season.
    _habitats = new TreeMap<>(new CaseInsensitiveOrderComparator());
    _species = new HashMap<>();
    _speciesByName = new HashMap<>();
    _employees = new TreeMap<>(new CaseInsensitiveOrderComparator());
    _vaccines = new HashMap<>();
    _vaccinationRecords = new ArrayList<VaccinationRecord>();
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
   * Gets whether the hotel has unsaved changes or not.
   * 
   * @return the state of the bool unsavedChanges
   */ 
  public boolean getUnsavedChanges() {
    return _unsavedChanges;
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
    for(HotelObserver observer : _hotelObservers) {
      observer.update(true);
    }
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
   * Advances the hotel to the next season.
   */
  int nextSeason() {
    _currentSeason = _currentSeason.nextSeason();
    for (Habitat habitat : _habitats.values())
      habitat.nextSeason(_currentSeason);
    notifyHotelObservers();
    return this.currentSeason().id();
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
    for(Habitat habitat : _habitats.values()) {
      if(habitat.containsAnimal(idAnimal))
        return habitat.identifyAnimal(idAnimal);
    }
    throw new UnknownAnimalIdException(idAnimal);
  }

  /**
   * Identifies a species by its id.
   * 
   * @param idSpecies the id of the species to identify
   * @return the species object with the given id
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
   * @throws UnknownVeterinarianIdException
   * if the veterinarian with the given id is not found
   */
  public Veterinarian identifyVet(String idVet)
  throws UnknownEmployeeIdException {
    Employee employee = identifyEmployee(idVet);
    if (employee.type().pt() != EmployeeType.VETERINARIAN.pt()) {
      throw new UnknownEmployeeIdException(idVet);
    }
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
   * Checks if the tree alreay exists on one of the habitats in the hotel
   * 
   * @param id the id to check 
   * @return true if it alredy exists fasle if not
  */  
  boolean treeAlreadyExists(String id) {
    for (Habitat habitat : _habitats.values()) {
      if (habitat.identifyTree(id) == null)
        return false;
    }
    return true;
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
    if (_habitats.containsKey(id))
      throw new DuplicateHabitatIdException(id);
    Habitat habitat = new Habitat(id, name, area);
    _habitats.put(id, habitat);
    notifyHotelObservers();
    return habitat;
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
    if(AnimalAlreadyExists(idAnimal))
      throw new DuplicateAnimalIdException(idHabitat);
    Habitat habitat = identifyHabitat(idHabitat);
    Species species = identifySpecies(idSpecies);
    new Animal(idAnimal, name, species, habitat);
    notifyHotelObservers();
  }

  /**
   * Test if this animal id already exists in one of the habitats
   * 
   * @param idAnimal the id of the animal
   * @return returns true if the animal exist in one of the habitats 
   * of the hotel
   */
  private boolean AnimalAlreadyExists(String idAnimal) {
    for(Habitat habitat : _habitats.values()) {
      if(habitat.containsAnimal(idAnimal))
        return true;
    }
    return false;
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
    if (_species.containsKey(id))
      throw new DuplicateSpeciesIdException(id);
    if (_speciesByName.containsKey(name))
      throw new DuplicateSpeciesNameException(name);
    Species species = new Species(id, name);
    _species.put(id, species);
    notifyHotelObservers();
  }

  /**
   * Registers a new employee in the hotel.
   * 
   * @param id the employee's unique identifier
   * @param name the employee's name
   * @param type the employee's type (VET or TRT)
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
    if (_vaccines.containsKey(idVaccine))
      throw new DuplicateVaccineIdException(idVaccine);
    List<Species> speciesList = new ArrayList<>();
    for (String id : speciesIds)
      speciesList.add(identifySpecies(id));
    Vaccine vaccine = new Vaccine(idVaccine, name, speciesList);
    _vaccines.put(idVaccine, vaccine);
    notifyHotelObservers();
  }

  /**
   * Adds a responsibility to an employee.
   * 
   * @param idEmployee the employee's unique identifier
   * @param idReponsibility the responsibility's unique identifier
   * @throws UnknownIdException if the employee with the given id is not found
   * @throws UnknownResponsibilityException if responsibility with the given id
   * is not found
   */
  public void addResponsibility(String idEmployee, String idReponsibility)
  throws UnknownEmployeeIdException, UnknownResponsibilityException {
    identifyEmployee(idEmployee).addResponsibility(idReponsibility);
    notifyHotelObservers();
  }

  /**
   * Removes a responsibility of an employee.
   * 
   * @param idEmployee the employee's unique identifier
   * @param idReponsibility the responsibility's unique identifier
   * @throws UnknownIdException if the employee with the given id is not found
   * @throws UnknownResponsibilityException if responsibility with the given id
   * is not found
   */
  public void removeResponsibility(String idEmployee, String idReponsibility)
  throws UnknownEmployeeIdException, UnknownResponsibilityException {
    identifyEmployee(idEmployee).removeResponsibility(idReponsibility);
    notifyHotelObservers();
  }

  /**
   * Adds a new vaccination record to the hotel and vaccinates the animal.
   * 
   * @param vet the veterinarian that is going to vaccinate the animal
   * @param animal the animal to vaccinate
   * @param vaccine the vaccine to apply
   */
  public boolean addVaccinationRecord(Veterinarian vet, Animal animal,
  Vaccine vaccine) throws EmployeeNotResponsibleException {
    boolean vaccineApropriated = true;
    if(!vaccine.isSpeciesApropriated(animal.species()))
      vaccineApropriated = false;
    VaccinationRecord record = vet.vaccinate(vaccine, animal);
    _vaccinationRecords.add(record);
    notifyHotelObservers();
    return vaccineApropriated;
  }

  /**
   * Changes the influence of a habitat on a species.
   * 
   * @param habitat the habitat to change the influence
   * @param species the species to change the influence
   * @param influenceString the influence to change to
   */
  public void changeHabitatInflunece(Habitat habitat, Species species,
  int influence) {
    if(habitat.identifyInfluence(species) == 0)
      habitat.addInfluence(species, influence);
    habitat.changeHabitatInflunece(species, influence);
    notifyHotelObservers();
  }

  /**
   * Lists all habitats in the hotel in an immutable list containing 
   * information about each habitat.
   * 
   * @return an immutable List containing the Habitat object string
   * representation of all habitats in the hotel
   */
  public List<String> listHabitats() {
    List<String> listHabitats = new ArrayList<>();
    for (Habitat habitat : _habitats.values()) {
      listHabitats.add(habitat.toString());
      listHabitats.addAll(habitat.listTrees(this.currentSeason()));
    }
    return Collections.unmodifiableList(listHabitats);
  }

  /**
   * List all the Trees of an habitat in a string containing 
   * information about each tree.
   * @param habitat The specific habitat
   * @return  a String containing the Tree information of all
   * trees in the habitat
   */
  public List<String> listTreesHabitat(Habitat habitat) {
    return Collections.unmodifiableList(habitat.listTrees(this.currentSeason()));
  }

  /**
   * Lists all animals in the hotel in an immutable list containing
   * information about each animal.
   * 
   * @return an immutable List containing the Animal object string
   * representation of all animals in the hotel
   */
  public List<Animal> listAnimals() {
    List<Animal> allAnimals = new ArrayList<>();
    for (Habitat habitat : _habitats.values())
      allAnimals.addAll(habitat.listAnimals());
    allAnimals.sort(Comparator.comparing(Animal::id,String.CASE_INSENSITIVE_ORDER)); //FIXME This is needed because the animals are sorted in each habitat but not globaly (Inês check this do we need a coment ?)
    return Collections.unmodifiableList(allAnimals);
  }

  /**
   * Lists all species in the hotel in an unmodifiable list containing
   * information about each species.
   * 
   * @return an unmodifiable List containing the Species object of all
   * species in the hotel
   */
  public List<Species> listSpecies() {
    return Collections.unmodifiableList(new ArrayList<>(_species.values()));
  }

  /**
   * Lists all employees in the hotel in an unmodifiable list containing
   * information about each employee.
   * 
   * @return an unmodifiable List containing the Employee object string
   * representation of all employees in the hotel
   */
  public List<Employee> listEmployees() {
    return Collections.unmodifiableList(new ArrayList<>(_employees.values()));
  }

  /**
   * Lists all vaccines in the hotel in an unmodifiable list containing
   * information about each vaccine.
   * 
   * @return an unmodifiable List containing the Vaccine objects of
   * all vaccines in the hotel
   */
  public List<Vaccine> listVaccines() {
    List<Vaccine> vaccines = new ArrayList<>(_vaccines.values());
    vaccines.sort(Comparator.comparing(Vaccine::id,
    String.CASE_INSENSITIVE_ORDER));
    return Collections.unmodifiableList(vaccines);
  }

  /**
   * Lists all vaccination records in an unmodifiable list containing
   * information about each record.
   * 
   * @return an unmodifiable List containing the VaccinationRecord objects
   */
  public List<VaccinationRecord> listVaccinationRecords() {
    return Collections.unmodifiableList(new ArrayList<>(_vaccinationRecords));
  }

  /**
   * Lists all vaccination records of vaccines given to a given
   * animal in a string containing information about each record.
   * 
   * @param animal the animal to list the vaccination records of
   * @return  an unmodifiable List containing the VaccinationRecord objects
   * of a specific animal
   */
  public List<VaccinationRecord> listAnimalVaccinationHistory(Animal animal) {
    List<VaccinationRecord> animalVaccinationHistory = new ArrayList<>();
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.animal().equals(animal))
        animalVaccinationHistory.add(record);
    }
    return Collections.unmodifiableList(animalVaccinationHistory);
  }

  /** 
   * Lists all vaccination records of vaccines administered by a given vet
   * in a string containing information about each record. 
   * 
   * @param vet the veterinarian to list the vaccination records of
   * @return an unmodifiable List containing the VaccinationRecord object
   * of all vaccination records of vaccines administered by the given vet
   */
  public List<VaccinationRecord> listVetVaccinationRecords(Veterinarian vet) {
    List<VaccinationRecord> vetVaccinationRecords = new ArrayList<>();
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.vet().equals(vet))
        vetVaccinationRecords.add(record);
    }
    return Collections.unmodifiableList(vetVaccinationRecords);
  }

  /**
   * Lists all erroneous vaccination records in a string containing
   * information about each record.
   * 
   * @return a String containing the VaccinationRecord object string
   * representation of all erroneous vaccination records
   */
  public List<VaccinationRecord> listErroneousVaccinations() {
    List<VaccinationRecord> erroneousVaccination = new ArrayList<>();
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.damage() != "NORMAL")
        erroneousVaccination.add(record);
    }
    return Collections.unmodifiableList(erroneousVaccination);
  }

  /**
   * Calculates the global satisfaction level of the hotel by summing 
   * the satisfaction levels of all employees and animals.
   * 
   * @return the global satisfaction level of the hotel
   */
  public int calculateGlobalSatisfaction() {
    double globalSatisfaction = 0;
    for (Employee employee : _employees.values())
      globalSatisfaction += employee.calculateSatisfaction();
    for (Habitat habitat : _habitats.values())
      globalSatisfaction += habitat.calculateAnimalsSatisfaction();
    return (int) Math.round(globalSatisfaction);
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
    var parser = new Parser(this);
    parser.parseFile(filename);
    notifyHotelObservers();
  }
}
