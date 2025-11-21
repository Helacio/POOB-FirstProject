package silkroad;

import shapes.Canvas;
import java.awt.Point;
import java.util.HashMap;
import java.util.ArrayList;
 

/**
 * Write a description of class NeverBack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NeverBack extends Robot {
    

    /**
     * Constructor for objects of class NeverBack
     */
    public NeverBack(int start, String color) {
        super(start, color);
    }

     /**
     * Move a Robot to a specific shop
     */
    public void moveRobot(SilkRoad silkRoad, int shopId) {
        
        HashMap<Integer, Shop> shops = silkRoad.getShops();
        Shop shop = shops.get(shopId);
        ArrayList<Point> path = silkRoad.getPath();
        this.gains = shop.getTenges() - Math.abs(this.actualLocation - shop.getDistanceX());
        
        if (actualLocation < shop.getDistanceX()) {
            for(int i = actualLocation; i <= shop.getDistanceX(); i++){
                Point step = path.get(i);
                setPosition(step.x, step.y);
                Canvas.getCanvas().wait(100);
                this.nMoves++;
            }
        }
        
        actualLocation = shop.getDistanceX();
        profitsPerMove.add(gains);
    
    }
}
