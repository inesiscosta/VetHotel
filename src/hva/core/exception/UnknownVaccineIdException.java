package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an unknown vaccine id exception.
 */
public class UnknownVaccineIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownVaccineIdException(String id) {
    super("Vaccine doesnt exist: " + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that doesnt exist
   * @param cause The cause of the exception
   **/
  public UnknownVaccineIdException(String id, Exception cause) {
    super("Vaccine doesnt exist: " + id, cause);
  }
}
