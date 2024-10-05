package hva.core;
import static java.lang.Math.log;

public abstract class Tree extends NamedEntity{
    private int _age;
    private int _baseCleaningDifficulty;
    private final TreeType _treeType;
    private final Season _creationSeason;

    public Tree(String id, String name, int age, int baseCleaningDifficulty, TreeType treeType , Season currentSeason) {
        super(name, id);
        _age = age;
        _baseCleaningDifficulty = baseCleaningDifficulty;
        _treeType = treeType;
        _creationSeason = currentSeason;
    }

    int age() {
        return _age;
    }

    int baseCleaningDifficulty() {
        return _baseCleaningDifficulty;
    }

    TreeType treeType() {
        return _treeType;
    }

    Season seasonAtCreation() {
        return _creationSeason;
    }

    public String toString(Season currentSeason){
        StringBuilder result = new StringBuilder();
        result.append("ÁRVORE | ")
            .append(id()).append(" | ")
            .append(name()).append(" | ")
            .append(age()).append(" | ")
            .append(baseCleaningDifficulty()).append(" | ")
            .append(treeType()).append(" | ")
            .append(getBioCycle(currentSeason));
        return result.toString();
    }

    double calculateCleaningEffort(Season currentSeason) {
        return _baseCleaningDifficulty * seasonalEffort(currentSeason) * log(age() + 1);
    }

    abstract int seasonalEffort(Season currentSeason);

    abstract Leaf getBioCycle(Season currentSeason);

    boolean equalsCreationSeason(Season currentSeason) {
        return _creationSeason == currentSeason;
    }
}
