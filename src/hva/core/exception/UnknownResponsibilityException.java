package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an unknown responsibility exception.
 */
public class UnknownResponsibilityException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownResponsibilityException(String id) {
    super("Responsibility doesnt exist:" + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that doesnt exist
   * @param cause The cause of the exception
   **/
  public UnknownResponsibilityException(String id, Exception cause) {
    super("Responsibility doesnt exist:" + id, cause);
  }
}
