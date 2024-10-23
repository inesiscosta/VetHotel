package hva.core.season;

import hva.core.Leaf;

public class Spring implements SeasonState{
    private final int _id = 0;

    @Override
    public SeasonState nextSeason() {
        return new Summer();
    }

    @Override
    public int id() {
        return _id;
    }

    @Override
    public Season getEnum() {
        return Season.Spring;
    }

    public Leaf getBioCycleForDeciduous() {
        return Leaf.GENERATING_LEAVES;
    }

    public int seasonalEffortForDeciduous() {
        return 1;
    }

    public Leaf getBioCycleForEvergreen() {
        return Leaf.GENERATING_LEAVES;
    }

    public int seasonalEffortForEvergreen() {
        return 1;
    }
}
