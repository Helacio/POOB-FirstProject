import java.util.ArrayList;

/**
 * Write a description of class Simulador here.
 * 
 * @author SanchezVillagran 
 * @version (a version number or a date)
 */
public class Simulador
{
    // instance variables - replace the example below with your own
    
    private ArrayList<Tienda> tiendas;
    private ArrayList<Robot> robots;
    private RutaDeSeda rutaDeSeda;
    private boolean visible;
    private boolean finish;
    /**
     * Constructor for objects of class Simulador
     */
    public Simulador()
    {
        RutaDeSeda rutaDeSeda = new RutaDeSeda(100);
        this.rutaDeSeda = rutaDeSeda;
        visible = true;
        finish = false;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    
    public void addTienda() {
        Tienda newTienda = new Tienda(tiendas.size()); // el argumento de la tienda va a ser aleatorio
        tiendas.add(newTienda);
    }
    
    public void removeTienda(int idTienda) {
        tiendas.remove(idTienda);
    }
    
    public void addRobot() { /* el argumento de addRobot debe tener un entero
        el cual será la posición inicial del robot */
        Robot newRobot = new Robot(robots.size());
        robots.add(newRobot);
    }
    
    public void removeRobot(int idRobot) { /* El robot y tienda debe tener un ID 
        para luego eliminarlo desde ese id (se piensa usar tablasHash para
        la siguiente entrega*/
        robots.remove(idRobot);
    }
    
    public void resetRutaRutaDeSeda() {
        for (Robot r : robots) {
            r.resetPosition();
        }
        
        for (Tienda t : tiendas) {
            t.reabastecer();
        }
    }
    
    public ArrayList getRobots() {
        return robots;
    }
    
    public ArrayList getTienda() {
        return tiendas;
    }
    
    public int getGains() {
        
        int totallyGains = 0;
        
        for (Robot r : robots) {
            totallyGains += r.getGain();
        }
        
        return totallyGains;
    }
    
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
    
    public void finishSimulator() {
        finish = true;
    }
}