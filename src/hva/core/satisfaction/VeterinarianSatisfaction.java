package hva.core.satisfaction;

import hva.core.Veterinarian;
import java.io.Serializable;

/**
 * Interface for the Satisfaction calculation, can have multiple methods of
 * doing it.
 */
public interface VeterinarianSatisfaction extends Serializable{
    double calculateSatisfaction(Veterinarian vet);
}
