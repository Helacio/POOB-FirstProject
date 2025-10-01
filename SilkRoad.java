import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Arrays;
import javax.swing.JOptionPane;
import java.awt.Point;

/**
 * Write a description of class Simulator here.
 * @author SanchezVillagran 
 * @version (a version number or a date)
 */
public class SilkRoad
{
    
    public final static ArrayList<String> COLORS = new ArrayList<>(Arrays.asList("red", "black", 
        "blue", "yellow", "magenta", "white", "orange", "pink",
        "cyan", "gray", "lightGray", "darkGray", "brown", "maroon"));
    public static final int CELLSIZE = 40;
    private static final int n = 15;
    private HashMap<Integer, Shop> shops;
    private HashMap<Integer, Robot> robots;
    private boolean visible;
    private boolean finished;
    private HashMap<Integer, Shop> randomShopsNum;
    private HashMap<Integer, Robot> randomRobotsNum; 
    private int len;
    private ArrayList<Point> path; 
    private ArrayList<Rectangle> cells;
    
    /**
     * Constructor for objects of class Simulator
     */

    public SilkRoad(int len)
    {

        visible = true;
        finished = false;
        
        this.len = len;
        path = generateSpiral();
        createSilkRoad(this.len);
        
        randomShopsNum = new HashMap<>();
        randomRobotsNum = new HashMap<>();
        
        shops = new HashMap<>();
        robots = new HashMap<>();
        
        this.len = len;
        
    }

    /**
     * Generate Spiral
     * matrix will be 15x15, at most 225 steps on the silk road
     */
    public ArrayList<Point> generateSpiral() {
        ArrayList<Point> path = new ArrayList<>();
        int value = 1;
        
        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;

        while (top <= bottom && left <= right && path.size() < len) {
            // Move from left to right
            for(int j = left; j <= right && path.size() < len; j++) {
                path.add(new Point(top, j));
            }
            top++;

            // Move after reaching the "top" from top to bottom
            for(int i = top; i <= bottom && path.size() < len; i++) {
                path.add(new Point(i, right));
            }
            right--;

            // Move after reaching the "top" from right to left
            if(top <= bottom) {
                for (int j = right; j >= left && path.size() < len; j--) {
                    path.add(new Point(bottom, j));
                    }
                bottom--;
            }

            // Move after reaching the "top" from bottom to top
            if(left <= right) {
                for (int i = bottom; i >= top && path.size() < len; i--) {
                    path.add(new Point(i, left));
                }
                left++;
            }
        }
        return path;
    }
    
    public void createSilkRoad(int len) {
        cells = new ArrayList<>();
        if (len > path.size()) {
            len = path.size();
        }

        for (int k = 0; k < len; k++) {
            Point pos = path.get(k);
            int row = pos.x;
            int col = pos.y;

            Rectangle cell = new Rectangle();

            cell.changeSize(CELLSIZE, CELLSIZE);
            cell.moveHorizontal(col * CELLSIZE);
            cell.moveVertical(row * CELLSIZE);
            cell.changeColor("lightGray");
            cell.makeVisible();
            cells.add(cell);
        }
    }
    
    public int getLenRoad() {
        return len;
    }
    
    /**
     * Deletes the cells from the canvas but they still exist
     */
    public void cleanRoad(){
        if (cells != null) {
            for (Rectangle r : cells) {
                r.makeInvisible();
                }
        }
    }

    public ArrayList<Point> getPath(){
        return path;
    }
        
    /**
     * Add a shop in a random position with a random color
     * */
    
    public void addShop() {
        Random random = new Random();
        
        int randomShopPos = random.nextInt(len);
        
        if (randomShopsNum.containsKey(randomShopPos)) {
            addShop();
        }
        
        int randomShopColorPos = random.nextInt(COLORS.size());
        int randomTenges = random.nextInt((int)Math.pow(10, 8)) + 1;
        
        Shop newShop = new Shop(randomShopPos, COLORS.get(randomShopColorPos), randomTenges); 
        shops.put(shops.size(), newShop);
        randomShopsNum.put(randomShopPos, newShop);
        
        COLORS.remove(COLORS.get(randomShopColorPos));
        int index = newShop.getDistanceX();
        Point pos = path.get(index);
        
        int row = pos.x;
        int col = pos.y;
        newShop.locateShop(row, col);
    }
    
    
    /**
     * Add a robot in a random position with a random color
     * */
    
