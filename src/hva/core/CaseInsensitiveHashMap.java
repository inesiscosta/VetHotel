package hva.core;

import java.io.Serial;
import java.util.HashMap;

/**
 * Custom HashMap override that treats all the id strings as case insensitive
 * it does so by forcing all ids to be lowercase before calling the super
 * methods provided by the HashMap class.
 */
public class CaseInsensitiveHashMap<T extends NamedEntity>
extends HashMap<String, T> {

  @Serial
  private static final long serialVersionUID = 202410250009L;

  /**
  * Puts the object in the map given a key.
  */
  @Override
  public T put(String key, T value) {
    return super.put(key.toLowerCase(), value);
  }

  /**
  * Gets the object from the map given a key.
  */
  @Override
  public T get(Object key) {
    return super.get(((String)key).toLowerCase());
  }

  /**
   * Checks if the map contains the key given a key.
   */
  @Override
  public boolean containsKey(Object key) {
    return super.containsKey(((String)key).toLowerCase());
  }

  @Override
  public T putIfAbsent(String key, T value) {
    return super.putIfAbsent(key.toLowerCase(), value);
  }

  /**
   * Removes the object from the map given a key.
   */
  @Override
  public T remove(Object key) {
    return super.remove(((String)key).toLowerCase());
  }
}
