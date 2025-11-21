package exception;


/**
 * Write a description of class creationObjectInSilkRoad here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ObjectInSilkRoadException extends Exception {
    
    public final static String CANT_BACK = "This robot can't back on silk road";
    public final static String CANT_DISCOUNT = "This shop can't be stolen, because this shop is a Fighter";
    /**
     * Constructor for objects of class creationObjectInSilkRoad
     */
    public ObjectInSilkRoadException (String mensaje) {
        super(mensaje);
    }

}