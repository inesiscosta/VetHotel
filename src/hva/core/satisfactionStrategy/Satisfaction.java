package hva.core.satisfactionStrategy;

import hva.core.Veterinarian;
import hva.core.ZooKeeper;

/**
 * Interface for the Satisfaction calculation, can have multiple methods of
 * doing it.
 */
public interface Satisfaction {
    double calculateSatisfaction(Veterinarian vet);
    double calculateSatisfaction(ZooKeeper keeper);
}
