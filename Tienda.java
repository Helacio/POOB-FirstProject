
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
    private int vecesDesocupada;
    private Rectangle cuerpo;
    private Triangle techo;
    private Rectangle puerta;
    
    /**
     * Constructor for objects of class Tienda
     */
    public Tienda(int distanciaX, String color, int tenges)
    {
        
        this.numeroTienda = numeroTienda;
        this.cuerpo = new Rectangle();
        this.puerta = new Rectangle();
        this.techo = new Triangle();
        this.distanciaX = distanciaX;
        
        cuerpo.changeSize(30, 40);
        puerta.changeSize(20, 15);
        techo.changeSize(15, 40);
        
        techo.moveVertical(30);
        cuerpo.moveVertical(10);
        techo.moveHorizontal(-70);
        puerta.moveVertical(20);
        puerta.moveHorizontal(13);
        
    /*
        if (distanciaX > 0) {
        cuerpo.moveHorizontal(distanciaX);
        puerta.moveHorizontal(distanciaX);
        techo.moveHorizontal(distanciaX-70);
        this.distanciaX = distanciaX;
    }
    */
        
        
        cuerpo.changeColor(color);
        puerta.changeColor("red");
        techo.changeColor("blue");
        
        
        this.tenges = tenges;
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
    
    public void makeInvisible() {
        cuerpo.makeInvisible();
        puerta.makeInvisible();
        techo.makeInvisible();
    }

    public void ubicarTienda(int posX, int posY){
        int fila = posX;
        int columna = posY;  
        
        cuerpo.setXPosition(50 + columna * 40);
        cuerpo.setYPosition(60 + fila * 40);
        techo.setXPosition(70 +columna * 40);
        techo.setYPosition(45 + fila * 40);
        puerta.setXPosition(63 + columna * 40);
        puerta.setYPosition(70 + fila * 40);
        cuerpo.makeVisible();
        puerta.makeVisible();
        techo.makeVisible();
    }
}