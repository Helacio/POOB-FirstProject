import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Point;

public class Robot
{
    private int actualLocation;
    private int initialStart;
    private int gains;
    private int nMoves = 0;
    private ArrayList<Figure> figureRobot;
    private ArrayList<Integer> profitsPerMove;
    
    /**
     * Constructor for objects of class Robot
     * Index Order: 0: Circle(leftEye), 1: Circle(rightEye), 2: Rectangle(head), 
     * 3: Triangle(body), 4: Rectangle(mouth), 5: Rectangle(antenna)
     */
    public Robot(int start, String color)
    {
        this.figureRobot = new ArrayList<>();
        this.actualLocation = start;
        this.initialStart = start;
        
        // Creation and addition of shapes:
        this.figureRobot.add(new Circle());
        this.figureRobot.add(new Circle());
        this.figureRobot.add(new Rectangle());
        this.figureRobot.add(new Triangle());
        this.figureRobot.add(new Rectangle());
        this.figureRobot.add(new Rectangle());
        
        
        //Modifying head
        ((Rectangle)figureRobot.get(2)).changeSize(20, 20);
        figureRobot.get(2).changeColor(color);
        ((Rectangle)figureRobot.get(2)).changeSize(15,15);
        
        //Modifyng leftEye
        figureRobot.get(0).changeColor("white");
        ((Circle)figureRobot.get(0)).changeSize(3);
        figureRobot.get(0).moveVertical(5);
        figureRobot.get(0).moveHorizontal(5);
        figureRobot.get(0).makeInvisible();
        
        //Modifying rightEye
        figureRobot.get(1).changeColor("white");
        ((Circle)figureRobot.get(1)).changeSize(3);
        figureRobot.get(1).moveVertical(5);
        figureRobot.get(1).moveHorizontal(10);
        figureRobot.get(1).makeInvisible();
        
        //Modifyng body
        ((Triangle)figureRobot.get(3)).changeSize(10, 15);
        ((Triangle)figureRobot.get(3)).rotate(Math.PI);
        figureRobot.get(3).moveHorizontal(-83);
        figureRobot.get(3).moveVertical(61);
        
        //Modifying mouth
        ((Rectangle)figureRobot.get(4)).changeSize(3, 10);
        figureRobot.get(4).moveVertical(10);
        figureRobot.get(4).moveHorizontal(3);
        figureRobot.get(4).changeColor("white");
        
        //Modifying antenna
        ((Rectangle)figureRobot.get(5)).changeSize(7, 5);
        figureRobot.get(5).moveVertical(-7);
        figureRobot.get(5).moveHorizontal(5);
        figureRobot.get(5).changeColor("red");
    }

    /**
     * Set the robots to the initial position
     */
    public void resetRobot(SilkRoad silkRoad) {
        actualLocation = initialStart;
        ArrayList<Point> path = silkRoad.getPath();
        Point pair = path.get(initialStart);
        gains = 0;
        int x = pair.x;
        int y = pair.y;
        this.setPosition(x, y);
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
        } else {
            for(int i = actualLocation; i >= shop.getDistanceX(); i--){
                Point step = path.get(i);
                setPosition(step.x, step.y);
                Canvas.getCanvas().wait(100);
                this.nMoves++;
            }
        }
        actualLocation = shop.getDistanceX();
        profitsPerMove.add(gains);
    }
    
    /**
     * Hide the robot of canvas
     */
    public void makeInvisible() {
        for (Figure f : figureRobot) {
            f.makeInvisible();
        }
    }
    
    /**
     * Get the total gains of a robot
     */
    public int getGain() {
        return gains;
    }
    
    /**
     * Get the actual location of a Robot
     */
    public int getActualLocation() {
        return actualLocation;
    }
    
    /**
     * Get the initial position of a Robot
     */
    public int getInitialStart() {
        return initialStart;
    }
    
    /**
     * Set the position of the Robot in Pixels
     */
    public void setPosition(int x, int y) {
        // 2: head
        figureRobot.get(2).setXPosition(50 + y * 40);
        figureRobot.get(2).setYPosition(50 + x * 40);
        // 4: mouth
        figureRobot.get(4).setXPosition(53 + y * 40);
        figureRobot.get(4).setYPosition(60 + x * 40);
        // 5: antenna
        figureRobot.get(5).setXPosition(55 + y * 40);
        figureRobot.get(5).setYPosition(43 + x * 40);
        // 3: body
        figureRobot.get(3).setXPosition(57 + y * 40);
        figureRobot.get(3).setYPosition(76 + x * 40);
        // 0: leftEye
        figureRobot.get(0).setXPosition(55 + y * 40);
        figureRobot.get(0).setYPosition(55 + x * 40);
        // 1: rightEye
        figureRobot.get(1).setXPosition(60 + y * 40);
        figureRobot.get(1).setYPosition(55 + x * 40);
        
        // Make all parts visible
        figureRobot.get(3).makeVisible(); // body
        figureRobot.get(2).makeVisible(); // head
        figureRobot.get(5).makeVisible(); // antenna
        figureRobot.get(4).makeVisible(); // mouth
        figureRobot.get(0).makeVisible(); // leftEye
        figureRobot.get(1).makeVisible(); // rightEye
    }
    
    /**
     * Get the profit in a specific movement
     */
    public int profitPerMove(int moveIndex){
        return profitsPerMove.get(moveIndex);
    }
}