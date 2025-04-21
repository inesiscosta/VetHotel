package hva.core.observers;

import hva.core.season.Season;

import java.io.Serializable;

/**
 * Interface for the Tree Observer.
 */
public interface TreeObserver extends Serializable{
  void updateAge();
  void advanceSeason(Season currentSeason);
}
