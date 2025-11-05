package silkroad;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


public class SilkRoadC3Tests
{
    @Test
    public void shouldCreateSolutions(){
        SilkRoadContest game = new SilkRoadContest();
        ArrayList<Integer> solutionArray = game.solve(new int[][]{{1,20},{2, 15,15},{2,40,50}});
        assertNotNull(solutionArray);
    }
    
    @Test
    public void shouldCreateSolutionsForEachInput(){
        SilkRoadContest game = new SilkRoadContest();
        ArrayList<Integer> solutionArray = game.solve(new int[][]{{1,20},{2, 15,15},{2,40,50}});
        int lenSolution = solutionArray.size();
        assertEquals(lenSolution, 3);
    }
    
    @Test 
    public void shouldHaveCorrectAnswers(){
        SilkRoadContest game = new SilkRoadContest();
        ArrayList<Integer> solutionArray = game.solve(new int[][]{{1,20},{2, 15,15},{2,40,50}});
        assertEquals(solutionArray.get(0), 0);
        assertEquals(solutionArray.get(1), 10);
        assertEquals(solutionArray.get(2), 35);
    }
    
    @Test 
    public void shouldGetZeroWhenTheresNoShops(){
        SilkRoadContest game = new SilkRoadContest();
        ArrayList<Integer> solutionArray = game.solve(new int[][]{{1,20},{1, 30}});
        assertEquals(solutionArray.get(0), 0);
        assertEquals(solutionArray.get(1), 0);
    }
    
    @Test 
    public void shouldGetZeroWhenTheresNoRobtos(){
        SilkRoadContest game = new SilkRoadContest();
        ArrayList<Integer> solutionArray = game.solve(new int[][]{{2, 15,15},{2,40,50}});
        assertEquals(solutionArray.get(0), 0);
        assertEquals(solutionArray.get(1), 0);
    }
    
    @Test
    public void shouldGetTotalTengesIfTheresNoMovement(){
        SilkRoadContest game = new SilkRoadContest();
        ArrayList<Integer> solutionArray = game.solve(new int[][]{{1,15},{2, 15,15},});
        assertEquals(solutionArray.get(0), 0);
        assertEquals(solutionArray.get(1), 15);
    }
}
