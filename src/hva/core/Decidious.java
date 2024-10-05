package hva.core;

public class Decidious extends Tree {
    public Decidious(String idTree, String name, int age, int baseCleaningDifficulty, Season currentSeason){
        super(idTree, name, age, baseCleaningDifficulty, currentSeason);
    }

    protected int seasonalEffort(Season currentSeason){
        switch (currentSeason) {
            case SPRING:
                return 1;
            case SUMMER:
                return 2;
            case FALL:
                return 5;
            case WINTER:
                return 0;
            default:
                return -1;
        }
    }

    protected Leaf getBioCycle(Season currentSeason){
        switch (currentSeason) {
            case SPRING:
                return Leaf.GENERATING_LEAVES;
            case SUMMER:
                return Leaf.WITH_LEAVES;
            case FALL:
                return Leaf.SHEDDING_LEAVES;
            case WINTER:
                return Leaf.WITHOUT_LEAVES;
            default:
                return null;
        }
    }

    public String toString(Season currentSeason){
        return "ÁVORE | " + this.id() + " | " + name() + " | " + age() + " | " + getBaseCleaningDifficulty() + " | " + "CADUCA" + " | " + getBioCycle(currentSeason);
    }
}
