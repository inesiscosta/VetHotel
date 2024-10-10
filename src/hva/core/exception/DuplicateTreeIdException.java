package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an Duplicated Id entry.
 */
public class DuplicateTreeIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
    /**
   * @param message the id that already exists
   **/
  public DuplicateTreeIdException(String id) {
    super("Duplicated Tree id: " + id);
  }

  /**
   * @param message the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateTreeIdException(String id, Exception cause) {
    super("Duplicated Tree id: " + id, cause);
  }
}
