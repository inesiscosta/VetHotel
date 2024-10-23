package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an unknown Employee id exception.
 */
public class UnknownEmployeeIdException extends UnknownIdException {

  @Serial
  private static final long serialVersionUID = 202410232255L;

  private static final String SPECIFIC_ERROR_MESSAGE = "Employee id";

  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownEmployeeIdException(String id) {
    super(SPECIFIC_ERROR_MESSAGE + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that doesnt exist
   * @param cause The cause of the exception
   **/
  public UnknownEmployeeIdException(String id, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + id, cause);
  }
}
