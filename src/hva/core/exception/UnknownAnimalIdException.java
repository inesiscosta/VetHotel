package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an unknown Animal id exception.
 */
public class UnknownAnimalIdException extends UnknownIdException {

  @Serial
  private static final long serialVersionUID = 202410232254L;

  private static final String SPECIFIC_ERROR_MESSAGE = "Animal id";

  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownAnimalIdException(String id) {
    super(SPECIFIC_ERROR_MESSAGE + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that doesnt exist
   * @param cause The cause of the exception
   **/
  public UnknownAnimalIdException(String id, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + id, cause);
  }
}
