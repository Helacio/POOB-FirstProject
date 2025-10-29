package silkRoad;

import exception.*;
import java.util.ArrayList;
import shapes.*;


/**
 * Write a description of class Fighter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fighter extends Shop   {
    

    /**
     * Constructor for objects of class Fighter
     */
    public Fighter(int distanceX, String color, int tenges) {
        
        super(distanceX, color, tenges);
    
    }

    /**
     * This method make that a robot can rob this shop only if its Tenges is greater than the shop's Tenges; otherwise, it cannot.
     */
    @Override
    public void descount(int tengesRobot) throws ObjectInSilkRoadException {
        
        if (tengesRobot < this.tenges) throw new ObjectInSilkRoadException(ObjectInSilkRoadException.CANT_DISCOUNT);
        
        if (tengesRobot > this.tenges) {
            
            this.tenges -= tengesRobot;
            
        }
    }
}