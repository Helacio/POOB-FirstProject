import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Arrays;
import javax.swing.JOptionPane;
import java.awt.Point;

/**
 * Write a description of class Simulador here.
 * * @author SanchezVillagran 
 * @version (a version number or a date)
 */
public class Simulador
{
    // instance variables - replace the example below with your own
    
    private HashMap<Integer, Tienda> tiendas;
    private HashMap<Integer, Robot> robots;
    private RutaDeSeda rutaDeSeda;
    private boolean visible;
    private boolean finish;
    private HashMap<Integer, Tienda> numRandomTiendas;
    private HashMap<Integer, Robot> numRandomRobots; 
    private ArrayList<String> colors; 
    private int longitud;
    /**
     * Constructor for objects of class Simulador
     */


    public Simulador(int longitud)
    {
        colors = new ArrayList<>(Arrays.asList("red", "black", 
        "blue", "yellow", "magenta", "white", "orange", "pink",
        "cyan", "gray", "lightGray", "darkGray", "brown", "maroon"));
        
        numRandomTiendas = new HashMap<>();
        numRandomRobots = new HashMap<>();
        
        tiendas = new HashMap<>();
        robots = new HashMap<>();
        
        this.longitud = longitud;
        
        RutaDeSeda rutaDeSeda = new RutaDeSeda(longitud);
        this.rutaDeSeda = rutaDeSeda;
        visible = true;
        finish = false;
        
    }



    /**
     * Add a shop in a random position with a random color
     * 
     */
    
    public void addTienda() {
        Random random = new Random();
        
        int posRandomTien = random.nextInt(longitud);
        
        if (numRandomTiendas.containsKey(posRandomTien)) {
            addTienda();
        }
        
        int posColorRandomTien = random.nextInt(colors.size());
        int randomTenges = random.nextInt((int)Math.pow(10, 8)) + 1;
        
        Tienda newTienda = new Tienda(posRandomTien, colors.get(posColorRandomTien), randomTenges); 
        tiendas.put(tiendas.size(), newTienda);
        numRandomTiendas.put(posRandomTien, newTienda);
        
        colors.remove(colors.get(posColorRandomTien));
        int indice = newTienda.getDistanciaX(); // atributo de la tienda
        Point pos = rutaDeSeda.getCamino().get(indice);     // posición en la ruta
        
        int fila = pos.x;
        int col = pos.y;
        newTienda.ubicarTienda(fila, col);
        
    }
    
    
    /**
     * Add a robot in a random position with a random color
     * 
     */
    
    public void addRobot() { 
        Random random = new Random();
        int posRandomRob = random.nextInt(longitud);
        
        if (numRandomRobots.containsKey(posRandomRob) || numRandomTiendas.containsKey(posRandomRob)) {
            addRobot();
            return;
        }  
        
        int ColorRandomRob = random.nextInt(colors.size());
        
        Robot newRobot = new Robot(posRandomRob, colors.get(ColorRandomRob));
        robots.put(robots.size(), newRobot);
        
        colors.remove(colors.get(ColorRandomRob));
        int indice = newRobot.getInitialStart(); // atributo de la tienda
        System.out.println(posRandomRob);
        Point pos = rutaDeSeda.getCamino().get(indice);// posición en la ruta
        
        int fila = pos.x;
        int col = pos.y;
        newRobot.setPosition(fila, col);
    }
    
    
    /**
     * Delete a specific robot 
     * 
     * @param it gives robot's id from HashMap of robots to delete it
     */
    public void removeRobot(int idRobot) {  
        Robot robotToRemove = robots.get(idRobot);
        robots.remove(idRobot);
        robotToRemove.makeInvisible();
        robotToRemove = null;
    }
    
    /**
     * Delete a specific shop
     * 
     * @param It gives a shop's id from HashMap of tiendas to delete it 
     */
    public void removeTienda(int idTienda) {
        Tienda tiendaToRemove = tiendas.get(idTienda);
        tiendas.remove(idTienda);
        tiendaToRemove.makeInvisible();
        tiendaToRemove = null;
    }
    
    /**
     * Reset the skillroad, set the robot positions and shop's tenges given at the start of day
     * 
    */
    
    public void resetRutaRutaDeSeda() {
        for (Robot r : robots.values()) {
            r.resetRobot(rutaDeSeda);
        }
        
        for (Tienda t : tiendas.values()) {
            t.reabastecer();
        }
    }
    
    
    public HashMap<Integer, Robot> getRobots() {
        return robots;
    }
    
    public HashMap<Integer, Tienda> getTiendas() {
        return tiendas;
    }
    
    
    /**
     * Set shops with Tenge starting from the beginning of the day.
     */
    public void reabastecerTiendas() {
        for (Tienda t : tiendas.values()) {
            t.reabastecer();
        }
    }
    
    /**
     * Make reset the all robots position 
     * 
     */
    public void resetearRobots() {
        for (Robot r : robots.values()) {
            r.resetRobot(rutaDeSeda);
        }
    }
    
    /**
     * Move a robot to a specific cell
     */
    public void moveRobot(int idRobot, int idTienda) {
        
        Robot robot = robots.get(idRobot);
        
        robot.moveRobot(this, idTienda);
    }
    
    /**
     * Get the gains from all robots
     * 
     * @return Give the totally gains from all robots
     */
    
    public int getGains() {
        
        int totallyGains = 0;
        
        for (Robot r : robots.values()) {
            totallyGains += r.getGain();
        }
        
        return totallyGains;
    }
    
    /**
     * Give information about sillRoad
     * 
     */
    public void getInfoRutaSeda() {
        System.out.println("La longitud de la ruta de seda es " + rutaDeSeda.getLongitudRuta());
        System.out.println("Numero de robots es " + robots.size());
        System.out.println("Numero de tiendas es " + tiendas.size());
    }
    
    public void makeVisible() {
        visible = true;
    }
    
    public void makeInvisible() {
        visible = false;
    }
    
    /**
     * Finaliza el simulador y sale una pestaña de finalización
     * 
    */
    
    public void finishSimulator() {
        this.finish = true;

        if (finish) {
            
            JOptionPane.showMessageDialog(null, "¡Ha finalizado!", "Fin de la Ruta de la Seda", JOptionPane.INFORMATION_MESSAGE);

            System.exit(0);
        }
        
    }
    
    public RutaDeSeda getRutaDeSeda() {
        return rutaDeSeda;
    }
    
}