package hva.core;

public class Deciduous extends Tree {
    public Deciduous(String idTree, String name, int age, int baseCleaningDifficulty, Season currentSeason){
        super(idTree, name, age, baseCleaningDifficulty, TreeType.DECIDUOUS, currentSeason);
    }

    protected int seasonalEffort(Season currentSeason){
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
                return Leaf.SHEDDING_LEAVES;
            case Winter:
                return Leaf.WITHOUT_LEAVES;
            default:
                return null;
        }
    }

    protected String getTreeTypeInPT(){
        return "CADUCA";
    }

    protected String getBioCycleInPT(Season currentSeason){
        switch (currentSeason) {
            case Spring:
                return "GERARFOLHAS";
            case Summer:
                return "COMFOLHAS";
            case Fall:
                return "PERDERFOLHAS";
            case Winter:
                return "SEMFOLHAS";
            default:
                return null;
        }
    }
}
