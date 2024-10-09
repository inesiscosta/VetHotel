package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an Duplicated Id entry.
 */
public class DuplicateAnimalIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
    /**
   * @param message the id that already exists
   **/
  public DuplicateAnimalIdException(String id) {
    super("Duplicated Animal Id: " + id);
  }

  /**
   * @param message the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateAnimalIdException(String id, Exception cause) {
    super("Duplicated Animal Id: " + id, cause);
  }
}
