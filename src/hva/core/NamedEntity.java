package hva.core;

import java.io.Serial;
import java.io.Serializable;


/**
 * Represents an entity with a name and an identifier.
 * Inherited by most entities in the Vet Hotel.
 */
public abstract class NamedEntity implements
Comparable<NamedEntity>, Serializable {

  @Serial
  private static final long serialVersionUID = 202410242348L;

  private final String _id;
  private final String _name;

  /**
   * Creates a new NamedEntity.
   *
   * @param id the entity's unique identifier
   * @param name the entity's name
   * @throws NullEntryException if id or name is null
   */
  NamedEntity(String id, String name){
    _id = id;
    _name = name;
  }

  /**
   * Gets the entity's unique identifier.
   *
   * @return the entity's unique identifier
   */
  public String id() {
    return _id;
  }

  /**
   * Gets the entity's name.
   *
   * @return the entity's name
   */
  String name() {
    return _name;
  }

  /**
   * Compares this entity to another entity by their IDs, ignoring case.
   *
   * @param other the other entity to compare to
   * @return a negative integer, zero, or positive integer as this entity's id
   *         is less than, equal to, or greater than the other entity's ID.
   */
  @Override
  public int compareTo(NamedEntity other) {
    return this._id.compareToIgnoreCase(other.id());
  }

  /**
   * Determine whether two namedEntity objects are equal. 
   * Two namedEntity objects are equal if their identifiers are equal.
   *
   * @return true if the two namedEntity objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof NamedEntity))
      return false;
    NamedEntity other = (NamedEntity) obj;
    return this._id.equalsIgnoreCase(other.id());
  }

  /**
   * Returns a hash code value for a named entity object.
   *
   * @return a hash code value for this object.
   */
  /*
  @Override
  public int hashCode() {
    return _id.toLowerCase().hashCode();
  } 
  */
}
