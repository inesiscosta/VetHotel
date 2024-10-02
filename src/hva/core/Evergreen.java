package hva.core;

public class Evergreen extends Tree{

    public Evergreen(String idTree, String name, int baseCleaningDifficulty, Season currentSeason){
        super(idTree, name, baseCleaningDifficulty, currentSeason);
    }
    
    protected int seasonalEffort(Season currentSeason){
        switch (currentSeason) {
            case SPRING:
            case SUMMER:
            case FALL:
                return 1;
            case WINTER:
                return 2;
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
                return Leaf.WITH_LEAVES;
            case WINTER:
                return Leaf.SHEDDING_LEAVES;
            default:
                return null;
        }
    }

    public String toString(Season currentSeason){
        return "ÁVORE | " + id() + " | " + name() + " | " + age() + " | " + getBaseCleaningDifficulty() + " | " + "PERENE" + " | " + getBioCycle(currentSeason);
    }
}
