package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an unknown vaccine id exception.
 */
public class UnknownVaccineIdException extends UnknownIdException {

  @Serial
  private static final long serialVersionUID = 202410232300L;

  private static final String SPECIFIC_ERROR_MESSAGE = "Vaccine id";

  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownVaccineIdException(String id) {
    super(SPECIFIC_ERROR_MESSAGE + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that doesnt exist
   * @param cause The cause of the exception
   **/
  public UnknownVaccineIdException(String id, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + id, cause);
  }
}
