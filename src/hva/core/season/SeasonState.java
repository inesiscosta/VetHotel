package hva.core.season;

import hva.core.Leaf;

import java.io.Serializable;

/**
 * Interface that defines the SeasonState and the effects that the
 * Seasons has in the trees.
 */
public interface SeasonState extends Serializable{
  SeasonState nextSeason();
  Season getEnum();
  int id();
  int seasonalEffortForEvergreen();
  int seasonalEffortForDeciduous();
  Leaf getBioCycleForEvergreen();
  Leaf getBioCycleForDeciduous();
}
