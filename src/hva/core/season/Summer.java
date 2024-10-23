package hva.core.season;

import hva.core.Leaf;

public class Summer implements SeasonState {
    private final int _id = 1;

    @Override
    public SeasonState nextSeason() {
        return new Fall();
    }

    @Override
    public int id() {
        return _id;
    }

    @Override
    public Season getEnum() {
        return Season.Summer;
    }

    public Leaf getBioCycleForDeciduous() {
        return Leaf.WITH_LEAVES;
    }

    public int seasonalEffortForDeciduous() {
        return 2;
    }

    public Leaf getBioCycleForEvergreen() {
        return Leaf.WITH_LEAVES;
    }

    public int seasonalEffortForEvergreen() {
        return 1;
    }
}
