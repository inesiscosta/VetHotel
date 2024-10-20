package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an unknown species id exception.
 */
public class UnknownSpeciesIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownSpeciesIdException(String id) {
    super("Species doesnt exist: " + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that doesnt exist
   * @param cause The cause of the exception
   **/
  public UnknownSpeciesIdException(String id, Exception cause) {
    super("Species doesnt exist: " + id, cause);
  }
}
