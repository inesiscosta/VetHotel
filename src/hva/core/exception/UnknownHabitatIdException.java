package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an unknown habitat id exception.
 */
public class UnknownHabitatIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownHabitatIdException(String id) {
    super("Habitat doesnt exist: " + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that doesnt exist
   * @param cause The cause of the exception
   **/
  public UnknownHabitatIdException(String id, Exception cause) {
    super("Habitat doesnt exist: " + id, cause);
  }
}
