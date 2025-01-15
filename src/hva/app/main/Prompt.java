package hva.app.main;

public interface Prompt {
  static String openFile() {
    return "Ficheiro a abrir: ";
  }

  static String saveAs() {
    return "Save File como: ";
  }
  
  static String newSaveAs() {
    return "Ficheiro sem nome. " + saveAs();
  }

  static String saveBeforeExit() {
    return "Guardar antes de fechar? ";
  }
}
