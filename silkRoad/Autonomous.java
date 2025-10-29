package silkRoad;

import java.util.Random;

/**
 * Write a description of class Autonomous here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Autonomous extends Shop {
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Autonomous
     */
    public Autonomous(String color) {
        super(Shop.generateRandomNumber(), color);
        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}