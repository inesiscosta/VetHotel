package hva.core.exception;

/**
 * Class for representing an unknown vaccine id exception.
 */
public class UnknownVaccineIdException extends UnknownIdException {

  private static final String SPECIFIC_ERROR_MESSAGE = "Vaccine id";

  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownVaccineIdException(String id) {
    super(SPECIFIC_ERROR_MESSAGE + id, id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that doesnt exist
   * @param cause The cause of the exception
   **/
  public UnknownVaccineIdException(String id, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + id, id, cause);
  }
}
