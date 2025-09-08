
/**
 * Write a description of class Tienda here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tienda
{
    // instance variables - replace the example below with your own
    private int numeroTienda;
    private int distanciaX;
    private int tenges;
    private int initialTenges;
    /**
     * Constructor for objects of class Tienda
     */
    public Tienda(int numeroTienda)
    {
        this.numeroTienda = numeroTienda;

        distanciaX = 100; /* para efectos de calculo 
            la distancia debe ser positiva */
        tenges = 30;
        initialTenges = tenges; /* el tienda guarda un dinero inicial para al 
            final del dia reabastecer su dinero */
    }
    
    /**
     * Restablece la tienda, es decir coloca lo que tiene la tienda (tenges) en 0
     * 
     */
    public void reabastecer() {
        tenges = initialTenges;
    }
    
    public int getDistanciaX() {
        return distanciaX;
    }
    
    public int getTenges() {
        return tenges;
    }
}