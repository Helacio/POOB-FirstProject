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
        
        this.locationActual = start; 
        this.initialStart = start; 
        
        this.cabeza = new Rectangle();
        this.boca = new Rectangle();
        this.antena = new Rectangle();
        this.cuerpo = new Triangle();
        this.ojoIzq = new Circle();
        this.ojoDer = new Circle();
        
        ojoIzq.makeInvisible();
        ojoDer.makeInvisible();
        
        cabeza.changeSize(20, 20);
        cabeza.changeColor(color);
        
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
    }

    /**
     * Set the robot in their initial position
     */
    public void resetRobot(RutaDeSeda rutaDeSeda) {
        locationActual = initialStart;
        ArrayList<Point> camino = rutaDeSeda.getCamino();
        Point pair = camino.get(initialStart);
        gains = 0;
        int x = (int) pair.getX();
        int y = (int) pair.getY();
        this.setPosition(x, y);
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
        this.gains = tienda.getTenges() - Math.abs(this.locationActual - tienda.getDistanciaX());
        
        if (locationActual < tienda.getDistanciaX()) {
            for(int i = locationActual; i <= tienda.getDistanciaX(); i++){
                Point paso = camino.get(i);
                setPosition(paso.x, paso.y);
                Canvas.getCanvas().wait(100);
                this.nMovimientos++;
            }
        } else {
            for(int i = locationActual; i >= tienda.getDistanciaX(); i--){
                Point paso = camino.get(i);
                setPosition(paso.x, paso.y);
                Canvas.getCanvas().wait(100);
                this.nMovimientos++;
            }
        }
        locationActual = tienda.getDistanciaX();
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

    public void setPosition(int x, int y) {
        cabeza.setXPosition(50 + y * 40);
        cabeza.setYPosition(50 + x * 40);
        boca.setXPosition(53 + y * 40);
        boca.setYPosition(60 + x * 40);
        antena.setXPosition(55 + y * 40);
        antena.setYPosition(43 + x * 40);
        cuerpo.setXPosition(57 + y * 40);
        cuerpo.setYPosition(76 + x * 40);
        ojoIzq.setXPosition(55 + y * 40);
        ojoIzq.setYPosition(55 + x * 40);
        ojoDer.setXPosition(60 + y * 40);
        ojoDer.setYPosition(55 + x * 40);
        
        cuerpo.makeVisible();
        cabeza.makeVisible();
        antena.makeVisible();
        boca.makeVisible();
        ojoIzq.makeVisible();
        ojoDer.makeVisible();
    }
    

}