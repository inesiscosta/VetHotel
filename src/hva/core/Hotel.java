package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;

/**
 * Represents a Vet Hotel.
 */
public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  private Season _currentSeason;
  private Map<String,Habitat> _habitats;
  private Map<String,Employee> _employees;
  private Map<String,Species> _species;
  private Map<String,Vaccine> _vaccines;
  private List<VaccinationRecord> _vaccinationRecords;
  private Collection<String> _usedIds;
  private Collection<String> _existingSpeciesNames;

  /**
   * Creates a new Vet Hotel. The hotel starts in the Spring season.
   *
   */
  public Hotel() {
    _currentSeason = Season.Spring;
    _habitats = new TreeMap<>();
    _employees = new TreeMap<>();
    _species = new HashMap<>();
    _vaccines = new HashMap<>();
    _vaccinationRecords = new ArrayList<VaccinationRecord>();
    _usedIds = new HashSet<String>();
    _existingSpeciesNames = new HashSet<String>();
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
   * Checks if a given id is already used in the hotel.
   *
   * @param id the id to check
   * @return true if the id is already used, false otherwise
   */
  boolean isIdUsed(String id) {
    return _usedIds.contains(id);
  }

  /**
   * Advances the hotel to the next season.
   */
  void nextSeason() {
    _currentSeason = _currentSeason.nextSeason();
    for (Habitat habitat : _habitats.values())
      habitat.nextSeason(_currentSeason);
  }

  /**
   * Identifies a habitat by its id.
   * 
   * @param idHabitat the id of the habitat to identify
   * @return the habitat object with the given id
   * @throws UnknownHabitatIdException if the habitat with the given id is not found
   */
  public Habitat identifyHabitat(String idHabitat) throws UnknownHabitatIdException {
    if(!_habitats.containsKey(idHabitat))
      throw new UnknownHabitatIdException(idHabitat);
    return _habitats.get(idHabitat);
  }

  /**
   * Identifies an animal by its id.
   * 
   * @param idAnimal the id of the animal to identify
   * @return the animal object with the given id
   * @throws UnknownAnimalIdException if the animal with the given id is not found
   */
  public Animal identifyAnimal(String idAnimal) throws UnknownAnimalIdException {
    for(Habitat habitat : _habitats.values()) {
      Animal animal =  habitat.identifyAnimal(idAnimal);
      if (animal != null)
        return animal;
    }
    throw new UnknownAnimalIdException(idAnimal);
  }

  /**
   * Identifies a species by its id.
   * 
   * @param idSpecies the id of the species to identify
   * @return the species object with the given id
   * @throws UnknownSpeciesIdException if the species with the given id is not found
   */
  public Species identifySpecies(String idSpecies) throws UnknownSpeciesIdException {
    if(!_species.containsKey(idSpecies))
      throw new  UnknownSpeciesIdException(idSpecies);
    return _species.get(idSpecies);
  }

  /**
   * Identifies a veterinarian by its id.
   * 
   * @param idVet the id of the veterinarian to identify
   * @return the veterinarian object with the given id
   * @throws UnknownVeterinarianIdException if the veterinarian with the given id is not found
   */
  public Veterinarian identifyVet(String idVet) throws UnknownVeterinarianIdException {
    if(!_employees.containsKey(idVet))
      throw new UnknownVeterinarianIdException(idVet);
    Employee employee =  _employees.get(idVet);
    if (employee.type().toString() == "VET")
      return (Veterinarian) employee;
    return null;
  }

  public Employee identifyEmployee(String idEmployee) throws UnknownEmployeeIdException {
    if(!_employees.containsKey(idEmployee))
      throw new UnknownEmployeeIdException(idEmployee);
    return _employees.get(idEmployee);
  }

  /**
   * Identifies a vaccine by its id.
   * 
   * @param idVaccine the id of the vaccine to identify
   * @return the vaccine object with the given id
   * @throws UnknownVaccineIdException if the vaccine with the given id is not found
   */
  public Vaccine identifyVaccine(String idVaccine) throws UnknownVaccineIdException {
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
   * @return the new habitat object
   * @throws DuplicateIdException if the id is already used
   * @throws DuplicateHabitatIdException if there is already an Habitat with the same id
   */
  public Habitat registerHabitat(String id, String name, int area)
    throws DuplicateIdException, DuplicateHabitatIdException {
    if (_habitats.containsKey(id))
      throw new DuplicateHabitatIdException(id);
    else if (_usedIds.contains(id))
      throw new DuplicateIdException(id);
    Habitat habitat = new Habitat(id, name, area);
    _habitats.put(id, habitat);
    return habitat;
  }

  /**
   * Registers a new animal in the hotel.
   * 
   * @param idAnimal the animal's unique identifier
   * @param name the animal's name
   * @param idHabitat the habitat's id where the animal is
   * @param idSpecies the species' id of the animal
   * @throws UnknownHabitatIdException if the habitat with the given id is not found
   * @throws DuplicateAnimalIdException if the id is already used
   * @throws UnknownSpeciesIdException if the species with the given id is not found
   */
  public void registerAnimal(String idAnimal, String name, String idSpecies,
  String idHabitat) throws UnknownHabitatIdException, DuplicateAnimalIdException, UnknownSpeciesIdException {
    if (_usedIds.contains(idAnimal))
      throw new DuplicateAnimalIdException(idAnimal);
    Habitat habitat;
    try {
      habitat = identifyHabitat(idHabitat);
    } catch (UnknownHabitatIdException e) {
      throw new UnknownHabitatIdException(idHabitat, e);
    }
    Species species;
    try {
      species = identifySpecies(idSpecies);
    } catch (UnknownSpeciesIdException e) {
      throw new UnknownSpeciesIdException(idHabitat, e);
    } 
    new Animal(idAnimal, name, species, habitat);
    _usedIds.add(idAnimal);
  }

  /**
   * Registers a new species in the hotel.
   * 
   * @param id the species' unique identifier
   * @param name the species' name
   * @throws DuplicateIdException if the id is already used
   * @throws DuplicateSpeciesIdException if the species with this id already exists
   * @throws DuplicateSpeciesNameException if this species name is already used
   */
  public void registerSpecies(String id, String name)
  throws DuplicateSpeciesIdException, DuplicateIdException, DuplicateSpeciesNameException {
    try {
      identifySpecies(id);
      //If the species exist it doesnt throw a exception, else it is catched
      throw new DuplicateSpeciesIdException(id);
    } catch (UnknownSpeciesIdException e) { 
      //If the species doesnt exist it adds a new one
      if (_usedIds.contains(id))
        throw new DuplicateIdException(id);
      if(_existingSpeciesNames.contains(name))
        throw new DuplicateSpeciesNameException(name);
      Species specie = new Species(id, name);
      _species.put(id, specie);
      _usedIds.add(id);
      _existingSpeciesNames.add(name);
    }
  }

  /**
   * Registers a new employee in the hotel.
   * 
   * @param id the employee's unique identifier
   * @param name the employee's name
   * @param type the employee's type (VET or TRT)
   * @throws DuplicateIdException if the id is already used
   * @throws InvalidEmployeeTypeException if the type is not valid
   * @throws DuplicateEmployeeIdException if an employee with the same id alredy exists
   */
  public void registerEmployee(String id, String name, String type) 
  throws DuplicateIdException, DuplicateEmployeeIdException, InvalidEmployeeTypeException {
    if (_employees.containsKey(id))
      throw new DuplicateEmployeeIdException(id);
    if (_usedIds.contains(id))
      throw new DuplicateIdException(id);
    Employee employee = null;
    switch (type) {   //TODO Maybe also use a method from the enum or change the TreeType and DoAddTreehabitat, DoShowAllEmployees and DoShowHabitats and DoShowTrees for consistent across the project
      case "VET":
        employee = new Veterinarian(id, name);
        break;
      case "TRT":
        employee = new ZooKeeper(id, name);
        break;
      default:
        throw new InvalidEmployeeTypeException(type);
    }
    _employees.put(id, employee);
    _usedIds.add(id);
  }

  /**
   * Registers a new vaccine in the hotel.
   * 
   * @param vaccineId the vaccine's unique identifier
   * @param name the vaccine's name
   * @param speciesIds the species' ids that the vaccine is suitable for
   * @throws UnknownSpeciesIdException if the species with the given id is not found
   * @throws DuplicateIdException if the id is already used
   * @throws DuplicateVaccineIdException if a vaccine with the same id already exists
   */
  public void registerVaccine(String vaccineId, String name, String[] speciesIds)
  throws UnknownSpeciesIdException, DuplicateVaccineIdException, DuplicateIdException {
    if (_vaccines.containsKey(vaccineId))
      throw new DuplicateVaccineIdException(vaccineId);
    if (_usedIds.contains(vaccineId))
      throw new DuplicateIdException(vaccineId);
    List<Species> speciesList = new ArrayList<>();
    for (String id : speciesIds) {
      Species species;
      try {
        species = identifySpecies(id);
      } catch (UnknownSpeciesIdException e) {
        throw new UnknownSpeciesIdException(id, e);
      }
      speciesList.add(species);
    }
    Vaccine vaccine = new Vaccine(vaccineId, name, speciesList);
    _vaccines.put(vaccineId, vaccine);
    _usedIds.add(vaccineId);
  }

  /**
   * Adds a responsibility to an employee.
   * 
   * @param idEmployee the employee's unique identifier
   * @param idReponsibility the responsibility's unique identifier
   * @throws UnknownIdException if the employee with the given id is not found
   * @throws UnknownResponsabilityException if responsibility with the given id
   * is not found
   */
  public void addResponsibility(String idEmployee, String idReponsibility)
  throws UnknownEmployeeIdException, UnknownResponsabilityException {
    Employee employee;
    try {
      employee = _employees.get(idEmployee);
    } catch (NullPointerException e) {
      throw new UnknownEmployeeIdException(idEmployee, e);
    }
    employee.addResponsibility(idReponsibility);
  }

  /**
   * Removes a responsibility of an employee.
   * 
   * @param idEmployee the employee's unique identifier
   * @param idReponsibility the responsibility's unique identifier
   * @throws UnknownIdException if the employee with the given id is not found
   * @throws UnknownResponsabilityException if responsibility with the given id
   * is not found
   */
  public void removeResponsibility(String idEmployee, String idReponsibility)
  throws UnknownEmployeeIdException, UnknownResponsabilityException {
    Employee employee;
    try {
      employee = _employees.get(idEmployee);
    } catch (NullPointerException e) {
      throw new UnknownEmployeeIdException(idEmployee, e);
    }
    employee.removeResponsibility(idReponsibility);
  }

  /**
   * Adds a new vaccination record to the hotel and vaccinates the animal.
   * 
   * @param vet the veterinarian that is going to vaccinate the animal
   * @param animal the animal to vaccinate
   * @param vaccine the vaccine to apply
   */
  public void addVaccinationRecord(Veterinarian vet, Animal animal,
  Vaccine vaccine) throws EmployeeNotResponsibleException {
    VaccinationRecord record = vet.vaccinate(vaccine, animal);
    _vaccinationRecords.add(record);
  }

  /**
   * 
   * @param habitat
   * @param species
   * @param influenceString
   */
  public void changeHabitatInflunece(Habitat habitat, Species species, String influenceString) {
    int influence;
    switch (influenceString) {
      case "POS":
        influence = 20;
        break;
      case "NEG":
        influence = -20;
      default:
        influence = 0;
        break;
    }
    if(habitat.identifyInfluence(species) == 0)
      habitat.addInfluence(species, influence);
    habitat.changeHabitatInflunece(species, influence);
  }

  /**
   * Lists all habitats in the hotel in a string containing 
   * information about each habitat.
   * 
   * @return a String containing the Habitat object string
   * representation of all habitats in the hotel
   */
  public String listHabitats() {
    StringBuilder listHabitats = new StringBuilder();
    for(Habitat habitat : _habitats.values())
      listHabitats.append(habitat.toString(this.currentSeason()));
    return listHabitats.toString();
  }

  /**
   * List all the Trees of an habitat in a string containing 
   * information about each tree.
   * @param habitat The specific habitat
   * @return  a String containing the Tree information of all
   * trees in the habitat
   */
  public String listTreesHabitat(Habitat habitat) {
    return habitat.listTrees(this.currentSeason());
  }

  /**
   * Lists all animals in the hotel in a string containing
   * information about each animal.
   * 
   * @return a String containing the Animal object string
   * representation of all animals in the hotel
   */
  public String listAnimals() {
    StringBuilder allAnimals = new StringBuilder();
    for (Habitat habitat : _habitats.values())
      allAnimals.append(habitat.listAnimals()).append("\n");
    if(!allAnimals.isEmpty())
      allAnimals.setLength(allAnimals.length()-1);
    return allAnimals.toString();
  }

  /**
   * Lists all species in the hotel in a string containing
   * information about each species.
   * 
   * @return a String containing the Species object string
   * representation of all species in the hotel
   */
  public String listSpecies() {
    StringBuilder listSpecies = new StringBuilder();
    for (Species species : _species.values())
      listSpecies.append(species.toString()).append("\n");
    return listSpecies.toString();
  }

  /**
   * Lists all employees in the hotel in a string containing
   * information about each employee.
   * 
   * @return a String containing the Employee object string
   * representation of all employees in the hotel
   */
  public String listEmployees() {
    StringBuilder listEmployee = new StringBuilder();
    for (Employee employee : _employees.values())
      listEmployee.append(employee.toString()).append("\n");
    return listEmployee.toString();
  }

  /**
   * Lists all vaccines in the hotel in a string containing
   * information about each vaccine.
   * 
   * @return a String containing the Vaccine object string
   * representation of all vaccines in the hotel
   */
  public String listVaccines() {
    List<Vaccine> vaccines = new ArrayList<>(_vaccines.values());
    vaccines.sort(Comparator.comparing(Vaccine::id));
    StringBuilder listVaccines = new StringBuilder();
    for(Vaccine vaccine : vaccines)
      listVaccines.append(vaccine.toString());
    listVaccines.setLength(listVaccines.length()-1);
    return listVaccines.toString();
  }

  /**
   * Lists all vaccination records in a string containing
   * information about each record.
   * 
   * @return a String containing the VaccinationRecord object string
   * representation of all vaccination records
   */
  public String listVaccinationRecords() {
    StringBuilder listVaccinationRecords = new StringBuilder();
    for(VaccinationRecord record : _vaccinationRecords)
      listVaccinationRecords.append(record.toString()).append("\n");
    return listVaccinationRecords.toString();
  }

  /**
   * Lists all vaccination records of vaccines given to a given
   * animal in a string containing information about each record.
   * 
   * @param animal the animal to list the vaccination records of
   * @return a String containing the VaccinationRecord object string representation
   *  of all vaccination records of vaccines given to the given animal
   */
  public String listAnimalVaccinationHistory(Animal animal) {
    StringBuilder animalVaccinationHistory = new StringBuilder();
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.animal().equals(animal))
        animalVaccinationHistory.append(record.toString()).append("\n");
    }
    return animalVaccinationHistory.toString();
  }

  /** 
   * Lists all vaccination records of vaccines administered by a given vet
   * in a string containing information about each record. 
   * 
   * @param veterinary the veterinarian to list the vaccination records of
   * @return a String containing the VaccinationRecord object string representation
   * of all vaccination records of vaccines administered by the given vet
   */
  public String listVetVaccinationRecords(Veterinarian veterinary) {
    StringBuilder vetVaccinationRecords = new StringBuilder();
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.vet().equals(veterinary))
        vetVaccinationRecords.append(record.toString()).append("\n");
    }
    return vetVaccinationRecords.toString();
  }

  /**
   * Lists all erroneous vaccination records in a string containing
   * information about each record.
   * 
   * @return a String containing the VaccinationRecord object string
   * representation of all erroneous vaccination records
   */
  public String listErroneousVaccinations() {
    StringBuilder erroneousVaccination = new StringBuilder();
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.damage() != "NORMAL")
        erroneousVaccination.append(record.toString()).append("\n");
    }
    return erroneousVaccination.toString();
  }

  /**
   * Calculates the global satisfaction level of the hotel by summing 
   * the satisfaction levels of all employees and all animals.
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
  //  UnallowedTypeException, DuplicateException, UnallowedKeyException,
  void importFile(String filename) throws UnrecognizedEntryException,
  IOException, ImportFileException {
    var parser = new Parser(this);
    parser.parseFile(filename);
  }
}
