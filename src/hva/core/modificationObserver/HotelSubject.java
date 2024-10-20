package hva.core.modificationObserver;

import java.io.Serializable;

/**
 * TODO Add javadoc.
 */
public interface HotelSubject extends Serializable{
    void addHotelObserver(HotelObserver observer);
    void removeHotelObserver(HotelObserver observer);
    void notifyHotelObservers();
}
