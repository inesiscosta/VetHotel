package hva.core.exception;

/**
 * Class to represent a duplicate id exception.
 */
public abstract class DuplicateIdException extends Exception {
  
  private static final String ERROR_MESSAGE = "Duplicate id: ";
  private final String _id;
  
  /**
   * Constructor for the exception.
   * 
   * @param specificMessage the exception description
   **/
  public DuplicateIdException(String specificMessage, String id) {
    super(ERROR_MESSAGE + specificMessage);
    this._id = id;
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param specificMessage the exception description
   * @param cause exception that triggered this one
   **/

  public DuplicateIdException(String specificMessage, String id, Exception cause) {
    super(ERROR_MESSAGE + specificMessage, cause);
    this._id = id;
  }

  /**
   * Gets the duplicated id.
   * 
   * @return the duplicated id
   */
  public String id() {
    return _id;
  }
}
