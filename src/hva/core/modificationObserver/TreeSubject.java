package hva.core.modificationObserver;

import java.io.Serializable;

import hva.core.Season;

public interface TreeSubject extends Serializable{
    void addTreeObserver(TreeObserver observer);
    void removeTreeObserver(TreeObserver observer);
    void notifyTreeObservers(Season currentSeason);
}
