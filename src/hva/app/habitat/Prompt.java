package hva.app.habitat;

public interface Prompt {

  static String habitatKey() {
    return "Identificador único do habitat: ";
  }

  static String habitatName() {
    return "Nome do habitat: ";
  }

  static String habitatArea() {
    return "Área do habitat: ";
  }

  static String habitatInfluence() {
    return "Influência (positiva, negativa, neutra: POS, NEG, NEU): ";
  }

  static String treeKey() {
    return "Identificador único da árvore: ";
  }

  static String treeName() {
    return "Nome da árvore: ";
  }

  static String treeAge() {
    return "Idade da árvore: ";
  }

  static String treeDifficulty() {
    return "Dificuldade de limpeza da árvore: ";
  }

  static String treeType() {
    return "Tipo de árvore: (CADUCA ou PERENE) ";
  }

  static String treeState() {
    return "A nova situação biológica passa a ser: ";
  }
}
