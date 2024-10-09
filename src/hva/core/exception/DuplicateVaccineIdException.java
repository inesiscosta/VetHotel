package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an Duplicated Id entry.
 */
public class DuplicateVaccineIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
    /**
   * @param message the id that already exists
   **/
  public DuplicateVaccineIdException(String id) {
    super("Duplicated Vaccine Id: " + id);
  }

  /**
   * @param message the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateVaccineIdException(String id, Exception cause) {
    super("Duplicated Vaccine Id: " + id, cause);
  }
}
