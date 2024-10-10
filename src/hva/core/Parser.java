package hva.core;

import hva.core.exception.DuplicateAnimalIdException;
import hva.core.exception.DuplicateEmployeeIdException;
import hva.core.exception.DuplicateHabitatIdException;
import hva.core.exception.DuplicateIdException;
import hva.core.exception.DuplicateSpeciesIdException;
import hva.core.exception.DuplicateSpeciesNameException;
import hva.core.exception.DuplicateVaccineIdException;
import hva.core.exception.InvalidEmployeeTypeException;
import hva.core.exception.InvalidTreeTypeException;
import hva.core.exception.UnknownEmployeeIdException;
import hva.core.exception.UnknownHabitatIdException;
import hva.core.exception.UnknownResponsibilityException;
import hva.core.exception.UnknownSpeciesIdException;
import hva.core.exception.UnrecognizedEntryException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Parses a file with information about a Vet Hotel.
 */
public class Parser {
  private Hotel _hotel;
  private Map<String,Tree> _tempTreesNoHabitat;
  
  /**
   * Creates a new Parser.
   *
   * @param h the hotel to parse the file into
   */
  Parser(Hotel h) {
    _hotel = h;
    _tempTreesNoHabitat = new HashMap<>();
  }

  /**
   * Parses a file with information about a Vet Hotel.
   *
   * @param filename the name of the file to parse
   * @throws IOException if an I/O error occurs
   * @throws UnrecognizedEntryException if the file contains an invalid entry
   */
  public void parseFile(String filename) throws IOException,
  UnrecognizedEntryException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) { 
      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);
    }
  }

  /**
   * Parses a line from the file.
   * @param line the line to parse
   * @throws UnrecognizedEntryException if the line contains an invalid entry
   */
  private void parseLine(String line) throws UnrecognizedEntryException {
    String[] components = line.split("\\|");
    switch(components[0]) {
    case "ESPÉCIE" -> parseSpecies(components);
    case "ANIMAL" -> parseAnimal(components);
    case "ÁRVORE" -> parseTree(components);
    case "HABITAT" -> parseHabitat(components);
    case "TRATADOR" -> parseEmployee(components, "TRT");
    case "VETERINÁRIO" -> parseEmployee(components, "VET");
    case "VACINA" -> parseVaccine(components);
    default -> throw new UnrecognizedEntryException ("tipo de entrada inválido: "
    + components[0]);
    }
  }

  /**
   * Parses a line with format ANIMAL|id|name|idSpecies|idHabitat
   * 
   * @param components the components of the line to parse
   * (id, name, speciesId, habitatId)
   * @throws UnrecognizedEntryException if the line contains an invalid entry
   */
  private void parseAnimal(String[] components) throws
  UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      String habitatId = components[4];
      String speciesId = components[3];

      _hotel.registerAnimal(id, name, speciesId, habitatId);
    } catch (UnknownHabitatIdException | DuplicateAnimalIdException | UnknownSpeciesIdException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  /**
   * Parses a line with format ESPÉCIE|id|name
   * @param components the components of the line to parse (id, name)
   * @throws UnrecognizedEntryException if the line contains an invalid entry
   */
  private void parseSpecies(String[] components) throws
  UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];

      _hotel.registerSpecies(id, name);
    } catch (DuplicateSpeciesIdException | DuplicateSpeciesNameException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  /**
   * Parses a line with format TRATADOR|id|name|idHabitat1,...,idHabitatN or
   * VETERINÁRIO|id|nome|idEspécie1,...,idEspécieN
   * @param components the components of the line to parse (id, name, 
   * habitatIds or speciesIds)
   * @param empType the type of employee to parse (TRT or VET)
   * @throws UnrecognizedEntryException if the line contains an invalid entry
   */
  private void parseEmployee(String[] components, String empType) throws
  UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];

      _hotel.registerEmployee(id, name, empType);

      if (components.length == 4) {
        for(String responsibility : components[3].split(","))
          _hotel.addResponsibility(components[1], responsibility);
      }
		} catch (DuplicateEmployeeIdException | InvalidEmployeeTypeException |
    UnknownResponsibilityException | UnknownEmployeeIdException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  /**
   * Parses a line with format VACINA|id|name|idSpecies1,...,idSpeciesN
   * 
   * @param components the components of the line to parse 
   * (id, name, speciesIds)
   * @throws UnrecognizedEntryException if the line contains an invalid entry
   */
  private void parseVaccine(String[] components) throws
  UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      String[] speciesIds = components.length == 4 ?
      components[3].split(",") : new String[0];
      _hotel.registerVaccine(id, name, speciesIds);
    } catch (UnknownSpeciesIdException | DuplicateVaccineIdException | DuplicateIdException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  /**
   * Parses a line with format ÁRVORE|id|name|age|difficulty|type
   * @param components the components of the line to parse 
   * (id, name, age, diff, type)
   * @throws UnrecognizedEntryException if the line contains an invalid entry
   */
  private void parseTree(String[] components) throws
  UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      int age = Integer.parseInt(components[3]);
      int diff = Integer.parseInt(components[4]);
      String type = components[5];
	    Tree tree;
	    if(type != "PERENE" || type != "CADUCA")
		    throw new InvalidTreeTypeException(type);
	    if(type == "PERENE") {
        tree = new Evergreen(id, name, age, diff, null);
        _tempTreesNoHabitat.put(id, tree);
      }
      if(type == "CADUCA") {
        tree = new Deciduous(id, name, age, diff, null);
        _tempTreesNoHabitat.put(id, tree);
      }
    } catch (InvalidTreeTypeException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  /**
   * Parses a line with format HABITAT|id|name|area|treeId1,...,treeIdN
   * @param components the components of the line to parse
   * (id, name, area, treeIds)
   * @throws UnrecognizedEntryException if the line contains an invalid entry
   */
  private void parseHabitat(String[] components) throws
  UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      int area = Integer.parseInt(components[3]);

      Habitat hab = _hotel.registerHabitat(id, name, area);

      if (components.length == 5) {
        String[] listOfTree = components[4].split(",");
        for (String treeKey : listOfTree) {
          Tree tree =_tempTreesNoHabitat.get(treeKey);
          hab.plantTree(tree.id(), tree.name(), tree.age(),
          tree.baseCleaningDifficulty(), tree.treeType(),
          _hotel.currentSeason(), _hotel);
        }
      }
    } catch (InvalidTreeTypeException | DuplicateIdException | DuplicateHabitatIdException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }
}