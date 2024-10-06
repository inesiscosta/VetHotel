package hva.core.exception;

import java.io.Serial;

  /**
   * Class for representing an damage value
   **/
public class InvalidDamageValueException extends IllegalStateException{

    @Serial
    private static final long serialVersionUID = 202407081733L;

    private static final String ERROR_MESSAGE = "Invalid damage value: ";

    /**
     * @param message The error message
     **/
    public InvalidDamageValueException(int value) {
        super(ERROR_MESSAGE + value);
    }


}