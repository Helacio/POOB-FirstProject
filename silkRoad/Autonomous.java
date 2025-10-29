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
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Autonomous
     */
    public Autonomous(int start, String color, int initialTenges) {
        
        super(generateRandomNumber(), color, initialTenges);
        
        this.shopNumber = shopNumber;
        this.distanceX = distanceX;
        this.figureShop = new ArrayList<>();
        this.visible = false;
        this.isEmpty = false;
        this.color = color;
        
        this.figureShop.add(new Rectangle()); //0: body
        this.figureShop.add(new Rectangle()); //1: door
        this.figureShop.add(new Triangle());  //2: roof
        
        ((Rectangle)figureShop.get(0)).changeSize(30, 40);
        ((Rectangle)figureShop.get(1)).changeSize(20, 15);
        ((Triangle)figureShop.get(2)).changeSize(15, 40);
        
        figureShop.get(2).moveVertical(30);
        figureShop.get(0).moveVertical(10);
        figureShop.get(2).moveHorizontal(-70);
        figureShop.get(1).moveVertical(20);
        figureShop.get(1).moveHorizontal(13);
        
        figureShop.get(0).changeColor(color);
        figureShop.get(1).changeColor("red");
        figureShop.get(2).changeColor("blue");
        
        this.tenges = tenges;
        initialTenges = tenges;
        emptiedCount = 0;
    
    }
    
}