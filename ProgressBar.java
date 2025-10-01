
/**
 * Write a description of class ProgressBar here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ProgressBar
{
    private Rectangle background;
    private Rectangle progress;
    private int max;
    private int actual;
    
    /**
     * Constructor class
     * @param x horizontal coordinate in canvas
     * @param y vertical coordinate in canvas.
     * @param width horizontal long bar
     * @param height vertical long bar
     * @param max highest possible value to get
     */
    public ProgressBar(int x, int y, int width, int height, int max){
        this.max = max;
        this.actual = 0;
        
        background = new Rectangle();
        background.changeSize(height, width);
        background.moveHorizontal(x);
        background.moveVertical(y);
        background.changeColor("gray");
        background.makeVisible();
        
        progress = new Rectangle();
        progress.changeSize(height, 0);
        progress.moveHorizontal(x);
        progress.moveVertical(y);
        progress.changeColor("green");
        progress.makeVisible();
    }
    
    public void update(int valor) {
        this.actual = Math.min(valor, max);
        int newWidth = (int)((double)actual / max * background.getWidht());
        progress.changeSize(background.getHeight(), newWidth);
    }
    
    public void reset(int height) {
        progress.changeSize(height, 0);
    }
}