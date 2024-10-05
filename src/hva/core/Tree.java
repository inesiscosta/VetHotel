package hva.core;
import static java.lang.Math.log;

public abstract class Tree extends NamedEntity{
    private int _age;
    private int _baseCleaningDifficulty;
    private final TreeType _treeType;
    private final Season _seasonAtCreation;

    public Tree(String id, String name, int age, int baseCleaningDifficulty, TreeType treeType , Season currentSeason) {
        super(name, id);
        _age = age;
        _baseCleaningDifficulty = baseCleaningDifficulty;
        _treeType = treeType;
        _seasonAtCreation = currentSeason;
    }

    public int age() {
        return _age;
    }

    public int getBaseCleaningDifficulty() {
        return _baseCleaningDifficulty;
    }

    public TreeType getTreeType() {
        return _treeType;
    }

    public Season getSeasonAtCreation() {
        return _seasonAtCreation;
    }

    public double calculateCleaningEffort(Season currentSeason) {
        return _baseCleaningDifficulty * seasonalEffort(currentSeason) * log(age() + 1);
    }

    protected abstract int seasonalEffort(Season currentSeason);

    public boolean equalsSeasonAtCreation(Season currentSeason) {
        return _seasonAtCreation == currentSeason;
    }

    protected abstract Leaf getBioCycle(Season currentSeason);

    public String toString(Season currentSeason){
        return "ÁRVORE | " + id() + " | " + name() + " | " + age() + " | " + getBaseCleaningDifficulty() + " | " + getTreeTypeInPT() + " | " + getBioCycleInPT(currentSeason);
    }

    protected abstract String getTreeTypeInPT();

    protected abstract String getBioCycleInPT(Season currentSeason);
}
