package hva.core.exception;

/**
 * Class for representing a duplicate vaccine id exception.
 */
public class DuplicateVaccineIdException extends DuplicateIdException {

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
