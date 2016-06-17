/*
 * Ejercicio Cine (matrices)
 */
package cineperalimonera;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author mfontana
 */
public class CinePeraLimonera {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] cine = new int[8][14];
        int opcion;
        do {
            mostrarMenu();
            opcion = pedirEntero("Escoge una opción.");
            switch (opcion) {
                case 1:
                    sentarseConEntrada(cine);
                    break;
                case 2:
                    mostrarOcupacion(cine);
                    break;
                case 3:
                    sentarseFila(cine);
                    break;
                case 4:
                    sentarseButaca(cine);
                    break;
                case 5:
                    finalizarPelicula(cine);
                    break;
                case 6:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        } while (opcion != 6);
    }

    private static void finalizarPelicula(int[][] cine) {
        for (int i = 0; i < cine.length; i++) {
            for (int j = 0; j < cine[0].length; j++) {
                cine[i][j] = 0;
            }
        }
        System.out.println("Película finalizada. Cine vacío.");
    }

    private static void sentarseButaca(int[][] cine) {
        int butaca = pedirButaca(cine);
        int libres = 0;
        System.out.println("Disponibilidad de la columna " + butaca);
        for (int i = 0; i < cine.length; i++) {
            System.out.println(cine[i][butaca] + " ");
            if (cine[i][butaca] == 0) {
                libres++;
            }
        }
        if (libres == 0) {
            System.out.println("Lo sentimos. En esta columna no hay ningún asiento disponible.");
        } else {
            System.out.println("Quedan " + libres + " asientos disponibles.");
            int fila = pedirFila(cine);
            sentarseFilaButaca(cine, fila, butaca);
        }
    }

    private static void sentarseFila(int[][] cine) {
        int fila = pedirFila(cine);
        int libres = 0;
        System.out.println("Disponibilidad de la fila " + fila);
        for (int j = 0; j < cine[0].length; j++) {
            System.out.print(cine[fila][j] + " ");
            if (cine[fila][j] == 0) {
                libres++;
            }
        }
        System.out.println();
        if (libres == 0) {
            System.out.println("Lo sentimos. En esta fila no hay ningún asiento disponible.");
        } else {
            System.out.println("Quedan " + libres + " asientos disponibles.");
            int butaca = pedirButaca(cine);
            sentarseFilaButaca(cine, fila, butaca);

        }

    }

    private static void mostrarOcupacion(int[][] cine) {
        int libres = 0;
        System.out.println("Ocupación del cine La Pera Limonera");
        for (int i = 0; i < cine.length; i++) {
            for (int j = 0; j < cine[0].length; j++) {
                System.out.print(cine[i][j] + " ");
                if (cine[i][j] == 0) {
                    libres++;
                }
            }
            System.out.println();
        }
        if (libres == 0) {
            System.out.println("Cine completo. No quedan asientos disponibles.");
        } else {
            System.out.println("Quedan " + libres + " butacas disponibles.");
        }
    }

    private static void sentarseConEntrada(int[][] cine) {
        System.out.println("Datos de tu entrada");
        int fila = pedirFila(cine);
        int butaca = pedirButaca(cine);
        sentarseFilaButaca(cine, fila, butaca);
    }

    private static void sentarseFilaButaca(int[][] cine, int fila, int butaca) {
        if (cine[fila][butaca] == 0) {
            cine[fila][butaca] = 1;
            System.out.println("Ya puedes sentarte. Asiento asignado.");
        } else {
            System.out.println("Lo siento pero el asiento que indicas ya está ocupado. No puedes sentarte ahí.");
        }
    }

    private static int pedirButaca(int[][] cine) {
        int butaca;
        do {
            butaca = pedirEntero("Butaca: ");
            if (butaca < 0 || butaca >= cine[0].length) {
                System.out.println("Butaca incorrecta. Debe estar entre 0 y " + (cine[0].length - 1));
            }
        } while (butaca < 0 || butaca >= cine[0].length);
        return butaca;
    }

    private static int pedirFila(int[][] cine) {
        int fila;
        do {
            fila = pedirEntero("Fila: ");
            if (fila < 0 || fila >= cine.length) {
                System.out.println("Fila incorrecta. Debe estar entre 0 y " + (cine.length - 1));
            }
        } while (fila < 0 || fila >= cine.length);
        return fila;
    }

    private static void mostrarMenu() {
        System.out.println("Bienvenido al Cine la Pera Limonera");
        System.out.println("1. Sentarse con entrada.");
        System.out.println("2. Ver la ocupación de cine.");
        System.out.println("3. Sentarse en una fila.");
        System.out.println("4. Sentarse en una butaca.");
        System.out.println("5. Finalizar película");
        System.out.println("6. Salir");
    }

    private static int pedirEntero(String texto) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        boolean error;
        do {
            try {
                System.out.println(texto);
                num = Integer.parseInt(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error de entrada / salida.");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("Debes introducir un número entero.");
                error = true;
            }
        } while (error);    // error == true
        return num;
    }

}
