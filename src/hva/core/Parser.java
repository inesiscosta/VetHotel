package hva.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
/*
import java.io.Reader;
TODO Maybe this import are not necessary
import java.util.Collection;
import java.util.ArrayList;
*/
import java.util.HashMap;

import hva.core.exception.DucplicatedIdException;
import hva.core.exception.InvalidTypeException;
import hva.core.exception.UnknowIdException;
import hva.core.exception.UnrecognizedEntryException;

public class Parser {
  private Hotel _hotel;
  private HashMap<String,Tree> _tempTreesNoHabitat;

  Parser(Hotel h) {
    _hotel = h;
    _tempTreesNoHabitat = new HashMap<>();
  }

  public void parseFile(String filename) throws IOException, UnrecognizedEntryException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) { //Small correction 
      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);

    }
  }

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
    default -> throw new UnrecognizedEntryException ("tipo de entrada inválido: " + components[0]);
    }
  }

  // Parse a line with format ANIMAL|id|nome|idEspécie|idHabitat
  private void parseAnimal(String[] components) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      String habitatId = components[4];
      String speciesId = components[3];

      _hotel.registerAnimal(id, name, habitatId, speciesId);
    } catch (UnknowIdException | DucplicatedIdException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format ESPÉCIE|id|nome
  private void parseSpecies(String[] components) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];

      _hotel.registerSpecies(id, name);
    } catch (UnknowIdException | DucplicatedIdException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }
  
  // Parse a line with format TRATADOR|id|nome|idHabitat1,...,idHabitatN or
  // VETERINÁRIO|id|nome|idEspécie1,...,idEspécieN
  private void parseEmployee(String[] components, String empType) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];

      _hotel.registerEmployee(id, name, empType);

      if (components.length == 4) {
        for(String responsibility : components[3].split(","))
          _hotel.addResponsibility(components[1], responsibility);
      }
		} catch (UnknowIdException | DucplicatedIdException | InvalidTypeException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format VACINA|id|nome|idEspécie1,...,idEspécieN
  private void parseVaccine(String[] components) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      String[] speciesIds = components.length == 4 ? components[3].split(",") : new String[0];
      _hotel.registerVaccine(id, name, speciesIds);
    } catch (DucplicatedIdException | UnknowIdException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format ÁRVORE|id|nome|idade|dificuldade|tipo
  private void parseTree(String[] components) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      int age = Integer.parseInt(components[3]);
      int diff = Integer.parseInt(components[4]);
      String type = components[5];
	    Tree tree;
	    if(type != "PERENE" || type != "CADUCA")
		    throw new InvalidTypeException(InvalidTypeException.ErrorMessage());
	    if(_hotel.isIdUsed(id)) //We need to add this to Hotel and the plantTree Method and maybe remove from here the Tree is the only object that is not created by the Hotel
		    throw new DucplicatedIdException(DucplicatedIdException.errorMessage()); //Should we only verify in the plantTree ??
	    if(type == "PERENE") {
        tree = new Evergreen(id, name, age, diff, null);
        _tempTreesNoHabitat.put(id, tree);
      }
      if(type == "CADUCA") {
        tree = new Deciduous(id, name, age, diff, null);
        _tempTreesNoHabitat.put(id, tree);
      }
    } catch (InvalidTypeException | DucplicatedIdException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format HABITAT|id|nome|área|idÁrvore1,...,idÁrvoreN
  private void parseHabitat(String[] components) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      int area = Integer.parseInt(components[3]);

      Habitat hab = _hotel.registerHabitat(id, name, area);

      if (components.length == 5) {
        String[] listOfTree = components[4].split(",");
        for (String treeKey : listOfTree) {
          Tree tree =_tempTreesNoHabitat.get(treeKey);
          hab.plantTree(tree.id(), tree.name(), tree.age(), tree.baseCleaningDifficulty(), tree.treeType(), _hotel.currentSeason());
        }
      }
    } catch (InvalidTypeException | DucplicatedIdException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }
}