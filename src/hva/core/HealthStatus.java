package hva.core;
import hva.core.exception.InvalidDamageValueException;

/**
 * Enumerates the possible health status of an animal / effects of a vaccine on an animal.
 */
public enum HealthStatus {
  NORMAL,
  CONFUSION,
  ACCIDENT,
  ERROR;
  
  /**
   * Determines the health status of an animal based on the damage and if the species is correct.
   * 
   * @param damage the damage suffered by the animal as the result of a vaccine
   * @param correctSpecies a boolean indicating whether the species is correct
   * @return the animal's health status (effect on animal's health) as a result of the vaccine
   * @throws InvalidDamageValueException if the damage is invalid (less than 0)
   */
  protected static HealthStatus determineHealthStatus(int damage, boolean correctSpecies) throws InvalidDamageValueException{
    if (correctSpecies)
      return HealthStatus.NORMAL;
    else if (damage == 0)
      return HealthStatus.CONFUSION;
    else if (damage >= 1 && damage <= 4)
      return HealthStatus.ACCIDENT;
    else if (damage >= 5)
      return HealthStatus.ERROR;
    throw new InvalidDamageValueException(damage);
    }
}
