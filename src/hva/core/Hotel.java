package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*; // We need to specify each import from java.util I think. Ja estava no modelo do prof. - Miguel
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
    _employees = new TreeMap<>();
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

  protected void registerNewSpecies(String id, String name) throws UnknowIdException, DucplicatedIdException {
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

  protected void registerNewAnimal(String idAnimal, String name, String idSpecies, String idHabitat) throws UnknowIdException, DucplicatedIdException {
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
    } catch (UnknowIdException e) { //If the species doesnt exists it call the registerNewSpecies
      registerNewSpecies(idSpecies, name);
      species = identifySpecies(idSpecies); //After the new species is created it needs to make the object available
    } 
    new Animal(idAnimal, name, species, habitat);
    _usedIds.add(idAnimal);
  }

  public String listEmployee() {
    StringBuilder listEmployee = new StringBuilder();
    for (Employee employee : _employees.values())
      listEmployee.append(employee.toString()).append("\n");
    return listEmployee.toString();
  }

  protected void registerNewEmployee(String id, String name, String type) throws DucplicatedIdException, InvalidTypeException {
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

  // Not sure if this is the best way to implement this method. But i dont want to pass Hotel as an argument in vaccinate.
  protected void addVaccinationRecord(Veterinarian vet, Animal animal, Vaccine vaccine) {
    VaccinationRecord record = vet.vaccinate(vaccine, animal);
    _vaccinationRecords.add(record);
  }

  protected String listAnimalVaccinationRecord(String id) throws UnknowIdException {
    Animal animal = identifyAnimal(id); //Here we dont need to try catch right??
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
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
  }
}
