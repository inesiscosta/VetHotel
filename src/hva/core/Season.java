package hva.core;

public enum Season {
    Spring,
    Summer,
    Fall,
    Winter;

    protected static Season getSeason(int id) throws IllegalStateException {
        switch (id) {
            case 0:
                return Spring;
            case 1:
                return Summer;
            case 2:
                return Fall;
            case 3:
                return Winter;
            default:
                throw new IllegalStateException("Unexpected value: " + id); // Check Exception
        }
    }

    protected static int getSeason(Season season) throws IllegalStateException{
        switch (season) {
            case Spring:
                return 0;
            case Summer:
                return 1;
            case Fall:
                return 2;
            case Winter:
                return 3;
            default:
                throw new IllegalStateException("Unexpected value: " + season); // Check Exception
        }
    }

    public Season nextSeason() throws IllegalStateException {
        switch (this) {
            case Spring:
                return Season.Summer;
            case Summer:
                return Season.Fall;
            case Fall:
                return Season.Winter;
            case Winter:
                return Season.Spring;
            default:
                throw new IllegalStateException("Unexpected value: " + this); // Check Exception
        }
    }
}
