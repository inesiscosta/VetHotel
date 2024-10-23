package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an unknown species id exception.
 */
public class UnknownSpeciesIdException extends UnknownIdException {

  @Serial
  private static final long serialVersionUID = 202410232259L;

  private static final String SPECIFIC_ERROR_MESSAGE = "Species id";

  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownSpeciesIdException(String id) {
    super(SPECIFIC_ERROR_MESSAGE + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that doesnt exist
   * @param cause The cause of the exception
   **/
  public UnknownSpeciesIdException(String id, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + id, cause);
  }
}
