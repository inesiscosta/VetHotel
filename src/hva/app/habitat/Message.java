package hva.app.habitat;

public interface Message {

  static String noAssociation(String idHabitat, String idSpecies) {
    return "Não existe  associação entre o habitat '" + idHabitat + "' e a espécie'" + idSpecies + "' ";
  }
}
