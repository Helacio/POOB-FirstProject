package silkRoad;

import java.util.Random;
import java.util.ArrayList;
import shapes.*;

/**
 * Write a description of class Autonomous here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Autonomous extends Shop {
    
    

    /**
     * Constructor for objects of class Autonomous
     */
    public Autonomous(int start, String color, int initialTenges) {
        
        super(generateRandomNumber(), color, initialTenges);
        
    }
    
}