import java.util.ArrayList;

public class Shop
{
    private int shopNumber;
    private int distanceX;
    private int tenges;
    private int initialTenges;
    private ArrayList<Shape> figureShop;
    
    /**
     * Constructor for objects of class Shop
     * Index Order: 0: Rectangle(body), 1: Rectangle(door), 2: Triangle(roof)
     */
    public Shop(int distanceX, String color, int tenges)
    {
        this.shopNumber = shopNumber;
        this.distanceX = distanceX;
        this.figureShop = new ArrayList<>();
        
        this.figureShop.add(new Rectangle()); // 0: body
        this.figureShop.add(new Rectangle()); // 1: door
        this.figureShop.add(new Triangle());  // 2: roof
        
        ((Rectangle)figureShop.get(0)).changeSize(30, 40);
        ((Rectangle)figureShop.get(1)).changeSize(20, 15);
        ((Triangle)figureShop.get(2)).changeSize(15, 40);
        
        
        figureShop.get(2).moveVertical(30);
        figureShop.get(0).moveVertical(10);
        figureShop.get(2).moveHorizontal(-70);
        figureShop.get(1).moveVertical(20);
        figureShop.get(1).moveHorizontal(13);
        
        if (distanceX > 0) {
            // Apply initial offset
            figureShop.get(0).moveHorizontal(distanceX);
            figureShop.get(1).moveHorizontal(distanceX);
            figureShop.get(2).moveHorizontal(distanceX-70);
            this.distanceX = distanceX;
        }
    
        
        figureShop.get(0).changeColor(color);
        figureShop.get(1).changeColor("red");
        figureShop.get(2).changeColor("blue");
        
        this.tenges = tenges;
        initialTenges = tenges;
    }
    
    public void resupply() {
        tenges = initialTenges;
    }
    
    public int getDistanceX() {
        return distanceX;
    }
    
    public int getTenges() {
        return tenges;
    }
    
    public void makeInvisible() {
        for (Shape s : figureShop) {
            s.makeInvisible();
        }
    }

    public void locateShop(int posX, int posY){
        int row = posX;
        int column = posY;
        
        // 0: body 
        figureShop.get(0).setXPosition(50 + column * 40);
        figureShop.get(0).setYPosition(60 + row * 40);
        // 2: roof 
        figureShop.get(2).setXPosition(70 +column * 40);
        figureShop.get(2).setYPosition(45 + row * 40);
        // 1: door 
        figureShop.get(1).setXPosition(63 + column * 40);
        figureShop.get(1).setYPosition(70 + row * 40);
        
        // Make visible
        figureShop.get(0).makeVisible();
        figureShop.get(1).makeVisible();
        figureShop.get(2).makeVisible();
    }
}