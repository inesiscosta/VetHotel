package hva.core.modificationObserver;

public interface HotelSubject {
    void addHotelObserver(HotelObserver observer);
    void removeHotelObserver(HotelObserver observer);
    void notifyHotelObservers();
}
