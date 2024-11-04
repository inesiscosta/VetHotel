package hva.core.exception;

/**
 * Class for representing a duplicate animal id exception.
 */
public class DuplicateAnimalIdException extends DuplicateIdException {

  private static final String SPECIFIC_ERROR_MESSAGE = "Animal id: ";
  
  /** 
   * Constructor for the exception.
   * 
   * @param id the id that already exists
   **/
  public DuplicateAnimalIdException(String id) {
    super(SPECIFIC_ERROR_MESSAGE + id, id);
  }

  /** 
   * Alternative constructor for the exception.
   * 
   * @param id the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateAnimalIdException(String id, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + id, id, cause);
  }
}
