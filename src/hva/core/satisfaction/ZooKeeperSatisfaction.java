package hva.core.satisfaction;

import hva.core.ZooKeeper;

import java.io.Serializable;

/**
 * Interface for the ZooKeeper Satisfaction calculation, can have multiple 
 * methods of calculating it.
 */
public interface ZooKeeperSatisfaction extends Serializable{
  double calculateSatisfaction(ZooKeeper keeper);
}
