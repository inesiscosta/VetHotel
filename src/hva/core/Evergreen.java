package hva.core;

public class Evergreen extends Tree{

    public Evergreen(String idTree, String name, int baseCleaningDifficulty, Season currentSeason){
        super(idTree, name, baseCleaningDifficulty, currentSeason);
    }
    
    protected int seasonalEffort(Season currentSeason){
        // TODO Implement Evergreen.seasonalEffort
        return 0;
    }

    protected Leaf getBioCycle(Season currentSeason){
        // TODO Implement Evergreen.getBioCycle
        return null;
    }

    public String toString(){
        // TODO Implement Evergreen.toString
        return null;
    }
}
