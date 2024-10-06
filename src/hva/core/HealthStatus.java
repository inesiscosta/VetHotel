package hva.core;
import hva.core.exception.InvalidDamageValueException;

public enum HealthStatus {
    NORMAL,
    CONFUSION,
    ACCIDENT,
    ERROR;

    protected static HealthStatus determineHealthStatus(int damage, boolean correctSpecies) throws InvalidDamageValueException{
        if (correctSpecies)
            return HealthStatus.NORMAL;
        if (damage == 0)
            return HealthStatus.CONFUSION;
        else if (damage >= 1 && damage <= 4)
            return HealthStatus.ACCIDENT;
        else if (damage >= 5)
            return HealthStatus.ERROR;
        throw new InvalidDamageValueException(damage);
    }
}
