package hva.core.exception;

import java.io.Serial;

  /**
   * Class for representing an invalid type, tree or employee.
   **/
public class InvalidNamedEntityException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param message The error message
   **/
  public InvalidNamedEntityException(String id) {
    super("Invalid Named Entity: " + id);
  }
}