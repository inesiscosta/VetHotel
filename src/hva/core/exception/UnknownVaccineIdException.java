package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an invalid ID entry.
 */
public class UnknownVaccineIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param message the error message
   **/
  public UnknownVaccineIdException(String id) {
    super("Vaccine doesnt exist: " + id);
  }

    /**
   * @param message the error message
   * @param cause The cause of the exception
   **/
  public UnknownVaccineIdException(String id, Exception cause) {
    super("Vaccine doesnt exist: " + id, cause);
  }
}
