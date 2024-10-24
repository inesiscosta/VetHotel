package hva.core.satisfaction;

import hva.core.Veterinarian;

import java.io.Serializable;

/**
 * Interface for the Veterinary Satisfaction calculation, can have multiple
 * methods of calculating it.
 */
public interface VeterinarianSatisfaction extends Serializable{
  double calculateSatisfaction(Veterinarian vet);
}
