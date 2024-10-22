package hva.core.observers;

import java.io.Serializable;

/**
 * Interface for the Hotel Subject.
 */
public interface HotelSubject extends Serializable{
    void addHotelObserver(HotelObserver observer);
    void removeHotelObserver(HotelObserver observer);
    void notifyHotelObservers();
}
