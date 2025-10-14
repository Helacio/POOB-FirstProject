import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Write a description of class SilkRoadC2Tests here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SilkRoadC2Tests
{
    @Test
    public void shouldCreateSilkRoadEmpty(){
        SilkRoad silkRoad = new SilkRoad(10);
        silkRoad.makeInvisible();
        assertTrue("SilkRoad should be created succesfully", silkRoad.getOk());
    }
    @Test
    public void shouldAddRobot(){
        SilkRoad silkRoad = new SilkRoad(15);
        silkRoad.addRobot(5);
        silkRoad.makeInvisible();
        
        assertTrue("Robot should be added succesfully", silkRoad.getOk());
    }
    @Test
    public void shouldAddShop(){
        SilkRoad silkRoad = new SilkRoad(15);
        silkRoad.addShop(8, 25);
        silkRoad.makeInvisible();
        
        assertTrue("Shop should be added succesfully", silkRoad.getOk());
    }
    @Test
    public void shoudFailWithTwoRobotsInSamePosition(){
        SilkRoad silkRoad = new SilkRoad(15);
        silkRoad.addRobot(5);
        silkRoad.addRobot(5);
        silkRoad.makeInvisible();
        assertFalse("Should fail when two robots are in the same position", silkRoad.getOk());
    }
    @Test
    public void shoudFailWithTwoShopsInSamePosition(){
        SilkRoad silkRoad = new SilkRoad(15);
        silkRoad.addShop(8, 25);
        silkRoad.addShop(8, 25);
        silkRoad.makeInvisible();
        assertFalse("Should fail whe two shops are in the same position", silkRoad.getOk());
    }
    @Test
    public void shouldMoveRobot(){
        int[] input = {1, 10, 2, 15, 20};
        SilkRoad silkRoad = new SilkRoad(input);
        
        silkRoad.moveRobot(10, 15);
        silkRoad.makeInvisible();
        assertTrue("Robot should move succesfully", silkRoad.getOk());
    }
    @Test
    public void ShouldFailWhenMoveRobotFails() {
        int[] input = {1, 10, 2, 15, 20};
        SilkRoad silkRoad = new SilkRoad(input);
        
        silkRoad.moveRobot(99, 15);
        silkRoad.makeInvisible();
        assertFalse("Should fail when trying to move a non exisstent robot", silkRoad.getOk());
    }
    @Test
    public void shouldReturnProfitPermove(){
        int[] input = {1, 10, 2, 15 ,25};
        SilkRoad silkRoad = new SilkRoad(input);
        silkRoad.makeInvisible();
        
        Robot r = silkRoad.getRobots().get(10);
        
        silkRoad.moveRobot(10, 15);
        int profit = silkRoad.profitPerMove(10, 0);
        
        assertEquals("Profit per move should be calculated correctly", 20, profit);
    }
    @Test
    public void shouldSortRobotsAscendingByLocation(){
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
    public void shouldSortShopsAscendingByLocation(){
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
}