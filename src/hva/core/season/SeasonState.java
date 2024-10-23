package hva.core.season;

import hva.core.Leaf;

/**
 * SeasonState
 * And all the effects that it causes 
 */
public interface SeasonState {
    SeasonState nextSeason();
    Season getEnum();
    int id();
    int seasonalEffortForEvergreen();
    int seasonalEffortForDeciduous();
    Leaf getBioCycleForEvergreen();
    Leaf getBioCycleForDeciduous();
}
