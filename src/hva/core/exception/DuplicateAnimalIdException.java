package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing a duplicate animal id exception.
 */
public class DuplicateAnimalIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /** 
   * Constructor for the exception.
   * 
   * @param id the id that already exists
   **/
  public DuplicateAnimalIdException(String id) {
    super("Duplicated Animal id: " + id);
  }

  /** 
   * Alternative constructor for the exception.
   * 
   * @param id the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateAnimalIdException(String id, Exception cause) {
    super("Duplicated Animal id: " + id, cause);
  }
}
