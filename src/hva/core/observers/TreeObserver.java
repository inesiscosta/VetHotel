package hva.core.observers;

import java.io.Serializable;

import hva.core.season.Season;

/**
 * Interface for the Tree Observer.
 */
public interface TreeObserver extends Serializable{
  void updateAge();
  void advanceSeason(Season currentSeason);
}
    
