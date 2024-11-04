package hva.core.exception;

/**
 * Class for representing an unknown responsibility id exception.
 */
public class UnknownResponsibilityIdException extends UnknownIdException {

  private static final String SPECIFIC_ERROR_MESSAGE = "Reponsibility id";

  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownResponsibilityIdException(String id) {
    super(SPECIFIC_ERROR_MESSAGE + id, id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that doesnt exist
   * @param cause The cause of the exception
   **/
  public UnknownResponsibilityIdException(String id, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + id, id, cause);
  }
}