    public void addRobot() { 
        Random random = new Random();
        int randomRobotPos = random.nextInt(len);
        
        if (randomRobotsNum.containsKey(randomRobotPos) || randomShopsNum.containsKey(randomRobotPos)) {
            addRobot();
            return;
        }  
        
        int randomRobotColor = random.nextInt(COLORS.size());
        
        Robot newRobot = new Robot(randomRobotPos, COLORS.get(randomRobotColor));
        robots.put(robots.size(), newRobot);
        
        COLORS.remove(COLORS.get(randomRobotColor));
        int index = newRobot.getInitialStart();// atributo de la tienda
        System.out.println(randomRobotPos);// posición en la ruta
        Point pos = path.get(index);
        
        int row = pos.x;
        int col = pos.y;
        newRobot.setPosition(row, col);
    }
    
    
    /**
     * Delete a specific robot 
     * @param it gives robot's id from HashMap of robots to delete it
     */
    public void removeRobot(int robotId) { 
        Robot robotToRemove = robots.get(robotId);
        robots.remove(robotId);
        robotToRemove.makeInvisible();
        robotToRemove = null;
    }
    
    /**
     * Delete a specific shop
     * @param It gives a shop's id from HashMap of shops to delete it 
     */
    public void removeShop(int shopId) {
        Shop shopToRemove = shops.get(shopId);
        shops.remove(shopId);
        shopToRemove.makeInvisible();
        shopToRemove = null;
    }
    
    /**
     * Reset the silkRoad, set the robot positions and shop's tenges given at the start of day
     */
    
    public void resetSilkRoad() {
        for (Robot r : robots.values()) {
            r.resetRobot(this);
        }
        
        for (Shop s : shops.values()) {
            s.resupply();
        }
    }
    
    /**
     * Get all robots created
     */
    public HashMap<Integer, Robot> getRobots() {
        return robots;
    }
    
    /**
     * Get all the shopds reated
     */
    public HashMap<Integer, Shop> getShops() {
        return shops;
    }
    
    /**
     * Set shops with Tenge starting from the beginning of the day.
     */
    public void resetShops() {
        for (Shop s : shops.values()) {
            s.resupply();
        }
    }
    
    /**
     * Make reset the all robots position 
     * * @return Give the total gains from all robots
     */
    public void resetRobots() {
        for (Robot r : robots.values()) {
            r.resetRobot(this);
        }
    }
    
    /**
     * Move a robot to a specific cell
     */
    public void moveRobot(int robotId, int shopId) {
        
        Robot robot = robots.get(robotId);
        
        robot.moveRobot(this, shopId);
    }
    
    /**
     * Get the gains from all robots
     * * @return Give the total gains from all robots
     */
    
    public int getGains() {
        int totalGains = 0;
        for (Robot r : robots.values()) {
            totalGains += r.getGain();
        }
        return totalGains;
    }
    
    /**
     * Give information about SilkRoad
     */
    public void getSilkRoadInfo() {
        System.out.println("The length of the Silk Road is " + len);
        System.out.println("Number of robots is " + robots.size());
        System.out.println("Number of shops is " + shops.size());
    }
    
    /**
     * Change all the silk road to visible
     */
    public void makeVisible() {
         if (visible == false) {
            for (Rectangle cell : cells) {
                cell.makeVisible();
            }
        }
        visible = true;
    }
    
    /**
     * Change all the silk road to invisible
     */
    public void makeInvisible() {
        if (visible == true) {
            for (Rectangle cell : cells) {
                cell.makeInvisible();
            }
        }
        visible = false;
    }
    
    /**
     * The simulator finishes and a completion window appears.
     */
    
    public void finishSimulator() {
        this.finished = true;

        if (finished) {
            
            JOptionPane.showMessageDialog(null, "It has finished!", "End of the Silk Road", JOptionPane.INFORMATION_MESSAGE);

            System.exit(0);
        }
    }
    
    /**
     * Get the SilkRoad
     */
    public SilkRoad getSilkRoad() {
        return this;
    }
    
}