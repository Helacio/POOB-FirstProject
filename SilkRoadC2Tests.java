import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * Test class SilkRoadC2
 *
 * @author sanchez-villagran
 * 
 */
public class SilkRoadC2Tests
{
    int[] input;
    
    @Before
    public void setUp(){
        input = new int[]{1, 10, 2, 15 ,25, 2, 40, 50};
    }
    
    
    @Test
    public void accordingSVshouldCreateSilkRoadMaratonInput(){
        SilkRoad silkRoad = new SilkRoad(input);
        
        assertTrue("Shoud create a silkRoad with maraton input", silkRoad.getOk());
    }
    
    @Test
    public void accordingSVshouldntCreateSilkRoadWithInvalidInput(){
        int[] input2 = new int[]{3, 10, 4, 15 ,25};
        SilkRoad silkRoad2 = new SilkRoad(input2);
        assertFalse("The silkRoad shouldn't create with an invalid input", silkRoad2.getOk());
    }
    
    @Test
    public void accordingSVshouldHave2ShopsAndOneRobot(){
        SilkRoad silkRoad = new SilkRoad(input);
        int numShops = (silkRoad.getShops().keySet()).size();
        int numRobots = (silkRoad.getRobots().keySet()).size();
        
        assertEquals("SilkRoad should have two shops", 2, numShops);
        assertEquals("SilkRoad sohuld hace two robots", 1, numRobots);
    }
    
    @Test
    public void accordindSVshouldCheckProfitPermove(){
        SilkRoad silkRoad = new SilkRoad(input);
        
        Robot r = silkRoad.getRobots().get(10);
        
        silkRoad.moveRobot(10, 15);
        int profit = silkRoad.profitPerMove(10, 0);

        assertEquals("Profit per move should be calculated correctly", 20, profit);
    }
    
    @Test
    public void accordindSVshouldSortShopsAscendingByLocation(){
        SilkRoad silkRoad = new SilkRoad(20);
        silkRoad.addShop(5, 30);
        silkRoad.addShop(10, 35);
        silkRoad.addShop(19, 27);
        silkRoad.makeInvisible();
        
        int[][] sorted = silkRoad.stores();
        
        assertEquals("First shop should have the lowest position", 5, sorted[0][0]);
        assertEquals("Second shop should have the middle position", 10, sorted[1][0]);
        assertEquals("Third shop should have the highest position", 19, sorted[2][0]);
    }
    
    @Test
    public void accordindSVshouldSortRobotsAscendingByLocation(){
        SilkRoad silkRoad = new SilkRoad(20);
        silkRoad.addRobot(5);
        silkRoad.addRobot(2);
        silkRoad.addRobot(10);
        silkRoad.makeInvisible();
        
        int[][] sorted = silkRoad.robots();
        
        assertEquals("First robot should have the lowest position", 2, sorted[0][0]);
        assertEquals("Second robot should have the middle position", 5, sorted[1][0]);
        assertEquals("Third robot should have the highest position",10, sorted[2][0]);
    }
    
    @Test
    public void accordingSVshouldHaveProfitAfterMove(){
        SilkRoad silkRoad = new SilkRoad(30);
        silkRoad.addRobot(10); 
        silkRoad.addShop(15, 40);
        
        silkRoad.moveRobot(10, 15);
        int gains = silkRoad.getRobots().get(10).getGain();
        
        assertTrue("Robot should move succesfully", silkRoad.getOk());
        assertTrue("Robot sgould have gains", gains > 0);
    }
}