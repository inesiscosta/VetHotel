package hva.core.satisfaction;

import hva.core.Animal;
import java.io.Serializable;

/**
 * Interface for the Animal Satisfaction calculation, can have multiple methods
 * of calculating it.
 */
public interface AnimalSatisfaction extends Serializable{
  double calculateSatisfaction(Animal animal);
}
