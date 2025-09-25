
/**
 * Write a description of class Shape here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public abstract class Shape
{
    // instance variables - replace the example below with your own
    protected String color;
    protected boolean isVisible;
    protected int xPosition;
    protected int yPosition;
    
    protected Shape(int x, int y, String color)
    {
        this.xPosition = x;
        this.yPosition = y;
        this.color = color;
    }
    
    /**
     * Make this shape visible. If it was already visible, do nothing.
     */
    protected void makeVisible() {
        isVisible = true;
        draw();
    }
    
    /**
     * Make this shape invisible. If it was already invisible, do nothing.
     */
    protected void makeInvisible() {
        isVisible = false;
        draw();
    }
    
    /**
     * Move the shape a few pixels to the right.
     */
    protected void moveRigth(int distance) {
        erase();
        yPosition += distance;
        draw();
    }
    
    /**
     * Move the shape a few pixels to the left.
     */
    protected void moveLeft() {
        moveHorizontal(-20);
    }
    
    /**
     * Move the shape a few pixels up.
     */
    protected void moveUp() {
        moveVertical(-20);
    }
    
    /**
     * Move the shape a few pixels down.
     */
    protected void moveDown() {
        moveVertical(20);
    }
    
    /**
     * Move the shape vertically.
     * @param distance the desired distance in pixels
     */
    protected void moveVertical(int distance) {
        erase();
        yPosition += distance;
        draw();
    }
    
    /**
     * Move the shape horizontally.
     * @param distance the desired distance in pixels
     */
    protected void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        draw();
    }
    
    /**
     * Slowly move the shape horizontally.
     * @param distance the desired distance in pixels
     */
    protected void slowMoceHorizontal(int distance) {
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }
    
    /**
     * Slowly move the shape vertically.
     * @param distance the desired distance in pixels
     */
    protected void slowMoveVertical(int distance) {
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }
    
    /**
     * Change the shape's color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "black", "white", "magenta", "orange", "pink", "cyan", "gray", "lightGray",
     * "darkGray", "brown" and "maroon".
     */
    protected void changeColor(String newColor) {
        color = newColor;
        draw();
    }
    
    /**
     * Get position x of shape
     * 
     * @return Position x of shape
     */    
    public int getXPosition(){
        return xPosition;
    }
    
    
    /**
     * Get position y of shape
     * 
     * @return Position y of shape
     */    
    public int getYPosition(){
        return yPosition;
    }
    
    
    /**
     * Set new position x of shape
     * 
     */    
    public void setXPosition(int x){
        this.xPosition = x;
    }

    /**
     * Set new position y of shape
     * 
     */  
    public void setYPosition(int y){
        this.yPosition = y;
    }
    
    /**
     * Erasing shape on canvas
     * 
     */  
    protected void erase() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Draw the shape on canvas
     */
    public abstract void draw();
}