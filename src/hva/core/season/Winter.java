package hva.core.season;

import hva.core.Leaf;

public class Winter implements SeasonState {
    private final int _id = 3;

    @Override
    public SeasonState nextSeason() {
        return new Spring();
    }

    @Override
    public int id() {
        return _id;
    }

    @Override
    public Season getEnum() {
        return Season.Winter;
    }

    public Leaf getBioCycleForDeciduous() {
        return Leaf.WITHOUT_LEAVES;
    }

    public int seasonalEffortForDeciduous() {
        return 0;
    }

    public Leaf getBioCycleForEvergreen() {
        return Leaf.SHEDDING_LEAVES;
    }

    public int seasonalEffortForEvergreen() {
        return 2;
    }
}
