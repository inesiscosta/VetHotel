package hva.core.exception;

/**
 * Class to represent an unknown id exception.
 */
public abstract class UnknownIdException extends Exception {

  private static final String ERROR_MESSAGE = "Unknown id: ";
  
    /**
     * Constructor for the exception.
     * 
     * @param specificMessage the exception description
     **/
    public UnknownIdException(String specificMessage) {
      super(ERROR_MESSAGE + specificMessage);
    }

    /**
     * Alternative constructor for the exception.
     * 
     * @param specificMessage the exception description
     * @param cause exception that triggered this one
     **/

    public UnknownIdException(String specificMessage, Exception cause) {
      super(ERROR_MESSAGE + specificMessage, cause);
    }
}
