package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing a duplicate tree id exception.
 */
public class DuplicateTreeIdException extends DuplicateIdException {

  @Serial
  private static final long serialVersionUID = 202410232243L;

  private static final String SPECIFIC_ERROR_MESSAGE = "Tree id: ";

  /**
   * Constructor for the exception.
   * 
   * @param id the id that already exists
   **/
  public DuplicateTreeIdException(String id) {
    super(SPECIFIC_ERROR_MESSAGE + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateTreeIdException(String id, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + id, cause);
  }
}
