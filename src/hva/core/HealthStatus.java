package hva.core;

public enum HealthStatus {
    NORMAL,
    CONFUSION,
    ACCIDENT,
    ERROR;

    protected HealthStatus determineHealthStatus(int damage, boolean correctSpecies) {
        if (damage == 0) {
            if (correctSpecies) {
                return NORMAL;
            }
            return CONFUSION;
        } else if (1 <= damage && damage <= 4) {
            return ACCIDENT;
        } else {
            return ERROR;
        }
    }
}