package hva.core;

public abstract class Tree extends NamedEntity{
    private int _age;
    private int _baseCleaningDifficulty;
    private Season _seasonAtCreation;

    public Tree(String idTree, String name, int baseCleaningDifficulty, Season currentSeason) {
        super(name, idTree);
        _baseCleaningDifficulty = baseCleaningDifficulty;
        _seasonAtCreation = currentSeason;
    }

    public float calculateCleaningEffort() {
        // TODO Implement Tree.calculateCleaningEffort
        return 0;
    }

    protected abstract int seasonalEffort(Season currentSeason);

    public boolean equalsSeasonAtCreation(Season currentSeason) {
        return _seasonAtCreation == currentSeason;
    }

    public abstract String toString();

    protected abstract Leaf getBioCycle(Season currentSeason);
}
