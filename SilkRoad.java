import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Arrays;
import javax.swing.JOptionPane;
import java.awt.Point;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;

/**
 * Write a description of class Simulator here.
 * @author SanchezVillagran 
 * @version (a version number or a date)
 */
public class SilkRoad
{
    public final static ArrayList<String> COLORS = new ArrayList<>(Arrays.asList("red", "black", 
        "blue", "yellow", "magenta", "white", "orange", "pink",
        "cyan", "gray", "darkGray", "brown", "maroon", "gold", "darkYellow",
        "greenTint", "salmon", "darkRed"));
    public static final int CELLSIZE = 40;
    private static final int N = 15;
    private HashMap<Integer, Shop> shops;
    private HashMap<Integer, Robot> robots;
    private boolean visible;
    private boolean finished;
    private HashMap<Integer, Shop> randomShopsNum;
    private HashMap<Integer, Robot> randomRobotsNum; 
    private int len;
    private ArrayList<Point> path; 
    private ArrayList<Rectangle> cells;
    private ProgressBar winBar;
    private boolean ok;
    private TreeMap<Integer, Integer> nextRobotToMove;
    
    /**
     * Constructor for objects of class Simulator
     */

    public SilkRoad(int len)
    {
        if(len >= 0 && len <= N * N){
            
            visible = false;
            finished = false;
            
            this.len = len;
            path = generateSpiral();
            createSilkRoad(this.len);
            
            randomShopsNum = new HashMap<>();
            randomRobotsNum = new HashMap<>();
            
            shops = new HashMap<>();
            robots = new HashMap<>();
            
            nextRobotToMove = new TreeMap<>();
            createProgressBar();
            
            this.len = len;
            this.ok = true;
            
        } else{
            
            this.ok = false;
            
        }
        
        
    }
    
    /**
     * Constructor for SilkRoad with icpc case
     * @param days The input of maraton icpc J but all in just one line
     */
    public SilkRoad(int[] days){
        visible = false;
        finished = false;
        robots = new HashMap<>();
        shops = new HashMap<>();
        boolean foundRobotOrShop;
        nextRobotToMove = new TreeMap<>();
        
        len = 120;
        path = generateSpiral();
        createSilkRoad(len);
        foundRobotOrShop = false;
        
        for(int i = 0; i < days.length; ){
            
            int value = days[i];
            
            if(value == 1) {
                
                int location = days[i+1];
                addRobot(location);
                i += 2;
                foundRobotOrShop = true;
                
            } else if(value == 2){
                
                int location = days[i + 1];
                int tenges = days[i + 2];
                addShop(location, tenges);
                i += 3;
                foundRobotOrShop = true;
                
            } else {
                
                i++;
                
            }
        }
        
        this.ok = foundRobotOrShop;
        createProgressBar();
        
    }
    // This method is a test 
    public static void main(String[] args){
        
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        
        String[] parts = inputLine.split(" ");
        int[] numbers = new int[parts.length];
        for(int i = 0; i < parts.length; i++){
            
            numbers[i] = Integer.parseInt(parts[i]);
            
        }
        
        SilkRoad silkRoad = new SilkRoad(numbers);
    }
    
    /**
     * This method makes robots move to the nearest shop. 
     * You can only use it when the shop and robot HashMaps are built with the location of these objects as the key. 
     * 
     */
    public void moveToMaxGain() {
        
        
        while (checkAll()) {
            
            setNeariestRobots();
            Set<Integer> keysToIterate = new TreeSet<>(nextRobotToMove.keySet());
            
            for (Integer rDis : keysToIterate) {
                
                Shop shopToGo = robots.get(nextRobotToMove.get(rDis)).getNeariestShop();
                
                if (shopToGo != null && !shopToGo.getIsEmpty()) {
                    
                    int shopId = shopToGo.getDistanceX();
                    int rId = nextRobotToMove.get(rDis);
                    Robot r = robots.get(rId);
                    
                    moveRobot(rId, shopId);
                    nextRobotToMove.remove(rDis);
                    setNeariestRobots();
                    Robot best = getRobotWithMajorGain();
                    
                    }
                    
            }
            
        }
        
    }
    
