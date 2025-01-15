package hva.app.habitat;

public interface Prompt {

  static String habitatKey() {
    return "Habitat ID: ";
  }

  static String habitatName() {
    return "Habitat Name: ";
  }

  static String habitatArea() {
    return "Habitat Area: ";
  }

  static String habitatInfluence() {
    return "Influence (Positive (POS), Negative (NEG), Neutral (NEU)): ";
  }

  static String treeKey() {
    return "Tree ID: ";
  }

  static String treeName() {
    return "Tree Name: ";
  }

  static String treeAge() {
    return "Tree Age: ";
  }

  static String treeDifficulty() {
    return "Tree's Difficulty to Clean: ";
  }

  static String treeType() {
    return "Tree Type: (DECIDUOUS or EVERGREEN) ";
  }

  static String treeState() {
    return "The new biology state becomes: ";
  }
}
