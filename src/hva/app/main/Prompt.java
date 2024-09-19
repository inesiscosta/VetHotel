package hva.app.main;

public interface Prompt {
  static String openFile() {
    return "Ficheiro a abrir: ";
  }

  static String saveAs() {
    return "Guardar ficheiro como: ";
  }
  
  static String newSaveAs() {
    return "Ficheiro sem nome. " + saveAs();
  }

  static String saveBeforeExit() {
    return "Guardar antes de fechar? ";
  }
}
