package hva.core.caseInsensitiveOrder;

import java.util.HashMap;

/**
 * Custom HasMap override that trets all the ids strings as case insensitive
 * it doesnt distingues between lower and upper case, it makes all the ids 
 * lower case before calling the super methods provided by the HashMap class.
 */
public class CaseInsensitiveHashMap<NamedEntity> extends HashMap<String, NamedEntity> {

    @Override
    public NamedEntity put(String key, NamedEntity object) {
        return super.put(key.toLowerCase(), object);
    }

    @Override
    public NamedEntity get(Object key) {
        if(key instanceof String)
            return super.get(((String)key).toLowerCase());
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        if(key instanceof String)
            return super.containsKey(((String)key).toLowerCase());
        return false;
    }

    @Override
    public NamedEntity remove(Object key) {
        if(key instanceof String)
            return super.remove(((String)key).toLowerCase());
        return null;
    }
}
