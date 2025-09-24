import java.util.ArrayList;
import java.awt.Point;

/**
 * Write a description of class RutaDeSeda here.
 * 
 * @author SanchezVillagran
 * @version 7/09/2025
 */


public class RutaDeSeda {
    private static final int n = 15; // tamaño fijo 15x15 matriz
    private int longitud;
    private ArrayList<Point> camino; // ArrayList permite guardar las coordenadas de la matriz
    private int tamañoCelda = 40;
    private ArrayList<Rectangle> celdas;
    private boolean finish = false;
    /**
     * Constructor for objects of class RutaDeSeda
     */
    public RutaDeSeda (int longitud) {
        this.longitud = longitud;
        camino = generarEspiral();
        crearRutaDeSeda(this.longitud);
    }
    
    /**
     * Generar Espiral
     * matriz será de 15x15, 225 pasos por mucho en la ruta de seda
     */
    public ArrayList<Point> generarEspiral() {
        ArrayList<Point> camino = new ArrayList<>();
        int valor = 1;
        
        int top = 0, inferior = n - 1;
        int left = 0, right = n - 1;

        while (top <= inferior && left <= right && camino.size() < longitud) {
            // Moverse de izquierda a derecha
            for(int j = left; j <= right; j++) {
                camino.add(new Point(top, j));
            }
            top++;

            // Moverse al llegar al "tope" de arriba hacia abajo
            for(int i = top; i <= inferior && camino.size() < longitud; i++) {
                camino.add(new Point(i, right));
            }
            right--;

            // Moverse al llegar al "tope" de derecha a izquierda
            if(top <= inferior) {
                for (int j = right; j >= left && camino.size() < longitud; j--) {
                    camino.add(new Point(inferior, j));
                }
                inferior--;
            }

            // Moverse al llegar al "tope" de abajo hacia arriba
            if(left <= right) {
                for (int i = inferior; i >= top && camino.size() < longitud; i--) {
                    camino.add(new Point(i, left));
                }
                left++;
            }
        }
        return camino;
    }
    
    public void crearRutaDeSeda(int longitud) {
        celdas = new ArrayList<>();
        if (longitud > camino.size()) {
            longitud = camino.size();
        }

        for (int k = 0; k < longitud; k++) {
            Point pos = camino.get(k);
            int fila = pos.x;
            int col = pos.y;

            Rectangle celda = new Rectangle();
            celda.changeSize(tamañoCelda, tamañoCelda);
            celda.moveHorizontal(col * tamañoCelda);
            celda.moveVertical(fila * tamañoCelda);
            celda.changeColor("lightGray");
            celda.makeVisible();
            celdas.add(celda);
        }
    }
    
    public int getLongitudRuta() {
        return longitud;
    }
    
    /**
     * Borra las celdas del canvas pero siguen existiendo
     */
    public void cleanRuta(){
        if (celdas != null) {
        for (Rectangle r : celdas) {
            r.makeInvisible();
            }
        }
    }

    public ArrayList<Point> getCamino(){
        return camino;
    }
    
}