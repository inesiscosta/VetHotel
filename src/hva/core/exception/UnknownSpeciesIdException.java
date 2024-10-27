package hva.core.exception;

/**
 * Class for representing an unknown species id exception.
 */
public class UnknownSpeciesIdException extends Exception {

  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownSpeciesIdException(String id) {
    super(id);
  }
}
