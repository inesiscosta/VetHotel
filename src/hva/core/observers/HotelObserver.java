package hva.core.observers;

import java.io.Serializable;

/**
 * Interface for the Hotel Observer.
 */
public interface HotelObserver extends Serializable {
    void update(boolean state);
}
