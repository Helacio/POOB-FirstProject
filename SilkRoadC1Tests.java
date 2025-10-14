import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test class SilkRoadC2
 *
 * @author sanchez-villagran
 * 
 */
public class SilkRoadC1Tests
{
    @Test
    public void accordingSVshouldCreateSilkRoadEmpty(){
        SilkRoad silkRoad = new SilkRoad(10);
        assertTrue("SilkRoad should be created succesfully", silkRoad.getOk());
    }
    
    @Test
    public void accordingSVshouldAddRobot(){
        SilkRoad silkRoad = new SilkRoad(15);
        silkRoad.addRobot(5);
        
        assertTrue("Robot should be added succesfully", silkRoad.getOk());
    }
    
    @Test
    public void accordingSVshouldAddShop(){
        SilkRoad silkRoad = new SilkRoad(15);
        silkRoad.addShop(8, 25);
        
        assertTrue("Shop should be added succesfully", silkRoad.getOk());
    }
    
    @Test
    public void accordingSVshoudntAddTwoRobotsInSamePosition(){
        SilkRoad silkRoad = new SilkRoad(15);
        silkRoad.addRobot(5);
        silkRoad.addRobot(5);
        assertFalse("Should fail when two robots are in the same position", silkRoad.getOk());
    }
    
    @Test
    public void accordingSVshoudntAddTwoShopsInSamePosition(){
        SilkRoad silkRoad = new SilkRoad(15);
        silkRoad.addShop(8, 25);
        silkRoad.addShop(8, 25);    
        silkRoad.makeInvisible();
        assertFalse("Should fail whe two shops are in the same position", silkRoad.getOk());
    }
    
    @Test
    public void accordingSVshouldMoveRobot(){
        SilkRoad silkRoad = new SilkRoad(30);
        silkRoad.addRobot(10); 
        silkRoad.addShop(15, 40); 
        
        silkRoad.moveRobot(10, 15);
        assertTrue("Robot should move succesfully", silkRoad.getOk());
    }
    
    @Test
    public void accordingSVShouldntMoveRobot() {
        SilkRoad silkRoad = new SilkRoad(15);
        
        silkRoad.moveRobot(99, 15);
        assertFalse("Should fail when trying to move a non exisstent robot", silkRoad.getOk());
    }
}