package hva.app.main;

public interface Message {
  static String fileNotFound() {
    return "O ficheiro não existe.";
  }

  static String fileNotFound(String filename) {
    return "O ficheiro '" + filename + "' não existe.";
  }

  static String openFile() {
    return "Open File";
  }

  static String saveAs() {
    return "Save File como: ";
  }
}
