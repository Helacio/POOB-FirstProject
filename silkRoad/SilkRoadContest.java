package silkroad;
import java.util.*;
 
public class SilkRoadContest{
    TreeSet<Integer> robots;
    TreeMap<Integer, Integer> shops;
    SilkRoad game;
    
    /**
     * Constructor class
     */
    public SilkRoadContest(){
        SilkRoad game = new SilkRoad(120);
    }
    
    /**
     * Solve method
     * @param int[][] days input type ICPC problem
     */
    public ArrayList<Integer> solve(int[][]days){
        TreeSet<Integer> robots = new TreeSet<>();
        TreeMap<Integer, Integer> shops = new TreeMap<>();
        ArrayList<Integer>solution = new ArrayList<>();
        int numDays = days.length;
        for(int[] day : days){
            int type = day[0];
            if(type == 1){
                robots.add(day[1]);
            }else if(type == 2){
                shops.put(day[1], day[2]);
            }
            Integer maxProfit = findBestProfit(robots, shops);
            solution.add(maxProfit);
        }
        return solution;
    }
    
    /**
     * Simulates the problem SilkRoad
     * @param int[][] days input type ICPC problem
     */
    public void simulate (int[][]days, boolean slow){
        SilkRoad game = new SilkRoad(120);
        int numDays = days.length;
        game.makeVisible();
        
        for(int[] day : days){
            int type = day[0];
            if(type == 1){
                game.addRobot(day[1]);
            }else if(type == 2){
                game.addShop(day[1], day[2]);
            }
            game.moveToMaxGain(slow);
            game.resetSilkRoad();
        }
    }
    
    /**
     * Preparation method before assign Shops - Robots
     */
    private int findBestProfit(TreeSet<Integer> robots, TreeMap<Integer, Integer> shops){
        if(robots.isEmpty() || shops.isEmpty()){
            return 0;
        }
        
        List<Integer> robotsList = new ArrayList<>(robots);
        List<int[]> shopsList = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : shops.entrySet()){
            shopsList.add(new int[] {entry.getKey(), entry.getValue()});
        }
        
        int r = robotsList.size();
        int s = shopsList.size();
        
        int maxProfit = 0;
        
        int[] assignment = new int[s]; //robot steal store
        maxProfit = tryAssignments(robotsList, shopsList, assignment, 0);
        
        return Math.max(0, maxProfit);
    }
    
    /**
     * Recursively tries to assign all possible shops to robots
     */
    private int tryAssignments(List<Integer> robots, List<int[]> shops, int[] assignment, int shopIndex){
        if(shopIndex == shops.size()){
            return calculateProfit(robots, shops, assignment);
        }
        
        int maxProfit = 0;
        //No assign this shop
        assignment[shopIndex] = -1;
        maxProfit = Math.max(maxProfit, tryAssignments(robots, shops, assignment, shopIndex + 1));
        
        //Try to assign this shop to each robot
        for(int r = 0; r < robots.size(); r++){
            assignment[shopIndex] = r;
            maxProfit = Math.max(maxProfit, tryAssignments(robots, shops, assignment, shopIndex + 1));
        }
        return maxProfit;
    }
    
    
    /**
     * Calculate profit for a specific assignment of shops to robots
     * @return totalProfit total gains robots
     */
    private int calculateProfit(List<Integer> robots, List<int[]> shops,int[] assignment){
        int totalProfit = 0;
        for(int r = 0; r < robots.size(); r++){
            List<Integer> assignedShops = new ArrayList<>();
            List<Integer> tengesList = new ArrayList<>();
            
            for(int i = 0; i < shops.size(); i++){
                if(assignment[i] == r){
                    assignedShops.add(shops.get(i)[0]);
                    tengesList.add(shops.get(i)[1]);
                }
            }
            if(assignedShops.isEmpty()) continue;
        
            int robotPos = robots.get(r);
            int minCost = calculateMinTravelCost(robotPos, assignedShops);
            int revenue = 0;
            for(int t : tengesList){
                revenue += t;
            }
            totalProfit += revenue -minCost;
        }
        return totalProfit;
    }
    
    /**
     * Calculate the minimum cost of travel by shops assigned to a robot
     * @return min cost
     * The robot moves optimal to the right or left.
     */
    private int calculateMinTravelCost(int robotPos, List<Integer> shopsPosition){
        if(shopsPosition.isEmpty()){
            return 0;
        }
        if(shopsPosition.size() == 1){
            return Math.abs(robotPos - shopsPosition.get(0));
        }
        
        Collections.sort(shopsPosition);
        int minPos = shopsPosition.get(0);
        int maxPos = shopsPosition.get(shopsPosition.size() - 1);
        
        int cost1 = Math.abs(robotPos - minPos) + (maxPos - minPos);
        int cost2 = Math.abs(robotPos -maxPos) + (maxPos - minPos);
        
        return Math.min(cost1, cost2);
    }

}