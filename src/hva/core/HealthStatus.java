package hva.core;

public enum HealthStatus {
    NORMAL,
    CONFUSION,
    ACCIDENT,
    ERROR;

    // Professor doesn't like static methods.
    protected static HealthStatus determineHealthStatus(int damage, boolean correctSpecies) {
        if (correctSpecies)
            return HealthStatus.NORMAL;
        if (damage == 0)
            return HealthStatus.CONFUSION;
        else if (damage >= 1 && damage <= 4)
            return HealthStatus.ACCIDENT;
        else if (damage >= 5)
            return HealthStatus.ERROR;
        throw new IllegalStateException("Invalid damage value"); // Check Exception
    }
}
