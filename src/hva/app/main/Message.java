package hva.app.main;

public interface Message {
  static String fileNotFound() {
    return "The File doesn't exist.";
  }

  static String fileNotFound(String filename) {
    return "The File '" + filename + "' doesn't exist.";
  }

  static String openFile() {
    return "Open File";
  }

  static String saveAs() {
    return "Save File As: ";
  }
}
