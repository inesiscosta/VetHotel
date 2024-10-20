package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an unknown animal id exception.
 */
public class UnknownAnimalIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownAnimalIdException(String id) {
    super("Animal doesnt exist: " + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that doesnt exist
   * @param cause The cause of the exception
   **/
  public UnknownAnimalIdException(String id, Exception cause) {
    super("Animal doesnt exist: " + id, cause);
  }
}
