package hva.app.main;

public interface Prompt {
  static String openFile() {
    return "Name of the File to Open: ";
  }

  static String saveAs() {
    return "Save File As: ";
  }

  static String newSaveAs() {
    return "Unnamed File. " + saveAs();
  }

  static String saveBeforeExit() {
    return "Save before exiting? ";
  }
}
