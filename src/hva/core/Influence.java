package hva.core;

import java.util.Arrays;

/**
 * Enumerates the influences of an habitat in a animal.
 */
enum Influence {
  POS(20),
  NEG(-20),
  NEU(0);

  private final int _value;

  /**
   * Creates a new Influence.
   * 
   * @param value the Influence value
  */
  Influence(int value) {
    this._value = value;
  }

  /**
   * Gets the Influence value.
   * 
   * @return the Influence int value
   */
  int value() {
    return _value;
  }

  /**
   * Converts a string to an Influence.
   * 
   * @param influenceString the string to convert into Influence
   * @return the Influence corresponding to the string
   */
  static Influence stringToEnum(String influenceString) {
    return Arrays.stream(Influence.values())
    .filter(influence -> influence.toString().equals(influenceString))
    .findFirst().orElse(null);
  }
}

