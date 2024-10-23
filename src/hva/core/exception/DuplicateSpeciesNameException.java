package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an exception for a duplicate species name.
 */
public class DuplicateSpeciesNameException extends Exception {

  @Serial
  private static final long serialVersionUID = 202410232242L;

  private static final String ERROR_MESSAGE = "Duplicated Species name: ";
  
  /**
   * Constructor for the exception.
   * 
   * @param id the name that already exists
   **/
  public DuplicateSpeciesNameException(String id) {
    super(ERROR_MESSAGE + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the name that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateSpeciesNameException(String name, Exception cause) {
    super(ERROR_MESSAGE + name, cause);
  }
}
