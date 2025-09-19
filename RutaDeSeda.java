import java.util.ArrayList;

/**
 * Write a description of class RutaDeSeda here.
 * 
 * @author SanchezVillagran
 * @version 7/09/2025
 */


public class RutaDeSeda
{
    private static final int n = 15; // tamaño fijo 15x15 matriz
    // instance variables - replace the example below with your own
    private int longitud;
    
    /**
     * Constructor for objects of class RutaDeSeda
     */
    public RutaDeSeda (int longitud) {
        this.longitud = longitud;
        generarEspiral();
        
    }
    
        /**
     * Generar Espiral
     * matriz será de 15x15, 225 pasos por mucho en la ruta de seda
     */
    public static int[][] generarEspiral() {
        int[][] matriz = new int[n][n];
        int valor = 1;

        int top = 0, inferior = n - 1;
        int left = 0, right = n - 1;

        while (top <= inferior && left <= right) {
            // Moverse de izquierda a derecha
            for(int j = left; j <= right; j++) {
                matriz[top][j] = valor++;
            }
            top++;

            // Moverse al llegar al "tope" de arriba hacia abajo
            for(int i = top; i <= inferior; i++) {
                matriz[i][right] = valor++;
            }
            right--;

            // Moverse al llegar al "tope" de derecha a izquierda
            if(top <= inferior) {
                for (int j = right; j >= left; j--) {
                    matriz[inferior][j] = valor++;
                }
                inferior--;
            }

            // Moverse al llegar al "tope" de abajo hacia arriba
            if(left <= right) {
                for (int i = inferior; i >= top; i--) {
                    matriz[i][left] = valor++;
                }
                left++;
            }
        }
        return matriz;
    }
    
    
    public int getLongitudRuta() {
        return longitud;
    }
    /**
     * Reset RutaDeSeda 
     */
    
        
    
    
}