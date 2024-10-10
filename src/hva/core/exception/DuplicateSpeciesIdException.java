package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an Duplicated Id entry.
 */
public class DuplicateSpeciesIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
    /**
   * @param message the id that already exists
   **/
  public DuplicateSpeciesIdException(String id) {
    super("Duplicated Species id: " + id);
  }

  /**
   * @param message the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateSpeciesIdException(String id, Exception cause) {
    super("Duplicated Species id: " + id, cause);
  }
}
