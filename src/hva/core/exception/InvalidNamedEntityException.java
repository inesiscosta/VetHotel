package hva.core.exception;

  /**
   * Class for representing an invalid named entity exception.
   **/
public class InvalidNamedEntityException extends Exception {

  /**
   * Constructor for the exception.
   * 
   * @param id the id that is invalid
   **/
  public InvalidNamedEntityException(String id) {
    super("Invalid Named Entity: " + id);
  }
}
