package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an Duplicated Id entry.
 */
public class DuplicateHabitatIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
    /**
   * @param message the id that already exists
   **/
  public DuplicateHabitatIdException(String id) {
    super("Duplicated Habitat Id: " + id);
  }

  /**
   * @param message the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateHabitatIdException(String id, Exception cause) {
    super("Duplicated Habitat Id: " + id, cause);
  }
}
