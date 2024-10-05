package hva.core;

public class Deciduous extends Tree {
    public Deciduous(String idTree, String name, int age, int baseCleaningDifficulty, Season currentSeason){
        super(idTree, name, age, baseCleaningDifficulty, TreeType.DECIDUOUS, currentSeason);
    }

    @Override
    protected int seasonalEffort(Season currentSeason) throws IllegalStateException{
        switch (currentSeason) {
            case Spring:
                return 1;
            case Summer:
                return 2;
            case Fall:
                return 5;
            case Winter:
                return 0;
            default:
                throw new IllegalStateException("Unexpected value: " + currentSeason); // Check Exception
        }
    }

    @Override
    protected Leaf getBioCycle(Season currentSeason) throws IllegalStateException{
        switch (currentSeason) {
            case Spring:
                return Leaf.GENERATING_LEAVES;
            case Summer:
                return Leaf.WITH_LEAVES;
            case Fall:
                return Leaf.SHEDDING_LEAVES;
            case Winter:
                return Leaf.WITHOUT_LEAVES;
            default:
                throw new IllegalStateException("Unexpected value: " + currentSeason); // Check Exception
        }
    }
}
