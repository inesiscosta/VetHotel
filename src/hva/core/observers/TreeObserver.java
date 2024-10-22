package hva.core.observers;

import java.io.Serializable;

import hva.core.Season;

/**
 * Interface for the Tree Observer.
 */
public interface TreeObserver extends Serializable{
    void updateAge();
    void advanceSeason(Season currentSeason);
}
    
