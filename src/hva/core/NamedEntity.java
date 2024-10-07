package hva.core;

/**
 * Represents an entity with a name and an identifier. Inherited by most entities in the Vet Hotel.
 */
public class NamedEntity {
  private final String _id;
  private final String _name;

  /**
   * Creates a new NamedEntity.
   *
   * @param id the entity's unique identifier
   * @param name the entity's name
   */
  public NamedEntity(String id, String name) {
    _id = id;
    _name = name;
  }

  /**
   * Gets the entity's unique identifier.
   *
   * @return the entity's unique identifier
   */
  protected String id() {
    return _id;
  }

  /**
   * Gets the entity's name.
   *
   * @return the entity's name
   */
  protected String name() {
    return _name;
  }

  /**
   * Determine whether two namedEntity objects are equal. 
   * Two namedEntity objects are equal if their identifiers are equal.
   *
   * @return true if the two namedEntity objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof NamedEntity))
      return false;
    NamedEntity other = (NamedEntity) obj;
    return this._id.equals(other.id());
  }
    //Hashcode?
}
