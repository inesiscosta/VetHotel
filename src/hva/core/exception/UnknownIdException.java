package hva.core.exception;

/**
 * Class to represent an unknown id exception.
 */
public abstract class UnknownIdException extends Exception {

  private static final String ERROR_MESSAGE = "Unknown id: ";
  private final String _id;
  
  /**
   * Constructor for the exception.
   * 
   * @param specificMessage the exception description
   **/
  public UnknownIdException(String specificMessage, String id) {
    super(ERROR_MESSAGE + specificMessage);
    this._id = id;
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param specificMessage the exception description
   * @param cause exception that triggered this one
   **/

  public UnknownIdException(String specificMessage, String id, Exception cause) {
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
