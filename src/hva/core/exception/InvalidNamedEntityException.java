package hva.core.exception;

import java.io.Serial;

  /**
   * Class for representing an invalid named entity exception.
   **/
public class InvalidNamedEntityException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id that is invalid
   **/
  public InvalidNamedEntityException(String id) {
    super("Invalid Named Entity: " + id);
  }
}
