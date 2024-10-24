package hva.core.exception;

/**
 * Class for representing an invalid damage value exception.
 **/
public class InvalidDamageValueException extends IllegalStateException{

  /**
   * @param value The invalid damage value
   **/
  public InvalidDamageValueException(int value) {
    super("Invalid Damage value" + value);
  }
}
