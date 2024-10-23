package hva.core.season;

import hva.core.Leaf;

/**
 * Interface that defines the SeasonState and the effects that the
 * Seasons has in the trees.
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
