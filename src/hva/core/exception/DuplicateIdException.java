package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public abstract class DuplicateIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
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
