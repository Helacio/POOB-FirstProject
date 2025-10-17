
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
        
        progress = new Rectangle();
        progress.changeSize(height, 0);
        progress.moveHorizontal(x);
        progress.moveVertical(y);
        progress.changeColor("green");
    }

    /**
     * Change the progress bar
     * @param valor is the value to fill the progress bar
     */
    public void update(int valor) {
        this.actual = Math.min(valor, max);
        int newWidth = (int)((double)actual / max * background.getWidht());
        progress.changeSize(background.getHeight(), newWidth);
    }
    
    /**
     * Change the color of progress bar like orignally
     * @param int is the vertical len of the barr progress
     */
    public void reset(int height) {
        progress.changeSize(height, 0);
    }
    
    /**
     * Change the max parameter
     * @param max Is the value to change
     */
    public void setMax(int max){
        this.max = max;
    }
    
    /**
     * Draw the progressbar on canvas
     */
    public void makeVisible(){
        background.makeVisible();
        progress.makeVisible();
    }
    
    /**
     * Hide the progressbar on canvas
     */
    public void makeInvisible(){
        progress.makeInvisible();
        background.makeInvisible();
    }
}