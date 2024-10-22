package hva.core.observers;

import java.io.Serializable;

import hva.core.Season;

public interface TreeObserver extends Serializable{
    void updateAge();
    void advanceSeason(Season currentSeason);
}
    
