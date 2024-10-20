package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing a duplicate vaccine id exception.
 */
public class DuplicateVaccineIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id that already exists
   **/
  public DuplicateVaccineIdException(String id) {
    super("Duplicated Vaccine id: " + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateVaccineIdException(String id, Exception cause) {
    super("Duplicated Vaccine id: " + id, cause);
  }
}
