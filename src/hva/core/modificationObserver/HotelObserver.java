package hva.core.modificationObserver;

import java.io.Serializable;

/**
 * Interface for the Hotel Observer, can have multiple methods.
 */
public interface HotelObserver extends Serializable {
    void update(boolean state);
}
