package hva.core;

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
   * @param id the Influence int id
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
    for(Influence influence : Influence.values())
        if(influence.toString().equals(influenceString))
            return influence;
    return null;
  }
}

