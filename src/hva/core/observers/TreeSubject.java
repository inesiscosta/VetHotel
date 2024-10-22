package hva.core.observers;

import java.io.Serializable;

import hva.core.Season;

/**
 * Interface for the Tree Subject.
 */
public interface TreeSubject extends Serializable{
    void addTreeObserver(TreeObserver observer);
    void removeTreeObserver(TreeObserver observer);
    void notifyTreeObservers(Season currentSeason);
}
