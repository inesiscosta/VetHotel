package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an exception for a duplicate species name.
 */
public class DuplicateSpeciesNameException extends Exception {

  private static final String SPECIFIC_ERROR_MESSAGE = "Animal id: ";
  
  /**
   * Constructor for the exception.
   * 
   * @param id the name that already exists
   **/
  public DuplicateSpeciesNameException(String id) {
    super("Duplicated Species name: " + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the name that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateSpeciesNameException(String name, Exception cause) {
    super("Duplicated Species name: " + name, cause);
  }
}
