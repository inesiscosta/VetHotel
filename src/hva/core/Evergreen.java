package hva.core;

public class Evergreen extends Tree{

    public Evergreen(String idTree, String name, int age, int baseCleaningDifficulty, Season currentSeason){
        super(idTree, name, age, baseCleaningDifficulty, TreeType.EVERGREEN, currentSeason);
    }
    
    @Override
    int seasonalEffort(Season currentSeason) throws IllegalStateException{
        switch (currentSeason) {
            case Spring:
            case Summer:
            case Fall:
                return 1;
            case Winter:
                return 2;
            default:
                throw new IllegalStateException("Unexpected value: " + currentSeason); // Check Exception
        }
    }

    @Override
    Leaf getBioCycle(Season currentSeason) throws IllegalStateException{
        switch (currentSeason) {
            case Spring:
                return Leaf.GENERATING_LEAVES;
            case Summer:
                return Leaf.WITH_LEAVES;
            case Fall:
                return Leaf.WITH_LEAVES;
            case Winter:
                return Leaf.SHEDDING_LEAVES;
            default:
                throw new IllegalStateException("Unexpected value: " + currentSeason); // Check Exception
        }
    }
}
