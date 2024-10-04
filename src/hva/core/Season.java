package hva.core;

public enum Season {
    SPRING,
    SUMMER,
    FALL,
    WINTER;

    protected static Season getSeason(int id) {
        switch (id) {
            case 0:
                return SPRING;
            case 1:
                return SUMMER;
            case 2:
                return FALL;
            case 3:
                return WINTER;
            default:
                return SPRING;
        }
    }

    protected static int getSeason(Season season) {
        switch (season) {
            case SPRING:
                return 0;
            case SUMMER:
                return 1;
            case FALL:
                return 2;
            case WINTER:
                return 3;
            default:
                return 0;
        }
    }
}
