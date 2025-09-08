
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
    /**
     * Constructor for objects of class Robot
     */
    public Robot(int start)
    {
        locationActual = start; // Debe ser positivo
        initialStart = start; /* el robot guarda una posicion inicial para al 
            final del dia colocarlo en la posicion donde inicio */
    }

    /**
     * Set the robot in their initial position
     */
    public void resetPosition() {
        locationActual = initialStart;
        gains = 0;
    }
    /**
     * Mueve el robot y calcula la ganancia que obtiene
     * @param Esto es la tienda a la que va a saquear el robot
     * 
     */
    public void moveRobot(Tienda tienda) {
        gains = tienda.getTenges() - Math.abs(locationActual - tienda.getDistanciaX());
        locationActual = tienda.getDistanciaX();
        
    }
    
    public int getGain() {
        return gains;
    }
    
}