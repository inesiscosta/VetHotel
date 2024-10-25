package hva.core;

import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a vaccine in a Vet Hotel.
 */
class Vaccine extends NamedEntity {

  @Serial
  private static final long serialVersionUID = 202410242352L;

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
    _appropiateSpecies = new HashSet<>(appropiateSpecies);
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
      return HealthStatus.determineHealthStatus(
      calculateVaccineDamage(animal), correctSpecies);
    }

  private int calculateVaccineDamage(Animal animal) {
    String biggestSpeciesName = 
    animal.species().name().length() > speciesBiggestName().name().length()
    ? animal.species().name()
    : speciesBiggestName().name();
    int maxCommonCharacters = 0;
    for (Species species : _appropiateSpecies) {
        long commonCharacters = animal.species().name().chars().mapToObj(c -> (char) c)
            .filter(c -> species.name().indexOf(c) >= 0).count();
        maxCommonCharacters = Math.max(maxCommonCharacters, (int) commonCharacters);
    }
    return biggestSpeciesName.length() - maxCommonCharacters;
  }

  private Species speciesBiggestName() {
    return _appropiateSpecies.stream()
    .max((species1, species2) -> Integer
    .compare(species1.name().length(), species2.name().length())).orElse(null);
  }

  private String suitableSpeciesToString() {
    return _appropiateSpecies.isEmpty() ? "" : "|" + _appropiateSpecies
    .stream().sorted().map(Species::id).collect(Collectors.joining(","));
  }

  /**
   * Gets the Vaccine object representation as a string containing
   * information that describes said Vaccine.
   * 
   * @return the Vaccine object string representation
   */
  @Override
  public String toString() {
    // VACINA|id|name|numApplications|species1,species2,...
    StringBuilder result = new StringBuilder();
    result.append("VACINA|")
    .append(this.id()).append("|")
    .append(this.name()).append("|")
    .append(_numApplications)
    .append(suitableSpeciesToString());
    return result.toString();
  }
}
