package hva.core;

public abstract class Tree {
    private String _idTree;
    private String _name;
    private int _age;
    private int _baseCleaningDifficulty;
    private Season _seasonAtCreation;

    public Tree(String idTree, String name, int baseCleaningDifficulty, Season currentSeason) {
        _idTree = idTree;
        _name = name;
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
