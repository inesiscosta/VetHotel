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
  private HashSet<Employee> _employees;
  private HashSet<Habitat> _habitats;
  private HashSet<Vaccine> _vaccines;
  private List<VaccinationRecord> _vaccinationRecords;

  //TODO Add Hotel contructor!!
  public void nextSeason() {
    //TODO Implement Hotel.nextSeason
  }

  public int calculateGlobalSatisfaction() {
    //TODO Implement Hotel.calculateGlobalSatisfaction
    return 0;
  }

  public String listAnimals() {
    List<Habitat> habitats = new ArrayList<>(_habitats);
    habitats.sort(Comparator.comparing(Habitat::id)); //Dont know if its is the best way??
    StringBuilder allAnimals = new StringBuilder();
    for(Habitat habitat : habitats) {
      allAnimals.append(habitat.listAnimals()).append("\n");
    }
    return allAnimals.toString();
  }

  protected void registerNewAnimal(String idAnimal, String name, String idSpecies, String idHabitat) {
    //TODO Implement Hotel.registerNewAnimal
  }
  
  public Animal identifyAnimal(String idAnimal) {
    for(Habitat habitat : _habitats) {
      Animal animal = habitat.identifyAnimal(idAnimal);
      if(animal != null)
        return animal;
    }
    return null;
  }

  public String listEmployee() {
    List<Employee> employees = new ArrayList<>(_employees);
    employees.sort(Comparator.comparing(Employee::id)); //Dont know if its is the best way??
    StringBuilder listEmployee = new StringBuilder();
    for(Employee employee : _employees) {
      listEmployee.append(employee.toString()).append("\n");
    }
    return listEmployee.toString();
  }

  protected void registerNewEmployee(String idEmployee, String name, String type) {
    //TODO Implement Hotel.registerNewEmployee
  }

  public Species identifySpecies(String Species) {
    //TODO Implement Hotel.identifySpecies
    return null;
  }

  protected String addResponsibility(Employee employee, String idResponsability) {
    //TODO Implement Hotel.addResponsibility
    return "";
  }

  public String listHabitats(Season currentSeason) {
    //TODO Implement Hotel.listHabitats
    return "";
  }

  public Habitat identifyHabitat(String idHabitat) {
    return null;
  }

  public String listVaccines() {
    //TODO Implement Hotel.listVaccines
    return "";
  }

  public Veterinary identifyVet(String idVet) {
    //TODO Implement Hotel.identifyVet
    return null;
  }

  public Vaccine identifyVaccine(String idVaccine) {
    //TODO Implement Hotel.identifyVaccine
    return null;
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
