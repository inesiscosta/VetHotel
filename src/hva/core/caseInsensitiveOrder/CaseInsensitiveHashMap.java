package hva.core.caseInsensitiveOrder;

import hva.core.NamedEntity;
import java.io.Serial;
import java.util.HashMap;

/**
 * Custom HasMap override that trets all the ids strings as case insensitive
 * it doesnt distingues between lower and upper case, it makes all the ids 
 * lower case before calling the super methods provided by the HashMap class.
 */
public class CaseInsensitiveHashMap<T extends NamedEntity>
extends HashMap<String, T> {

  @Serial
  private static final long serialVersionUID = 202410232303L;

  /**
  * Puts the object in the map with the key in lower case.
  */
  @Override
  public T put(String key, T value) {
    return super.put(key.toLowerCase(), value);
  }

  /**
  * Gets the object from the map with the key in lower case.
  */
  @Override
  public T get(Object key) {
    return super.get(((String)key).toLowerCase());
  }

  /**
   * Checks if the map contains the key in lower case.
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
   * Removes the object from the map with the key in lower case.
   */
  @Override
  public T remove(Object key) {
    return super.remove(((String)key).toLowerCase());
  }
}
