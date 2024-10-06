package hva.core.exception;

import java.io.Serial;

import hva.core.Season;

  /**
   * Class for representing an invalid season
   **/
public class InvalidSeasonException extends IllegalStateException{

    @Serial
    private static final long serialVersionUID = 202407081733L;

    private static final String ERROR_MESSAGE = "Invalid Season value: ";

    /**
     * @param message The error message
     **/
    public InvalidSeasonException(Season currentSeason) {
        super(ERROR_MESSAGE + currentSeason);
    }


}
