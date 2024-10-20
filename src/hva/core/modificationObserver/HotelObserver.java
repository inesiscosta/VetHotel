package hva.core.modificationObserver;

import java.io.Serializable;

/**
 * TODO Add javadoc.
 */
public interface HotelObserver extends Serializable {
    void update(boolean state);
}
