import java.util.ArrayList;
import java.awt.Point;

/**
 * Write a description of class RutaDeSeda here.
 * 
 * @author SanchezVillagran
 * @version 7/09/2025
 */


public class SilkRoad {
    private static final int n = 15; // tamaño fijo 15x15 matriz
    private int lenght;
    private ArrayList<Point> wayCoordinates; // ArrayList permite guardar las coordenadas de la matriz
    private int cellSize = 40;
    private ArrayList<Rectangle> cells;
    private boolean finish = false;
    /**
     * Constructor for objects of class RutaDeSeda
     */
    public SilkRoad (int lenghtX) {
        this.lenght = lenghtX;
        wayCoordinates = generateSpiral();
        createSilkRoad(this.lenght);
    }
    
    /**
     * Generar Espiral
     * matriz será de 15x15, 225 pasos por mucho en la ruta de seda
     */
    public ArrayList<Point> generateSpiral() {
        ArrayList<Point> camino = new ArrayList<>();
        int valor = 1;
        
        int top = 0, inferior = n - 1;
        int left = 0, right = n - 1;

        while (top <= inferior && left <= right && camino.size() < lenght) {
            // Moverse de izquierda a derecha
            for(int j = left; j <= right; j++) {
                camino.add(new Point(top, j));
            }
            top++;

            // Moverse al llegar al "tope" de arriba hacia abajo
            for(int i = top; i <= inferior && camino.size() < lenght; i++) {
                camino.add(new Point(i, right));
            }
            right--;

            // Moverse al llegar al "tope" de derecha a izquierda
            if(top <= inferior) {
                for (int j = right; j >= left && camino.size() < lenght; j--) {
                    camino.add(new Point(inferior, j));
                }
                inferior--;
            }

            // Moverse al llegar al "tope" de abajo hacia arriba
            if(left <= right) {
                for (int i = inferior; i >= top && camino.size() < lenght; i--) {
                    camino.add(new Point(i, left));
                }
                left++;
            }
        }
        return camino;
    }
    
    public void createSilkRoad(int lenghtX) {
        cells = new ArrayList<>();
        if (lenghtX > wayCoordinates.size()) {
            lenghtX = wayCoordinates.size();
        }

        for (int k = 0; k < lenghtX; k++) {
            Point pos = wayCoordinates.get(k);
            int fila = pos.x;
            int col = pos.y;

            Rectangle celda = new Rectangle();
            celda.changeSize(cellSize, cellSize);
            celda.moveHorizontal(col * cellSize);
            celda.moveVertical(fila * cellSize);
            celda.changeColor("lightGray");
            celda.makeVisible();
            cells.add(celda);
        }
    }
    
    public int getLenRoad() {
        return lenght;
    }
    
    /**
     * Borra las celdas del canvas pero siguen existiendo
     */
    public void cleanRoad(){
        if (cells != null) {
        for (Rectangle r : cells) {
            r.makeInvisible();
            }
        }
    }

    public ArrayList<Point> getWay(){
        return wayCoordinates;
    }
}