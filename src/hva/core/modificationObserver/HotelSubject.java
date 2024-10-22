package hva.core.modificationObserver;

import java.io.Serializable;

/**
 * Interface for the Hotel Subject, can have multiple methods.
 */
public interface HotelSubject extends Serializable{
    void addHotelObserver(HotelObserver observer);
    void removeHotelObserver(HotelObserver observer);
    void notifyHotelObservers();
}
