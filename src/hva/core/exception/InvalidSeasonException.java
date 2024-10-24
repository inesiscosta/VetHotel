package hva.core.exception;

import hva.core.season.Season;

/**
 * Class for representing an invalid season exception.
 **/
public class InvalidSeasonException extends IllegalStateException{

    /**
     * Constructor for the exception.
     * 
     * @param currentSeason The invalid current season
     **/
    public InvalidSeasonException(Season currentSeason) {
        super("Invalid Season value:" + currentSeason);
    }
}
