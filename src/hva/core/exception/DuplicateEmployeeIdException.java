package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an Duplicated Id entry.
 */
public class DuplicateEmployeeIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param message the id that already exists
   **/
  public DuplicateEmployeeIdException(String id) {
    super("Duplicated Employee id: " + id);
  }

  /**
   * @param message the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateEmployeeIdException(String id, Exception cause) {
    super("Duplicated Employee id: " + id, cause);
  }
}
