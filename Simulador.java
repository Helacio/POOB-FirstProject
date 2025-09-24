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
        
        RutaDeSeda rutaDeSeda = new RutaDeSeda(longitud);
        this.rutaDeSeda = rutaDeSeda;
        visible = true;
        finish = false;
        
    }



    /**
     * Agrega una tienda en una posicion aleatorea con un color aleatoreo
     * 
     */
    
    public void addTienda(int longitud) {
        Random random = new Random();
        
        int posRandomTien = random.nextInt(longitud);
        
        if (numRandomTiendas.containsKey(posRandomTien)) {
            addTienda(longitud);
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
     * Agrega un robot en una posición aleatorea con un color aleatoreo
     * 
     */
    
    public void addRobot(int longitud) { 
        Random random = new Random();
        int posRandomRob = random.nextInt(longitud);
        
        if (numRandomRobots.containsKey(posRandomRob) || numRandomTiendas.containsKey(posRandomRob)) {
            addRobot(longitud);
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
     * Elimina un robot en especifico
     * 
     * @param Se da el id de un robot de la tabla de robots para eliminarlo 
     */
    public void removeRobot(int idRobot) {  
        Robot robotToRemove = robots.get(idRobot);
        robots.remove(idRobot);
        robotToRemove.makeInvisible();
        robotToRemove = null;
    }
    
    /**
     * Elimina una tienda en especifico
     * 
     * @param Se da el id de una tienda de la tabla de tiendas para eliminarla 
     */
    public void removeTienda(int idTienda) {
        Tienda tiendaToRemove = tiendas.get(idTienda);
        tiendas.remove(idTienda);
        tiendaToRemove.makeInvisible();
        tiendaToRemove = null;
    }
    
    /**
     * Reinicia a su origen las posiciones de los robots y los tenges que tenian las tiendas en un principio
     * 
    */
    
    public void resetRutaRutaDeSeda() {
        for (Robot r : robots.values()) {
            r.resetPositionRob();
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
     * Establece las tiendas con los tenges que iniciaron al principio del dia
     * 
     * 
     */
    public void reabastecerTiendas() {
        for (Tienda t : tiendas.values()) {
            t.reabastecer();
        }
    }
    
    /**
     * Hace que todos los robots que se crearon en el dia vuelvan a sus posiciones inicial
     * 
     */
    public void retonarRobots() {
        for (Robot r : robots.values()) {
            r.resetPositionRob();
        }
    }
    
    /**
     * Mueve el robot a una casilla en especifico 
     * 
     * @param Brinda la casilla a la que se va a mover el robot 
     */
    public void moveRobot(int idRobot, int idTienda) {
        
        Robot robot = robots.get(idRobot);
        
        robot.moveRobot(this, idTienda);
    }
    
    public int getGains() {
        
        int totallyGains = 0;
        
        for (Robot r : robots.values()) {
            totallyGains += r.getGain();
        }
        
        return totallyGains;
    }
    
    /**
     * Se obtiene informacion de la ruta de seda
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