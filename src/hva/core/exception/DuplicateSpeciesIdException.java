package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing a duplicate species id exception.
 */
public class DuplicateSpeciesIdException extends DuplicateIdException {

  @Serial
  private static final long serialVersionUID = 202410232241L;

  private static final String SPECIFIC_ERROR_MESSAGE = "Animal id: ";
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id that already exists
   **/
  public DuplicateSpeciesIdException(String id) {
    super(SPECIFIC_ERROR_MESSAGE + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateSpeciesIdException(String id, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + id, cause);
  }
}
