package hva.core.satisfaction;

import hva.core.ZooKeeper;
import java.io.Serializable;

/**
 * Interface for the Satisfaction calculation, can have multiple methods of
 * doing it.
 */
public interface ZooKeeperSatisfaction extends Serializable{
    double calculateSatisfaction(ZooKeeper keeper);
}
