package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing a duplicate habitat id exception.
 */
public class DuplicateHabitatIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id that already exists
   **/
  public DuplicateHabitatIdException(String id) {
    super("Duplicated Habitat id: " + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateHabitatIdException(String id, Exception cause) {
    super("Duplicated Habitat id: " + id, cause);
  }
}
