package hva.core;

import hva.core.exception.InvalidDamageValueException;

import java.io.Serial;

/**
 * Enumerates the possible effects of a vaccine on an animal.
 */
enum HealthStatus {
  NORMAL("NORMAL"),
  CONFUSION("CONFUSION"),
  ACCIDENT("ACCIDENT"),
  ERROR("ERROR");
  
  @Serial
  private static final long serialVersionUID = 202410242358L;

  private final String _string;

  /**
   * Creates a new HealthStatus.
   * 
   * @param HealthStatus the HealthStatus type String representation.
   */
  HealthStatus (String HealthStatus) {
    this._string = HealthStatus;
  }

  /**
   * String representation of the health status in Portuguese.
   * 
   * @return the health status in Portuguese
   */
  @Override
  public String toString() {
    return _string;
  }

  /**
   * Determines the health status of an animal based on the damage incurred
   * by the vaccine and whether the species is correct.
   * 
   * @param damage the damage suffered by the animal as the result of a vaccine
   * @param correctSpecies a boolean indicating whether the species is correct
   * @return the effect on an animal's health as a result of the vaccine
   * @throws InvalidDamageValueException if the damage is invalid
   * (if the damage is less than 0)
   */
  static HealthStatus determineHealthStatus(int damage, 
  boolean correctSpecies) throws InvalidDamageValueException {
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
