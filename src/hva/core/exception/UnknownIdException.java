package hva.core.exception;

import java.io.Serial;

/**
 * Class to represent an unknown id exception.
 */
public abstract class UnknownIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202410232257L;
  
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
