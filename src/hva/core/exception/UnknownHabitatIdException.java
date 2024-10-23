package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an unknown Habitat id exception.
 */
public class UnknownHabitatIdException extends UnknownIdException {

  @Serial
  private static final long serialVersionUID = 202410232256L;
  
  private static final String SPECIFIC_ERROR_MESSAGE = "Habitat id";

  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownHabitatIdException(String id) {
    super(SPECIFIC_ERROR_MESSAGE + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that doesnt exist
   * @param cause The cause of the exception
   **/
  public UnknownHabitatIdException(String id, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + id, cause);
  }
}
