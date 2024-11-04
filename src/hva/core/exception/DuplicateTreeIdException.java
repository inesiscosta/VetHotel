package hva.core.exception;

/**
 * Class for representing a duplicate tree id exception.
 */
public class DuplicateTreeIdException extends DuplicateIdException {

  private static final String SPECIFIC_ERROR_MESSAGE = "Tree id: ";

  /**
   * Constructor for the exception.
   * 
   * @param id the id that already exists
   **/
  public DuplicateTreeIdException(String id) {
    super(SPECIFIC_ERROR_MESSAGE + id, id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateTreeIdException(String id, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + id, id, cause);
  }
}
