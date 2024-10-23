package hva.core;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;
import java.util.TreeSet;

/**
 * Represents a vaccine in a Vet Hotel.
 */
class Vaccine extends NamedEntity {
  private int _numApplications;
  private Collection<Species> _appropiateSpecies;

  /**
   * Creates a new Vaccine.
   *
   * @param id the vaccine's unique identifier
   * @param name the vaccine's name
   * @param appropiateSpecies a list of the species the vaccine is suitable for
   */
  Vaccine(String id, String name, List<Species> appropiateSpecies) {
    super(id, name);
    _appropiateSpecies = new TreeSet<Species>(appropiateSpecies);
  }

  /**
   * Gets the number of applications of the vaccine.
   */
  int numApplications() {
    return _numApplications;
  }

  boolean isSpeciesApropriated(Species species) {
    return _appropiateSpecies.contains(species);
  }
  /**
   * Gets the Vaccine object representation as a string containing
   * information that describes said Vaccine.
   * 
   * @return the Vaccine object string representation
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("VACINA|")
      .append(this.id()).append("|")
      .append(this.name()).append("|")
      .append(_numApplications)
      .append(suitableSpeciesToString());
    return result.toString();
  }

  private String suitableSpeciesToString() {
    StringBuilder suitableSpecies = new StringBuilder();
    suitableSpecies.append("|");
    for (Species specie: _appropiateSpecies)
      suitableSpecies.append( specie.id()).append(",");
    if(suitableSpecies.length() > 0)
      suitableSpecies.setLength(suitableSpecies.length()-1);
    return suitableSpecies.toString();
  }

  /**
   * Increments the number of applications of the vaccine.
   * 
   */
  void incrementNumApplications() {
    _numApplications++;
  }

  /**
   * Determines the effect of the vaccine on an animal and returns it.
   * 
   * @param animal the animal to determine the effect of the vaccine on
   * @return the effect of the vaccine on the animal
   * @throws IllegalStateException if the Health Status cannot be determined
   * / is not NORMAL, CONFUSION, ACCIDENT or ERROR
   */
  HealthStatus determineVaccineEffect(Animal animal) throws
  IllegalStateException {
    boolean correctSpecies = _appropiateSpecies.contains(animal.species());
      return HealthStatus.determineHealthStatus(calculateVaccineDamage(
      animal), correctSpecies);
    }

  private int calculateVaccineDamage(Animal animal) {
    Species speciesBiggestName =  biggestSpeciesName();
    int commonCharacters = 0;
    String speciesName = animal.species().name();
    //Converts a string to a list of characters using IntStream, Stream<Character> and collects them to a list<character>
    List<Character> nameCharsList = speciesName.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    for (int i = 0; i < Math.min(speciesBiggestName.name().length(),
    speciesName.length()); i++) {
      if (nameCharsList.contains(speciesBiggestName.name().charAt(i)))
        commonCharacters++;
      }
    return biggestSpeciesName().name().length() - commonCharacters;
  }

  private Species biggestSpeciesName() {
    Species biggestSpecies = null;
    for (Species species : _appropiateSpecies) {
      if (biggestSpecies == null ||
      species.name().length() > biggestSpecies.name().length())
        biggestSpecies = species;
    }
    return biggestSpecies;
  }
}
