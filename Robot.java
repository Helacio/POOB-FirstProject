import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Point;
/**
 * Write a description of class Robot here.
 * 
 * @author SanchezVillagran
 * @version (a version number or a date)
 */
public class Robot
{
    // instance variables - replace the example below with your own
    private int locationActual;
    private int initialStart;
    private int gains;
    private int nMovimientos = 0;
    private Rectangle cabeza;
    private Rectangle boca;
    private Rectangle antena;
    private Triangle cuerpo;
    private Circle ojoIzq;
    private Circle ojoDer;
    /**
     * Constructor for objects of class Robot
     */
    public Robot(int start, String color)
    {
        
        locationActual = start; 
        initialStart = start; 
        
        this.cabeza = new Rectangle();
        this.boca = new Rectangle();
        this.antena = new Rectangle();
        this.cuerpo = new Triangle();
        this.ojoIzq = new Circle();
        this.ojoDer = new Circle();
        
        ojoIzq.makeInvisible();
        ojoDer.makeInvisible();
        
        cabeza.changeSize(20, 20);
        cabeza.changeColor("black");
        
        ojoIzq.changeColor("white");
        ojoIzq.changeSize(3);
        ojoDer.changeColor("white");
        ojoDer.changeSize(3);
        ojoIzq.moveVertical(5);
        ojoDer.moveVertical(5);
        ojoIzq.moveHorizontal(5);
        ojoDer.moveHorizontal(10);
        
        
        
        
        cuerpo.changeSize(10, 15);
        cuerpo.rotate(Math.PI);
        
        boca.changeSize(3, 10);
        
        antena.changeSize(7, 5);
        
        cabeza.changeSize(15,15);
        
        boca.moveVertical(10);
        boca.moveHorizontal(3);
        boca.changeColor("white");
        
        antena.moveVertical(-7);
        antena.moveHorizontal(5);
        antena.changeColor("red");
        
        
        cuerpo.moveHorizontal(-83);
        cuerpo.moveVertical(61);
        
        /*
        if (start > 0 && color != null) {
            
            cabeza.moveHorizontal(start);
            boca.moveHorizontal(start);
            antena.moveHorizontal(start);
            cuerpo.moveHorizontal(start);
            ojoIzq.moveHorizontal(start);
            ojoDer.moveHorizontal(start);
            
            cabeza.changeColor(color);
            */
            cuerpo.makeVisible();
            cabeza.makeVisible();
            antena.makeVisible();
            boca.makeVisible();
            ojoIzq.makeVisible();
            ojoDer.makeVisible();
        
    }

    /**
     * Set the robot in their initial position
     */
    public void resetRobot() {
        locationActual = initialStart;
        gains = 0;
    }
    
    public void resetPositionRob() {
        locationActual = initialStart;
    }
    /**
     * Mueve el robot y calcula la ganancia que obtiene
     * @param Esto es la tienda a la que va a saquear el robot
     * 
     */
    public void moveRobot(Simulador simulador, int idTienda) {
        
        HashMap<Integer, Tienda> tiendas = simulador.getTiendas();
        Tienda tienda = tiendas.get(idTienda); 
        ArrayList<Point> camino = simulador.getRutaDeSeda().getCamino();
        gains = tienda.getTenges() - Math.abs(locationActual - tienda.getDistanciaX());
        locationActual = tienda.getDistanciaX();
        
        for(int i = 0; i<=idTienda; i++){
            Point paso = camino.get(i);
            int x = paso.x * 40;
            int y = paso.y * 40;
            setPosition(x, y);
        }
        this.nMovimientos++;

    }
    
    public void makeInvisible() {
        cuerpo.makeInvisible();
        cabeza.makeInvisible();
        antena.makeInvisible();
        boca.makeInvisible();
        ojoIzq.makeInvisible();
        ojoDer.makeInvisible();
    }
    
    public int getGain() {
        return gains;
    }
    
    public int getLocationActual() {
        return locationActual;
    }
    
    public int getInitialStart() {
        return initialStart;
    }

    private void setPosition(int x, int y) {
        // Mueve todas las partes del robot al punto (x, y)
        cabeza.setXPosition(50 + y);
        cabeza.setYPosition(50 + x);
        boca.setXPosition(53 + y);
        boca.setYPosition(53 + x);
        antena.setXPosition(55 + y);
        antena.setYPosition(55 + x);
        cuerpo.setXPosition(57 + y);
        cuerpo.setYPosition(57 + x);
        ojoIzq.setXPosition(55 + y);
        ojoIzq.setYPosition(55 + x);
        ojoDer.setXPosition(60 + y); 
        ojoDer.setYPosition(60 + x);
    }
    
}