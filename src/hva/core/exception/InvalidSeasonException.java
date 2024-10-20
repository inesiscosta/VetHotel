package hva.core.exception;

import java.io.Serial;

import hva.core.Season;

/**
 * Class for representing an invalid season exception.
 **/
public class InvalidSeasonException extends IllegalStateException{

    @Serial
    private static final long serialVersionUID = 202407081733L;

    /**
     * Constructor for the exception.
     * 
     * @param currentSeason The invalid current season
     **/
    public InvalidSeasonException(Season currentSeason) {
        super("Invalid Season value:" + currentSeason);
    }
}
