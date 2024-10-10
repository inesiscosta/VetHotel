package hva.core.modificationObserver;

import java.io.Serializable;
public interface HotelObserver extends Serializable {
    void update(boolean state);
}