    /**
     * Performs a checkup on all shops to verify if they are stolen.
     * @return Return true if there is at least one shop that is not stolen, and false otherwise.
     */
    
    public boolean checkAll() {
        for (Shop s : shops.values()) {
            
            if (!s.getIsEmpty()) {
                
                return true;
                
            }
        }
        
        return false;
        
    }
    
    /**
     * This method set the atribute neariest shop from robot
     * @return This is a TreeMap of the distances and the positions of the robots ready for movement.
     */
    public TreeMap<Integer, Integer> setNeariestRobots() {
        
        for (int rID : robots.keySet()) {
            
            Shop nearestShop = null;
            int minDistance = Integer.MAX_VALUE;
            
                for (int sID : shops.keySet()) {
                    
                    int distanceBetween = Math.abs(rID - sID);
                    
                    if (distanceBetween < minDistance && !shops.get(sID).getIsEmpty()) {
                        
                        minDistance = distanceBetween;
                        robots.get(rID).setNeariestShop(shops.get(sID));
                        
                    }
            }
            
            nextRobotToMove.put(minDistance, rID);
            
        }
        
        return nextRobotToMove;
        
    }
    
    /**
     * Generate Spiral
     * matrix will be 15x15, at most 225 steps on the silk road
     */
    public ArrayList<Point> generateSpiral() {
        
        ArrayList<Point> path = new ArrayList<>();
        
        int top = 0, bottom = N - 1;
        int left = 0, right =  - 1;

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
    
    /**
     * Create the silk road
     * @param The silkRoad's len
     */
    
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
            cell.changeColor("softGray");
            cells.add(cell);
            if (visible){
            
                cell.makeVisible();
            
            }
        }
    }
    
    /**
     * Get silkRoad's len
     */
    
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

    /**
     * Get the array of coordenades of silk road, the points where robots can move 
     * @return Array of cordenades where robots can move 
     */
    public ArrayList<Point> getPath(){
        
        return path;
    
    }
        
    /**
     * Add a shop in a random position with a random color
     * */
    public void addShop() {
    
        Random random = new Random();
        int min = 50;
        int max = 400;
        int randomShopPos = random.nextInt(len);
        
        if (randomShopsNum.containsKey(randomShopPos)) {
        
            addShop();
        
        }
        
        int randomShopColorPos = random.nextInt(COLORS.size());
        int randomTenges = random.nextInt((max - min) + 1) + min;
        
        Shop newShop = new Shop(randomShopPos, COLORS.get(randomShopColorPos), randomTenges); 
        shops.put(newShop.getDistanceX(), newShop);
        randomShopsNum.put(randomShopPos, newShop);
        
        COLORS.remove(COLORS.get(randomShopColorPos));
        int index = newShop.getDistanceX();
        Point pos = path.get(index);
        
        int row = pos.x;
        int col = pos.y;
        newShop.locateShop(row, col);
        System.out.println("posicion Shop: " + shops.size() +" " +randomShopPos);
        if(this.visible){
        
            newShop.makeVisible();
        
        }
        
        if(winBar != null){
        
            int possibleTenges = 0;
            
            for(Shop s : shops.values()) {
            
                possibleTenges += s.getTenges();
            
            }
            
            winBar.setMax(possibleTenges);
        
        }
        
    }
    
    /**
     * Add a shop in a specific location
     * @param location Place in the silk road
     * @param tenges Num money in the shop
     */
    public void addShop(int location, int tenges){
        if(!shops.containsKey(location)){
        
            Random random = new Random();
            int randomShopColorPos = random.nextInt(COLORS.size());
            
            Shop newShop = new Shop(location, COLORS.get(randomShopColorPos), tenges);
            shops.put(newShop.getDistanceX(), newShop);
            
            COLORS.remove(COLORS.get(randomShopColorPos));
            
            int index = newShop.getDistanceX();
            Point pos = path.get(index);
            
            int row = pos.x;
            int col = pos.y;
            newShop.locateShop(row, col);
            
            System.out.println("posicion Shop: " + shops.size() +" " + location);
            if(this.visible){
            
                newShop.makeVisible();
            
            }
            
            ok = true;
        
        } else{
            
            ok = false;
        
        }
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
        robots.put(newRobot.getInitialStart(), newRobot);
        
        COLORS.remove(COLORS.get(randomRobotColor));
        int index = newRobot.getInitialStart();// atributo de la tienda
        System.out.println("posicion Robot: " + robots.size() +" " +randomRobotPos);// posición en la ruta
        Point pos = path.get(index);
        
        int row = pos.x;
        int col = pos.y;
        newRobot.setPosition(row, col);
        if (this.visible){
        
            newRobot.makeVisible();
        
        }
    }
    
    /**
     * Add a robot in a determinated place
     * @param location Is tne initial location of the robot
     */
    public void addRobot(int location){
        if(!robots.containsKey(location)){
        
            Random random = new Random();
            int randomRobotColor = random.nextInt(COLORS.size());
            
            Robot newRobot = new Robot(location, COLORS.get(randomRobotColor));
            robots.put(location, newRobot);
            
            COLORS.remove(COLORS.get(randomRobotColor));
            int index = newRobot.getInitialStart();
            Point pos = path.get(index);
            
            int row = pos.x;
            int col = pos.y;
            newRobot.setPosition(row, col);
            
            if (this.visible){
            
                newRobot.makeVisible();
            
            }
            
            ok = true;
        
        } else {
            
            ok = false;
        
        }
    }
    
    /**
     * Delete a specific robot 
     * @param It gives robot's id from HashMap of robots to delete it
     */
    public void removeRobot(int robotId) { 
        Robot robotToRemove = robots.get(robotId);
        if(robotToRemove != null){
        
            robots.remove(robotId);
            robotToRemove.makeInvisible();
            robotToRemove = null;
            ok = true;
        
        } else{
            
            ok = false;
        
        }
    }
    
    /**
     * Delete a specific shop
     * @param shopId It gives a shop's id from HashMap of shops to delete it 
     */
    public void removeShop(int shopId) {
        Shop shopToRemove = shops.get(shopId);
        if(shopToRemove != null){
        
            shops.remove(shopId);
            shopToRemove.makeInvisible();
            shopToRemove = null;
            ok = true;
        
        } else{
            
            ok = false;
        
        }
    }
    
    /**
     * Reset the silkRoad, set the robot positions and shop's tenges given at the start of day
     */
    
    public void resetSilkRoad() {
        
        for (Robot r : robots.values()) {
        
            r.resetRobot(this.path);
        
        }
        
        for (Shop s : shops.values()) {
        
            s.resupply();
        
        }
        if (winBar != null){
            
            winBar.reset(50);
        
        }
    }
    
    /**
     * Get all robots created
     */
    public HashMap<Integer, Robot> getRobots() {
        
        return robots;
    
    }
    
    /**
     * Get all the shops created
     */
    public HashMap<Integer, Shop> getShops() {
    
        return shops;
    
    }
    
    /**
     * Set shops with Tenges starting from the beginning of the day.
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
            r.resetRobot(this.path);
        }
    }
    
    /**
     * Move a robot to a specific cell
     */
    public void moveRobot(int robotId, int shopId) {
        
        Robot robot = robots.get(robotId);
        Shop shop = shops.get(shopId);
        
        if(robot == null || shop == null) {
        
            ok = false;
            return;
        
        }
        
        int start = robot.getActualLocation();
        int distance = shop.getDistanceX();
        
        if(start <= distance) {
        
            for(int i = start; i <= distance; i++) {
            
                Point  step = path.get(i);
                robot.setPosition(step.x, step.y);
                Canvas.getCanvas().wait(100);
            
            }
        
        } else {
            
            for(int i = start; i >= distance; i--) {
                Point step = path.get(i);
                robot.setPosition(step.x, step.y);
                Canvas.getCanvas().wait(100);
            
            }
        }
        
        int gain = shop.empty() - Math.abs(start - distance);
        robot.addGain(gain);
        robot.setActualLocation(shop.getDistanceX());
        winBar.update(getGains());
        
        if(this.visible) {
            
            robot.makeVisible();
            Robot best = getRobotWithMajorGain();
            if(best != null) {
            
                best.blink();
            
            }
        } else {
            
            robot.makeInvisible();
        
        }
        
        ok = true;
    
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
             
            for(Rectangle cell : cells) {
                
                cell.makeVisible();
            
            }
            
            for(Shop s : shops.values()) {
                
                s.makeVisible();
            
            }
            
            for(Robot r: robots.values()) {
                
                r.makeVisible();
            
            }
            
            winBar.makeVisible();
        
        }
        
        visible = true;
    
    }
    
    /**
     * Change all the silk road to invisible
     */
    public void makeInvisible() {
    
        if (visible == true) {
        
            for(Rectangle cell : cells) {
                
                cell.makeInvisible();
            
            }
            
            for(Robot r : robots.values()){
                
                r.makeInvisible();
            
            }
            
            for(Shop s : shops.values()){
                
                s.makeInvisible();
            
            }
            
            winBar.makeInvisible();
        
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
    
    /**
     * Creates a ProgressBar at the bottom
     */
    public void createProgressBar(){
        int possibleTenges = 0;
    
        for(Shop s: shops.values()){
        
            possibleTenges += s.getTenges();
        
        }
        
        if (possibleTenges == 0){
        
            possibleTenges = 1;
        
        }
        
        winBar = new ProgressBar(150, 650, 400, 50, possibleTenges);
    
    }
    
    /**
     * Consult the profit of a robot
     * @param robotId is the initial location of the robot
     * @param idStore is the location of the shop
     * @return gets the profit in a movement
     */
    public int profitPerMove(int idRobot, int moveIndex){
        
        Robot r = robots.get(idRobot);
        
        return r.profitPerMove(moveIndex);
    
    }
    
    /**
     * Sort the shops by location
     */
    public int[][] stores() {
        int n = shops.size();
        int[][] result = new int[n][2];
        int i = 0;
        
        for(Integer index : shops.keySet()){
        
            result[i][0] = index;
            result[i][1] = shops.get(index).getTenges();
            i++;
        
        }
        // Insertion Sort
        for(int j = 1; j < n; j++){
        
            int[] key = result[j];
            int k = j - 1;
            
            while(k >= 0 && result[k][0] > key[0]){
            
                result[k + 1] = result[k];
                k--;
            
            }
            
            result[k + 1] = key;
        
        }
        
        return result;
    
    }
    
    /**
     * Sort the robots by location
     */
    public int[][] robots(){
        int n = robots.size();
        int[][] result = new int[n][2];
        int i = 0;
        
        for(Integer index : robots.keySet()){
        
            result[i][0] = robots.get(index).getActualLocation();
            result[i][1] = robots.get(index).getGain();
            i++;
        
        }

        for(int j = 1; j < n; j++){
        
            int[] key = result[j];
            int k = j - 1;
            
            while(k >= 0 && result[k][0] > key[0]){
            
                result[k + 1] = result[k];
                k--;
            
            }
            
            result[k + 1] = key;
        
        }
        
        return result;
    
    }
    
    /**
     * Get the ok parameter
     */
    public boolean getOk(){
    
        return ok;
    
    }
    
    /**
     * Get emptied stores ascending by location
     * @return int[][] Bidimensional Array with location and teanges
     */
    public int[][] emptiedStores(){
    
        int totalShops = shops.size();
        int[][] result = new int[totalShops][2];
        int i = 0;
        
        for(Integer index : shops.keySet()){
        
            Shop shop = shops.get(index);
            result[i][0] = shop.getDistanceX();
            result[i][1] = shop.getEmptiedCount();
            i++;
        
        }
        
        for(int j = 1; j < totalShops; j++){
        
            int[] key = result[j];
            int k = j - 1;
            
            while(k > 0 && result[k][0] > key[0]){
            
                result[k + 1] = result[k];
                k--;
            
            }
            
            result[k + 1] = key;
        
        }
        
        return result;
    
    }
    
    /**
     * Start to calculate the highest profit
     */
    public void initializeNeariestRobot() {
        
        if(shops != null && robots != null){
            setNeariestRobots();
        
        }
    }
    
    /**
     * 
     */
    public Robot getRobotWithMajorGain(){
    
        Robot bestRobot = null;
        int maxGain = Integer.MIN_VALUE;
        
        
        for(Robot r : robots.values()){
        
            if(r.getGain() > maxGain){
                maxGain = r.getGain();
                bestRobot = r;
            }
        }
        
        return bestRobot;
    
    }
}