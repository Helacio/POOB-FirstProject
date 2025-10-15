import java.util.ArrayList;

public class Shop
{
    private int shopNumber;
    private int distanceX;
    private int tenges;
    private int initialTenges;
    private ArrayList<Figure> figureShop;
    private int emptiedCount;
    private boolean visible;
    private boolean isEmpty;
    private String color;
    
    /**
     * Constructor for objects of class Shop
     * Index Order: 0: Rectangle(body), 1: Rectangle(door), 2: Triangle(roof)
     */
    public Shop(int distanceX, String color, int tenges)
    {
        this.shopNumber = shopNumber;
        this.distanceX = distanceX;
        this.figureShop = new ArrayList<>();
        this.visible = false;
        this.isEmpty = false;
        this.color = color;
        
        this.figureShop.add(new Rectangle()); //0: body
        this.figureShop.add(new Rectangle()); //1: door
        this.figureShop.add(new Triangle());  //2: roof
        
        ((Rectangle)figureShop.get(0)).changeSize(30, 40);
        ((Rectangle)figureShop.get(1)).changeSize(20, 15);
        ((Triangle)figureShop.get(2)).changeSize(15, 40);
        
        figureShop.get(2).moveVertical(30);
        figureShop.get(0).moveVertical(10);
        figureShop.get(2).moveHorizontal(-70);
        figureShop.get(1).moveVertical(20);
        figureShop.get(1).moveHorizontal(13);
        
        figureShop.get(0).changeColor(color);
        figureShop.get(1).changeColor("red");
        figureShop.get(2).changeColor("blue");
        
        this.tenges = tenges;
        initialTenges = tenges;
        emptiedCount = 0;
    }
    
    /**
     * Resupply the shop
     */
    public void resupply() {
        this.tenges = initialTenges;
        changeInitialColor();
        this.isEmpty = false;
    }
    
    /**
     * Get the position of the shop
     */
    public int getDistanceX() {
        return distanceX;
    }
    
    /**
     * Get the tenges
     */
    public int getTenges() {
        return tenges;
    }
    
    /**
     * Hide the shop of canvas
     */
    public void makeInvisible() {
        for (Figure s : figureShop) {
            s.makeInvisible();
        }
        this.visible = false;
    }
    
    /**
     * Draw the shop in canvas
     */
    public void makeVisible(){
        for (Figure s : figureShop) {
            s.makeVisible();
        }
        this.visible = true;
    }

    /**
     * Ubicate the shop in a specific coordinates
     */
    public void locateShop(int posX, int posY){
        int row = posX;
        int column = posY;
        
        figureShop.get(0).setXPosition(50 + column * 40);
        figureShop.get(0).setYPosition(60 + row * 40);
        
        figureShop.get(2).setXPosition(70 +column * 40);
        figureShop.get(2).setYPosition(45 + row * 40);
        
        figureShop.get(1).setXPosition(63 + column * 40);
        figureShop.get(1).setYPosition(70 + row * 40);
        
        if(this.visible){
            figureShop.get(0).makeVisible();
            figureShop.get(1).makeVisible();
            figureShop.get(2).makeVisible();
        }
    }
    
    public int getEmptiedCount(){
        return emptiedCount;
    }
    
    public int empty(){
        int collected = tenges;
        this.tenges = 0;
        emptiedCount++;
        this.isEmpty = true;
        changeStealColor();
        
        return collected;
    }
    
    /**
     * Change the color appearance when has been stolen
     */
    public void changeStealColor(){
        figureShop.get(0).changeColor("lightGray");
        figureShop.get(1).changeColor("hardGray");
        figureShop.get(2).changeColor("hardGray");
    }
    
    /**
     * Change the color appearance to the initial state
     */
    public void changeInitialColor(){
        figureShop.get(0).changeColor(this.color);
        figureShop.get(1).changeColor("red");
        figureShop.get(2).changeColor("blue");
    }
    
    /**
     * Get the isVisible atribute
     */
    public boolean getIsEmpty(){
        return this.isEmpty;
    }
}