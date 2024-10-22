package hva.core.observers;

import java.io.Serializable;

/**
 * Interface for the Hotel Observer. Can have multiple methods.
 */
public interface HotelObserver extends Serializable {
    void update(boolean state);
}
