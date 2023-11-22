package view;

import java.util.Scanner;

public class view_ChangeTurn {

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i<5; i++)
    {
      mostrarCambioDeTurno("Jugador 2");
      esperarTecla();
      limpiarPantalla();
      //Thread.sleep(2000);

    }

  }
/*
  public static void limpiarPantalla() {
    // Utiliza la secuencia de escape ANSI para limpiar la pantalla
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  */

  // For the moment the "visual" solution in IntelliJ due the
  // fact that we can't use any other option.
  public static void limpiarPantalla() {
    for (int i = 0; i < 50; i++) {
      System.out.println();
    }
  }

  public static void esperarTecla() {
    try {
      // Utiliza Scanner para esperar a que el usuario presione una tecla
      Scanner scanner = new Scanner(System.in);
      scanner.nextLine();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static void mostrarCambioDeTurno(String nombreJugador) {
    // Obtener el ancho de la terminal en IntelliJ
    int anchoTerminal = obtenerAnchoTerminalIntelliJ();

    String nextPlayerString = "Next turn. Player name:";
    String pressEnterString = "Press ENTER to continue . . .";
    // Calcula la posición para centrar el nombre del jugador
    int centrarEn = (anchoTerminal - (nombreJugador.length() + 2)) / 2;
    int centrarTitle = (anchoTerminal - (nextPlayerString.length() + 2)) / 2;
    int centerPressEnter = (anchoTerminal - (pressEnterString.length() + 2)) / 2;

    // Imprime el cambio de turno con el nombre del jugador centrado
    System.out.println("╔" + rellenarConCaracter('=', anchoTerminal - 2) + "╗");
    System.out.println("║" + rellenarConCaracter(' ', centrarTitle) + nextPlayerString + rellenarConCaracter(' ', centrarTitle) + " ║");
    System.out.println("║" + rellenarConCaracter(' ', centrarEn) + nombreJugador + rellenarConCaracter(' ', centrarEn) + " ║");
    System.out.println("╚" + rellenarConCaracter('=', anchoTerminal - 2) + "╝");

    System.out.println(" "); // New line (could be \n but is more visual now.
    System.out.println("┌" + rellenarConCaracter('─', anchoTerminal - 2) + "┐");
    System.out.println("│" + rellenarConCaracter(' ', centerPressEnter) + pressEnterString + rellenarConCaracter(' ', centerPressEnter) + " │");
    System.out.println("└" + rellenarConCaracter('─', anchoTerminal - 2) + "┘");
  }

  public static String rellenarConCaracter(char caracter, int longitud) {
    StringBuilder resultado = new StringBuilder();
    for (int i = 0; i < longitud; i++) {
      resultado.append(caracter);
    }
    return resultado.toString();
  }

  public static int obtenerAnchoTerminalIntelliJ() {
    // Puedes ajustar esto según tus necesidades
    return 160;
  }
}
