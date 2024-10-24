package hva.core.exception;

/**
 * Class for representing a duplicate employee id exception.
 */
public class DuplicateEmployeeIdException extends DuplicateIdException {

  private static final String SPECIFIC_ERROR_MESSAGE = "Employee id: ";
  
  /**
   * Constructor for the exception.
   * 
   * @param message the id that already exists
   **/
  public DuplicateEmployeeIdException(String id) {
    super(SPECIFIC_ERROR_MESSAGE + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param message the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateEmployeeIdException(String id, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + id, cause);
  }
}
