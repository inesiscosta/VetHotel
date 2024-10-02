package hva.core;
import static java.lang.Math.log;

public abstract class Tree extends NamedEntity{
    private int _age;
    private int _baseCleaningDifficulty;
    private Season _seasonAtCreation;

    public Tree(String idTree, String name, int baseCleaningDifficulty, Season currentSeason) {
        super(name, idTree);
        _baseCleaningDifficulty = baseCleaningDifficulty;
        _seasonAtCreation = currentSeason;
    }

    public int age() {
        return _age;
    }

    public int getBaseCleaningDifficulty() {
        return _baseCleaningDifficulty;
    }

    public Season getSeasonAtCreation() {
        return _seasonAtCreation;
    }

    public double calculateCleaningEffort() {
        return _baseCleaningDifficulty * seasonalEffort(_seasonAtCreation) * log(age() + 1);
    }

    protected abstract int seasonalEffort(Season currentSeason);

    public boolean equalsSeasonAtCreation(Season currentSeason) {
        return _seasonAtCreation == currentSeason;
    }

    public abstract String toString(Season currentSeason);

    protected abstract Leaf getBioCycle(Season currentSeason);
}
