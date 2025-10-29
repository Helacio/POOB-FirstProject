package silkRoad;

import exception.*;
import shapes.Canvas;
import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Point;


/**
 * Write a description of class Tender here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tender extends Robot
{

    /**
     * Constructor for objects of class Tender
     */
    public Tender(int start, String color) {
        
        super(start, color);
        
    }

     /**
     * Move a Robot to a specific shop
     */
    public void moveRobot(SilkRoad silkRoad, int shopId) throws ObjectInSilkRoadException {
        
        HashMap<Integer, Shop> shops = silkRoad.getShops();
        Shop shop = shops.get(shopId);
        ArrayList<Point> path = silkRoad.getPath();
        this.gains = (shop.getTenges() - Math.abs(this.actualLocation - shop.getDistanceX()))/2;
        
        if (actualLocation >= shop.getDistanceX()) throw new ObjectInSilkRoadException(ObjectInSilkRoadException.CANT_BACK);
        
        if (actualLocation < shop.getDistanceX()) {
            for(int i = actualLocation; i <= shop.getDistanceX(); i++){
                Point step = path.get(i);
                setPosition(step.x, step.y);
                Canvas.getCanvas().wait(100);
                this.nMoves++;
            }
        } else {
            for(int i = actualLocation; i >= shop.getDistanceX(); i--){
                Point step = path.get(i);
                setPosition(step.x, step.y);
                Canvas.getCanvas().wait(100);
                this.nMoves++;
            }
        }
        
        actualLocation = shop.getDistanceX();
        profitsPerMove.add(gains/2);
    
    }
}