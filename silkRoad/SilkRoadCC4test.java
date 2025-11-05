package silkroad;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
/**
 * The test class SilkRoadCC4test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SilkRoadCC4test {
    
    
    /**
     * Default constructor for test class SilkRoadCC4test
     */
    public SilkRoadCC4test()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
        
        SilkRoad silkRoad = new SilkRoad(200);
        
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Test
    public void setFighter() {
        
        SilkRoad silkRoad = new SilkRoad(200);
        silkRoad.addFighter(25, "red", 10);
        
    }
    
    @Test
    public void setAutonomous() {
        
        SilkRoad silkRoad = new SilkRoad(120);
        silkRoad.addAutonomous(10, "green", 20);
        
    }
    
    @Test 
    public void setTender() {
        
        SilkRoad silkRoad = new SilkRoad(120);
        silkRoad.addTender(30, "blue");
    
    }
    
    @Test
    public void setNeverBack() {
        
        SilkRoad silkRoad = new SilkRoad(120);
        silkRoad.addNeverBack(35, "white");
        
    }
    
    @Test
    public void tenderToShop() {
        
        SilkRoad silkRoad = new SilkRoad(120);
        silkRoad.addTender(30, "blue");
        silkRoad.addShop(40, 10);
        System.out.println(silkRoad.getRobots().get(30).getProfitsPerMove());
        silkRoad.moveRobot(30, 40);
        System.out.println(silkRoad.getRobots().get(30).getProfitsPerMove());
        
    }
    
    @AfterEach
    public void resetAll() {
        
    }
    
    
}
