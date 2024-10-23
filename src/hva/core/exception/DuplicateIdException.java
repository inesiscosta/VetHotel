package hva.core.exception;

import java.io.Serial;

/**
 * Class to represent a duplicate id exception.
 */
public abstract class DuplicateIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202410232240L;;
  
  private static final String ERROR_MESSAGE = "Duplicate id: ";
  
    /**
     * Constructor for the exception.
     * 
     * @param specificMessage the exception description
     **/
    public DuplicateIdException(String specificMessage) {
      super(ERROR_MESSAGE + specificMessage);
    }

    /**
     * Alternative constructor for the exception.
     * 
     * @param specificMessage the exception description
     * @param cause exception that triggered this one
     **/

    public DuplicateIdException(String specificMessage, Exception cause) {
      super(ERROR_MESSAGE + specificMessage, cause);
    }
}
