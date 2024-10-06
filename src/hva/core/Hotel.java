package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
import hva.core.Animal;
import hva.core.Deciduous;
import hva.core.Employee;
import hva.core.Evergreen;
import hva.core.Habitat;
import hva.core.HealthStatus;
import hva.core.Leaf;
import hva.core.NamedEntity;
import hva.core.Season;
import hva.core.Species;
import hva.core.Tree;
import hva.core.VaccinationRecord;
import hva.core.Vaccine;
import hva.core.Veterinarian;
import hva.core.ZooKeeper;

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  private Season _currentSeason;
  private Map<String,Employee> _employees;
  private Map<String,Habitat> _habitats;
  private Map<String,Vaccine> _vaccines;
  private Map<String,Species> _species;
  private List<VaccinationRecord> _vaccinationRecords;
  private Collection<String> _usedIds;

  public Hotel() {
    _currentSeason = Season.Spring;
    _employees = new TreeMap<>(); //Dont know wich is better being already sorted in natural order or the .get being O(log n) and not O(1), we need to see this
    _habitats = new TreeMap<>();
    _vaccines = new HashMap<>();
    _species = new HashMap<>();
    _vaccinationRecords = new ArrayList<VaccinationRecord>();
    _usedIds = new HashSet<String>();
  }

  Season currentSeason() {
    return _currentSeason;
  }

  public void nextSeason() {
    _currentSeason = _currentSeason.nextSeason();
  }

  public Animal identifyAnimal(String idAnimal) throws UnknowIdException {
    for(Habitat habitat : _habitats.values()) {
      Animal animal =  habitat.identifyAnimal(idAnimal);
      if (animal != null)
        return animal;
    }
    throw new UnknowIdException(UnknowIdException.errorMessage() + idAnimal);
  }

  public Species identifySpecies(String idSpecies) throws UnknowIdException {
    if(!_species.containsKey(idSpecies))
      throw new UnknowIdException(UnknowIdException.errorMessage() + idSpecies);
    return _species.get(idSpecies);
  }

  public Habitat identifyHabitat(String idHabitat) throws UnknowIdException {
    if(!_habitats.containsKey(idHabitat))
      throw new UnknowIdException(UnknowIdException.errorMessage() + idHabitat);
    return _habitats.get(idHabitat);
  }

  public Veterinarian identifyVet(String idVet) throws UnknowIdException {
    if(!_employees.containsKey(idVet))
      throw new UnknowIdException(UnknowIdException.errorMessage() + idVet);
    Employee employee =  _employees.get(idVet);
    if (employee.type().toString() == "VET") {
      return (Veterinarian) employee;
    }
    return null;
  }

  public Vaccine identifyVaccine(String idVaccine) throws UnknowIdException {
    if(!_vaccines.containsKey(idVaccine))
      throw new UnknowIdException(UnknowIdException.errorMessage() + idVaccine);
    return _vaccines.get(idVaccine);
  }

  public int calculateGlobalSatisfaction() {
    double globalSatisfaction = 0;
    for (Employee employee : _employees.values()) {
      globalSatisfaction += employee.calculateSatisfaction();
    }
    for (Habitat habitat : _habitats.values()) {
      globalSatisfaction += habitat.calculateAnimalsSatisfaction();
    }
    return (int) Math.round(globalSatisfaction);
  }

  public String listAnimals() {
    StringBuilder allAnimals = new StringBuilder();
    for (Habitat habitat : _habitats.values())
      allAnimals.append(habitat.listAnimals()).append("\n");
    return allAnimals.toString();
  }

  protected void registerSpecies(String id, String name) throws UnknowIdException, DucplicatedIdException {
    try {
      identifySpecies(id); //If the species exist it doesnt throw a exception, else it is catched
      throw new DucplicatedIdException(DucplicatedIdException.errorMessageSpecies() + id);
    } catch (UnknowIdException e) { //If the species doesnt exist it add a new one
      if (_usedIds.contains(id))
        throw new DucplicatedIdException(id);
      Species specie = new Species(id, name);
      _species.put(id, specie);
      _usedIds.add(id);
    }

  }

  protected void registerAnimal(String idAnimal, String name, String idHabitat, String idSpecies) throws UnknowIdException, DucplicatedIdException {
    if (_usedIds.contains(idAnimal))
      throw new DucplicatedIdException(DucplicatedIdException.errorMessage() + idAnimal);
    Habitat habitat;
    try {
      habitat = identifyHabitat(idHabitat);
    } catch (UnknowIdException e) {
      throw new UnknowIdException(UnknowIdException.errorMessageHabitat() + idHabitat, e);
    }
    Species species;
    try {
      species = identifySpecies(idSpecies);
    } catch (UnknowIdException e) { //If the species doesn't exist it calls registerSpecies
      registerSpecies(idSpecies, name);
      species = identifySpecies(idSpecies); //After the new species is created it needs to make the object available
    } 
    new Animal(idAnimal, name, species, habitat);
    _usedIds.add(idAnimal);
  }

  public void registerVaccine(String vaccineId, String name, String[] speciesIds) throws UnknowIdException, DucplicatedIdException {
    if (_vaccines.containsKey(vaccineId))
      throw new DucplicatedIdException(DucplicatedIdException.errorMessageVaccine() + vaccineId);
    if (_usedIds.contains(vaccineId))
      throw new DucplicatedIdException(DucplicatedIdException.errorMessage() + vaccineId);
    List<Species> speciesList = new ArrayList<>();
    for (String id : speciesIds) {
      Species species;
      try {
        species = identifySpecies(id);
      } catch (UnknowIdException e) {
        throw new UnknowIdException(UnknowIdException.errorMessageSpecies() + id, e);
      }
      speciesList.add(species);
    }
    Vaccine vaccine = new Vaccine(vaccineId, name, speciesList);
    _vaccines.put(vaccineId, vaccine);
    _usedIds.add(vaccineId);
  }

  public String listEmployee() {
    StringBuilder listEmployee = new StringBuilder();
    for (Employee employee : _employees.values())
      listEmployee.append(employee.toString()).append("\n");
    return listEmployee.toString();
  }

  protected void registerEmployee(String id, String name, String type) throws DucplicatedIdException, InvalidTypeException {
    if (_employees.containsKey(id))
      throw new DucplicatedIdException(DucplicatedIdException.errorMessageEmployee() + id);
    if (_usedIds.contains(id))
      throw new DucplicatedIdException(DucplicatedIdException.errorMessage() + id);
    Employee employee = null;
    switch (type) {
      case "VET":
        employee = new Veterinarian(id, name);
        break;
      case "TRT":
        employee = new ZooKeeper(id, name);
        break;
      default:
        throw new InvalidTypeException(InvalidTypeException.ErrorMessageEmployee() + type);
    }
    _employees.put(id, employee);
    _usedIds.add(id);
  }

  public void addResponsibility(String idEmployee, String idReponsibility) throws UnknowIdException {
    Employee employee;
    try {
      employee = _employees.get(idEmployee);
    } catch (NullPointerException e) {
      throw new UnknowIdException(UnknowIdException.errorMessageEmployee() + idEmployee, e);
    }
    employee.addResponsibility(idReponsibility);
  }

  public String listHabitats(Season currentSeason) {
    StringBuilder listHabitats = new StringBuilder();
    for(Habitat habitat : _habitats.values())
      listHabitats.append(habitat.toString());
    return listHabitats.toString();
  }

  public String listVaccines() {
    List<Vaccine> vaccines = new ArrayList<>(_vaccines.values());
    vaccines.sort(Comparator.comparing(Vaccine::id));
    StringBuilder listHabitats = new StringBuilder();
    for(Vaccine vaccine : vaccines)
      listHabitats.append(vaccine.toString());
    return vaccines.toString();
  }

  public String listAnimalVaccinationHistory(Animal animal) {
    StringBuilder animalVaccinationHistory = new StringBuilder();
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.animal().equals(animal))
        animalVaccinationHistory.append(record.toString()).append("\n");
    }
    return animalVaccinationHistory.toString();
  }

  public String listVetVaccinationRecords(Veterinarian veterinary) {
    StringBuilder vetVaccinationRecords = new StringBuilder();
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.vet().equals(veterinary))
        vetVaccinationRecords.append(record.toString()).append("\n");
    }
    return vetVaccinationRecords.toString();
  }

  public String listErroneousVaccination() {
    StringBuilder erroneousVaccination = new StringBuilder();
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.damage() != "NORMAL")
        erroneousVaccination.append(record.toString()).append("\n");
    }
    return erroneousVaccination.toString();
  }

  // Not sure if this is the best way to implement this method. But i dont want to pass Hotel as an argument in vaccinate. If this is the case when the user want's to vaccinate we need to call this method instead of vaccinate as well of course as identity all the arguments.
  protected void addVaccinationRecord(Veterinarian vet, Animal animal, Vaccine vaccine) {
    VaccinationRecord record = vet.vaccinate(vaccine, animal);
    _vaccinationRecords.add(record);
  }

  protected String listAnimalVaccinationRecord(String id) throws UnknowIdException {
    Animal animal = identifyAnimal(id);
    StringBuilder animalVaxRecord = new StringBuilder();
    for (VaccinationRecord record : _vaccinationRecords) {
      if (record.animal().equals(animal))
        animalVaxRecord.append(record.toString()).append("\n");
    }
    return animalVaxRecord.toString();
  }

  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  //  UnallowedTypeException, DuplicateException, UnallowedKeyException,
  void importFile(String filename) throws UnrecognizedEntryException, IOException, ImportFileException {
    //var parser = new Parser(this);
    //parser.parseFile(filename);
  }
}
