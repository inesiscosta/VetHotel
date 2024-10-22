package hva.core.satisfaction;

import hva.core.Animal;
import java.io.Serializable;

/**
 * Interface for the Satisfaction calculation, can have multiple methods of
 * doing it.
 */
public interface AnimalSatisfaction extends Serializable{
    double calculateSatisfaction(Animal animal);
}
