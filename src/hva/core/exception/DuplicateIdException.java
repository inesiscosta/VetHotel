package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an Duplicated Id entry.
 */
public class DuplicateIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;

    /**
   * @param message the id that already exists
   **/
  public DuplicateIdException(String id) {
    super("Duplicated Id: " + id);
  }

  /**
   * @param message the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateIdException(String id, Exception cause) {
    super("Duplicated Id: " + id, cause);
  }
}
