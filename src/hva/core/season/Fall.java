package hva.core.season;

import hva.core.Leaf;

public class Fall implements SeasonState {
    private final int _id = 2;

    @Override
    public SeasonState nextSeason() {
        return new Winter();
    }

    @Override
    public int id() {
        return _id;
    }
    
    @Override
    public Season getEnum() {
        return Season.Fall;
    }

    public Leaf getBioCycleForDeciduous() {
        return Leaf.SHEDDING_LEAVES;
    }

    public int seasonalEffortForDeciduous() {
        return 5;
    }

    public Leaf getBioCycleForEvergreen() {
        return Leaf.WITH_LEAVES;
    }

    public int seasonalEffortForEvergreen() {
        return 1;
    }
}
