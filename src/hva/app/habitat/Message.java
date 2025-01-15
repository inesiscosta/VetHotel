package hva.app.habitat;

public interface Message {

  static String noAssociation(String idHabitat, String idSpecies) {
    return "There is no association between Habitat '" + idHabitat + "' and Species '" + idSpecies + "' ";
  }
}
