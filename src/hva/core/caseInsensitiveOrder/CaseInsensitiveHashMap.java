package hva.core.caseInsensitiveOrder;

import java.util.HashMap;

/**
 * Custom HasMap override that trets all the ids strings as case insensitive
 * it doesnt distingues between lower and upper case, it makes all the ids 
 * lower case before calling the super methods provided by the HashMap class.
 */
public class CaseInsensitiveHashMap<NamedEntity> extends HashMap<String, NamedEntity> {

    /**
     * Puts the object in the map with the key in lower case
     */
    @Override
    public NamedEntity put(String key, NamedEntity object) {
        return super.put(key.toLowerCase(), object);
    }

    /**
     * Gets the object from the map with the key in lower case
     */
    @Override
    public NamedEntity get(Object key) {
        if(key instanceof String)
            return super.get(((String)key).toLowerCase());
        return null;
    }

    /**
     * Checks if the map contains the key in lower case
     */
    @Override
    public boolean containsKey(Object key) {
        if(key instanceof String)
            return super.containsKey(((String)key).toLowerCase());
        return false;
    }

    /**
     * Removes the object from the map with the key in lower case
     */
    @Override
    public NamedEntity remove(Object key) {
        if(key instanceof String)
            return super.remove(((String)key).toLowerCase());
        return null;
    }
}
