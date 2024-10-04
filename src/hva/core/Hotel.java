package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
import hva.core.Animal;
import hva.core.Decidious;
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
import hva.core.Veterinary;
import hva.core.ZooKeeper;

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  private Season _currentSeason;
  private HashMap<String,Employee> _employees;
  private HashMap<String,Habitat> _habitats;
  private HashMap<String,Vaccine> _vaccines;
  private HashMap<String,Species> _species;
  private List<VaccinationRecord> _vaccinationRecords;
  private Collection<String> _usedIds;

  public Hotel() {
    _currentSeason = Season.getSeason(0); //The app starts in the Spring(Primavera)
    _employees = new HashMap<>();
    _habitats = new HashMap<>();
    _vaccines = new HashMap<>();
    _species = new HashMap<>();
    _vaccinationRecords = new ArrayList<VaccinationRecord>();
    _usedIds = new HashSet<String>();
  }

  public void nextSeason() {
    Season[] seasons = Season.values();
    int idCurrentSeason = Season.getSeason(_currentSeason);
    _currentSeason = seasons[(idCurrentSeason + 1) % seasons.length];
  }

  public int calculateGlobalSatisfaction() {
    //TODO Implement Hotel.calculateGlobalSatisfaction
    return 0;
  }

  public String listAnimals() {
    List<Habitat> habitats = new ArrayList<>(_habitats.values());
    habitats.sort(Comparator.comparing(Habitat::id)); //Dont know if its is the best way??
    StringBuilder allAnimals = new StringBuilder();
    for(Habitat habitat : habitats) {
      allAnimals.append(habitat.listAnimals()).append("\n");
    }
    return allAnimals.toString();
  }

  protected void registerNewSpecies(String idSpecie, String name) { //Think is makes more sense here than on Animal Class because from the serialization its way easier for the Hotel to know the species
    Species specie = new Species(idSpecie, name);
    _species.put(idSpecie, specie);
  }

  protected void registerNewAnimal(String idAnimal, String name, String idSpecies, String idHabitat) {
    //TODO Implement Hotel.registerNewAnimal
  }
  
  public Animal identifyAnimal(String idAnimal) {
    for(Habitat habitat : _habitats.values()) {
      Animal animal =  habitat.identifyAnimal(idAnimal);
      if (animal != null)
        return animal;
    }
    return null;
  }

  public String listEmployee() {
    List<Employee> employees = new ArrayList<>(_employees.values());
    employees.sort(Comparator.comparing(Employee::id)); //Dont know if its is the best way??
    StringBuilder listEmployee = new StringBuilder();
    for(Employee employee : _employees.values()) {
      listEmployee.append(employee.toString()).append("\n");
    }
    return listEmployee.toString();
  }

  protected void registerNewEmployee(String idEmployee, String name, String type) {
    //TODO Implement Hotel.registerNewEmployee
  }

  public Species identifySpecies(String idSpecies) {
    return _species.get(idSpecies);
  }

  protected String addResponsibility(Employee employee, String idResponsability) {
    if (employee instanceof Veterinary) { //A bit cursedd but I think better than employeeType() == "VET"
      Veterinary vet = (Veterinary) employee;
      vet.addSpecies(identifySpecies(idResponsability));
      identifySpecies(idResponsability).addQualifiedVet(vet);
    } else if (employee instanceof ZooKeeper) {
      ZooKeeper keeper = (ZooKeeper) employee;
      keeper.addHabitat(identifyHabitat(idResponsability));
      identifyHabitat(idResponsability).addZooKeeper(keeper);
    }
    return idResponsability; //FIXME Maybe we need to think about this 
  }

  public String listHabitats(Season currentSeason) {
    List<Habitat> habitats = new ArrayList<>(_habitats.values());
    habitats.sort(Comparator.comparing(Habitat::id));
    StringBuilder listHabitats = new StringBuilder();
    for(Habitat habitat : habitats) {
      listHabitats.append(habitat.toString());
    }
    return habitats.toString();
  }

  public Habitat identifyHabitat(String idHabitat) {
    return _habitats.get(idHabitat);
  }

  public String listVaccines() {
    List<Vaccine> vaccines = new ArrayList<>(_vaccines.values());
    vaccines.sort(Comparator.comparing(Vaccine::id));
    StringBuilder listHabitats = new StringBuilder();
    for(Vaccine vaccine : vaccines) {
      listHabitats.append(vaccine.toString());
    }
    return vaccines.toString();
  }

  public Veterinary identifyVet(String idVet) {
    Employee employee =  _employees.get(idVet);
    if (employee.employeeType() == "VET") {
      return (Veterinary) employee;
    }
    return null;
  }

  public Vaccine identifyVaccine(String idVaccine) {
    return _vaccines.get(idVaccine);
  }

  public String listAnimalVaccinationHistory(Animal animal) {
    StringBuilder animalVaccinationHistory = new StringBuilder();
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.getAnimal().equals(animal))
        animalVaccinationHistory.append(record.toString()).append("\n");
    }
    return animalVaccinationHistory.toString();
  }

  public String listVetVaccinationRecords(Veterinary veterinary) {
    StringBuilder vetVaccinationRecords = new StringBuilder();
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.getVet().equals(veterinary))
        vetVaccinationRecords.append(record.toString()).append("\n");
    }
    return vetVaccinationRecords.toString();
  }

  public String listErroneousVaccination() {
    StringBuilder erroneousVaccination = new StringBuilder();
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.getDamage() != "NORMAL")
        erroneousVaccination.append(record.toString()).append("\n");
    }
    return erroneousVaccination.toString();
  }

  protected void addVaccinationRecord(VaccinationRecord record) {
    //TODO Implement Hotel.addVaccinationRecord
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
