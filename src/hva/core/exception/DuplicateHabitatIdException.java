package hva.core.exception;


/**
 * Class for representing a duplicate habitat id exception.
 */
public class DuplicateHabitatIdException extends DuplicateIdException {

  private static final String SPECIFIC_ERROR_MESSAGE = "Habitat id: ";

  /**
   * Constructor for the exception.
   * 
   * @param id the id that already exists
   **/
  public DuplicateHabitatIdException(String id) {
    super(SPECIFIC_ERROR_MESSAGE + id, id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateHabitatIdException(String id, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + id, id, cause);
  }
}
