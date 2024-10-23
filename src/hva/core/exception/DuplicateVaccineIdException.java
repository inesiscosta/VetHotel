package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing a duplicate vaccine id exception.
 */
public class DuplicateVaccineIdException extends DuplicateIdException {

  @Serial
  private static final long serialVersionUID = 202410232244L;

  private static final String SPECIFIC_ERROR_MESSAGE = "Vaccine id: ";
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id that already exists
   **/
  public DuplicateVaccineIdException(String id) {
    super(SPECIFIC_ERROR_MESSAGE + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateVaccineIdException(String id, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + id, cause);
  }
}
