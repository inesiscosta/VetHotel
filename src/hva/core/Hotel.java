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

  public void nextSeason() {
    //TODO Implement Hotel.nextSeason
  }

  public int calculateGlobalSatisfaction() {
    //TODO Implement Hotel.calculateGlobalSatisfaction
    return 0;
  }

  public String listAnimals() {
    //TODO Implement Hotel.listAnimals
    return "";
  }

  protected void registerNewAnimal(String idAnimal, String name, String idSpecies, String idHabitat) {
    //TODO Implement Hotel.registerNewAnimal
  }
  
  public Animal identifyAnimal(String idAnimal) {
    //TODO Implement Hotel.identifyAimal
    return null;
  }

  public String listEmployee() {
    //TODO Implement Hotel.listEmployee
    return "";
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
    //TODO Implement Hotel.identifyHabitat
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

  public String listAnimalVaccinationHistory(String idAnimal) {
    String animalVaccinationHistory = null;
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.getAnimal().equals(identifyAnimal(idAnimal)))
        animalVaccinationHistory += record.toString() + "\n";
    }
    return animalVaccinationHistory;
  }

  public String listVetVaccinationRecords(Veterinary veterinary) {
    String vetVaccinationRecords = null;
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.getVet().equals(veterinary))
        vetVaccinationRecords += record.toString() + "\n";
    }
    return vetVaccinationRecords;
  }

  public String listErroneousVaccination() {
    String erroneousVaccination = null;
    for(VaccinationRecord record : _vaccinationRecords) {
      if(record.getDamage() != "NORMAL")
        erroneousVaccination += record.toString() + "\n" ; //FIXME Do we need to print the Damage??
    }
    return erroneousVaccination;
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
