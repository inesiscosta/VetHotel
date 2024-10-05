package hva.core;

public class Evergreen extends Tree{

    public Evergreen(String idTree, String name, int age, int baseCleaningDifficulty, Season currentSeason){
        super(idTree, name, age, baseCleaningDifficulty, TreeType.EVERGREEN, currentSeason);
    }
    
    protected int seasonalEffort(Season currentSeason){
        switch (currentSeason) {
            case Spring:
            case Summer:
            case Fall:
                return 1;
            case Winter:
                return 2;
            default:
                return -1;
        }
    }

    protected Leaf getBioCycle(Season currentSeason){
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
                return null;
        }
    }
    protected String getTreeTypeInPT(){
        return "PERENE";
    }

    protected String getBioCycleInPT(Season currentSeason) throws IllegalStateException{
        switch (currentSeason) {
            case Spring:
                return "GERARFOLHAS";
            case Summer:
                return "COMFOLHAS";
            case Fall:
                return "COMFOLHAS";
            case Winter:
                return "PERDERFOLHAS";
            default:
                throw new IllegalStateException("Unexpected value: " + currentSeason); // Check Exception
        }
    }
}
