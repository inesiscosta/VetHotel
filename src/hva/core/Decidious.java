package hva.core;

public class Decidious extends Tree {
    public Decidious(String idTree, String name, int baseCleaningDifficulty, Season currentSeason){
        super(idTree, name, baseCleaningDifficulty, currentSeason);
    }

    protected int seasonalEffort(Season currentSeason){
        // TODO Implement Decidious.seasonalEffort
        return 0;
    }

    protected Leaf getBioCycle(Season currentSeason){
        return null;
    }

    public String toString(){
        // TODO Implement Decidious.toString
        return null;
    }
}
