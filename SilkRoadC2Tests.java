import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
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
        assertTrue(silkRoad.getOk());
    }
    @Test
    public void shouldAddRobot(){
        SilkRoad silkRoad = new SilkRoad(15);
        silkRoad.addRobot(5);
        
        assertTrue(silkRoad.getOk());
    }
    @Test
    public void shouldAddShop(){
        SilkRoad silkRoad = new SilkRoad(15);
        silkRoad.addShop(8, 25);
        
        assertTrue(silkRoad.getOk());
    }
    @Test
    public void shoudFailWithTwoRobotsInSamePosition(){
        SilkRoad silkRoad = new SilkRoad(15);
        silkRoad.addRobot(5);
        silkRoad.addRobot(5);
        assertFalse(silkRoad.getOk());
    }
    @Test
    public void shoudFailWithTwoShopsInSamePosition(){
        SilkRoad silkRoad = new SilkRoad(15);
        silkRoad.addShop(8, 25);
        silkRoad.addShop(8, 25);
        assertFalse(silkRoad.getOk());
    }
    @Test
    public void shouldMoveRobot(){
        int[] input = {1, 10, 2, 15, 20};
        SilkRoad silkRoad = new SilkRoad(input);
        
        silkRoad.moveRobot(10, 15);
        assertTrue(silkRoad.getOk());
    }
    @Test
    public void ShouldFailWhenMoveRobotFails() {
        int[] input = {1, 10, 2, 15, 20};
        SilkRoad silkRoad = new SilkRoad(input);
        
        silkRoad.moveRobot(99, 15);
        assertFalse(silkRoad.getOk());
    }
    @Test
    public void shouldReturnProfitPermove(){
        int[] input = {1, 10, 2, 15 ,25};
        SilkRoad silkRoad = new SilkRoad(input);
        
        Robot r = silkRoad.getRobots().get(10);
        
        silkRoad.moveRobot(10, 15);
        int profit = silkRoad.profitPerMove(10, 0);
        
        assertEquals(20, profit);
    }
    @Test
    public void shouldSortRobotsAscendingByLocation(){
        SilkRoad silkRoad = new SilkRoad(20);
        silkRoad.addRobot(5);
        silkRoad.addRobot(2);
        silkRoad.addRobot(10);
        
        int[][] sorted = silkRoad.robots();
        
        assertEquals(2, sorted[0][0]);
        assertEquals(5, sorted[1][0]);
        assertEquals(10, sorted[2][0]);
    }
    @Test
    public void shouldSortShopsAscendingByLocation(){
        SilkRoad silkRoad = new SilkRoad(20);
        silkRoad.addShop(5, 30);
        silkRoad.addShop(10, 35);
        silkRoad.addShop(19, 27);
        
        int[][] sorted = silkRoad.stores();
        
        assertEquals(5, sorted[0][0]);
        assertEquals(10, sorted[1][0]);
        assertEquals(19, sorted[2][0]);
    }
}