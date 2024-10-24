package hva.core.observers;

import hva.core.season.Season;

import java.io.Serializable;

/**
 * Interface for the Tree Subject.
 */
public interface TreeSubject extends Serializable{
  void addTreeObserver(TreeObserver observer);
  void removeTreeObserver(TreeObserver observer);
  void notifyTreeObservers(Season currentSeason);
}
