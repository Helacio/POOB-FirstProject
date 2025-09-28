import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Arrays;
import javax.swing.JOptionPane;
import java.awt.Point;

/**
 * Write a description of class Simulator here.
 * * @author SanchezVillagran 
 * @version (a version number or a date)
 */
public class Simulator
{
    // instance variables - replace the example below with your own
    
    private HashMap<Integer, Shop> shops;
    private HashMap<Integer, Robot> robots;
    private SilkRoad rutaDeSeda;
    private boolean visible;
    private boolean finish;
    private HashMap<Integer, Shop> numRandomShops;
    private HashMap<Integer, Robot> numRandomRobots; 
    private ArrayList<String> colors; 
    private int len;
    /**
     * Constructor for objects of class Simulator
     */


    public Simulator(int len)
    {
        colors = new ArrayList<>(Arrays.asList("red", "black", 
        "blue", "yellow", "magenta", "white", "orange", "pink",
        "cyan", "gray", "lightGray", "darkGray", "brown", "maroon"));
        
        numRandomShops = new HashMap<>();
        numRandomRobots = new HashMap<>();
        
        shops = new HashMap<>();
        robots = new HashMap<>();
        
        this.len = len;
        
        SilkRoad rutaDeSeda = new SilkRoad(len);
        this.rutaDeSeda = rutaDeSeda;
        visible = true;
        finish = false;
        
    }



    /**
     * Add a shop in a random position with a random color
     * 
     */
    
    public void addShop() {
        Random random = new Random();
        
        int posRandomTien = random.nextInt(len);
        
        if (numRandomShops.containsKey(posRandomTien)) {
            addShop();
        }
        
        int posColorRandomTien = random.nextInt(colors.size());
        int randomTenges = random.nextInt((int)Math.pow(10, 8)) + 1;
        
        Shop newTienda = new Shop(posRandomTien, colors.get(posColorRandomTien), randomTenges); 
        shops.put(shops.size(), newTienda);
        numRandomShops.put(posRandomTien, newTienda);
        
        colors.remove(colors.get(posColorRandomTien));
        int indice = newTienda.getDistance(); // atributo de la tienda
        Point pos = rutaDeSeda.getWay().get(indice);     // posición en la ruta
        
        int fila = pos.x;
        int col = pos.y;
        newTienda.setUbication(fila, col);
        
    }
    
    
    /**
     * Add a robot in a random position with a random color
     * 
     */
    
    public void addRobot() { 
        Random random = new Random();
        int posRandomRob = random.nextInt(len);
        
        if (numRandomRobots.containsKey(posRandomRob) || numRandomShops.containsKey(posRandomRob)) {
            addRobot();
            return;
        }  
        
        int ColorRandomRob = random.nextInt(colors.size());
        
        Robot newRobot = new Robot(posRandomRob, colors.get(ColorRandomRob));
        robots.put(robots.size(), newRobot);
        
        colors.remove(colors.get(ColorRandomRob));
        int indice = newRobot.getInitialStart(); // atributo de la tienda
        System.out.println(posRandomRob);
        Point pos = rutaDeSeda.getWay().get(indice);// posición en la ruta
        
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
     * @param It gives a shop's id from HashMap of shops to delete it 
     */
    public void removeTienda(int idTienda) {
        Shop tiendaToRemove = shops.get(idTienda);
        shops.remove(idTienda);
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
        
        for (Shop t : shops.values()) {
            t.reset();
        }
    }
    
    
    public HashMap<Integer, Robot> getRobots() {
        return robots;
    }
    
    public HashMap<Integer, Shop> getTiendas() {
        return shops;
    }
    
    
    /**
     * Set shops with Tenge starting from the beginning of the day.
     */
    public void resetShops() {
        for (Shop t : shops.values()) {
            t.reset();
        }
    }
    
    /**
     * Make reset the all robots position 
     * 
     */
    public void resetRobots() {
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
        System.out.println("La longitud de la ruta de seda es " + rutaDeSeda.getLenRoad());
        System.out.println("Numero de robots es " + robots.size());
        System.out.println("Numero de tiendas es " + shops.size());
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
    
    public SilkRoad getRutaDeSeda() {
        return rutaDeSeda;
    }
    
}