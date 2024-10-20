package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing a duplicate species id exception.
 */
public class DuplicateSpeciesIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id that already exists
   **/
  public DuplicateSpeciesIdException(String id) {
    super("Duplicated Species id: " + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateSpeciesIdException(String id, Exception cause) {
    super("Duplicated Species id: " + id, cause);
  }
}
