package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an invalid damage value exception.
 **/
public class InvalidDamageValueException extends IllegalStateException{

    @Serial
    private static final long serialVersionUID = 202407081733L;

    /**
     * @param message The error message
     **/
    public InvalidDamageValueException(int value) {
        super("Invalid Damage value" + value);
    }
}
