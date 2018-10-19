import java.util.Random;

public class Tiger extends Consumer {

    public String type = "Tiger";
    private int longTermEnergy = 0;

    /**
     * Class constructor method
     * @param grid
     * @param yCoordinate
     * @param xCoordinate
     */
    public Tiger(Grid grid, int yCoordinate, int xCoordinate) {
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    /**
     * This method generates random two numbers using Java’s Random class between 0 and the height or width of the grid.
     * Those two random numbers will be the coordinates of the location where the tiger will try to move. The method
     * calls getItem on that location. If there is not an item there, it moves the tiger to that location. If there is
     * a Beaver located there, consumption of 50 is recorded, the Beaver is eaten, meaning the item is registered as
     * null, then the Tiger moves to that cell in the grid.  If there is a Rabbit located there, consumption of 75 is
     * recorded, the Rabbit is eaten, meaning the item is registered as null, then the Tiger moves to that cell in the
     * grid. If there is a Transporter located there, nothing happens. Lastly, if there is a Farmer there, the Tiger is
     * “killed,” meaning that it is registered as null, thus deleting it.
     */
    public void moveAndEat(){
        int xRange = grid.getWidth()-1;
        int yRange = grid.getHeight()-1;
        Random movement = new Random();
        int newX = movement.nextInt(xRange);
        int newY = movement.nextInt(yRange);

        AbstractItem checkCell = grid.getItem(newX, newY);

        if(checkCell != null){
            if(checkCell instanceof Beaver){
                grid.recordConsumption(50);
                grid.registerItem(newX, newY, null);
                grid.registerItem(newX, newY, this);
                grid.registerItem(xCoordinate, yCoordinate, null);
                xCoordinate = newX;
                yCoordinate = newY;
            }
            else if(checkCell instanceof Rabbit){
                grid.recordConsumption(75);
                grid.registerItem(newX, newY, null);
                grid.registerItem(newX, newY, this);
                grid.registerItem(xCoordinate, yCoordinate, null);
                xCoordinate = newX;
                yCoordinate = newY;
            }
            else if(checkCell instanceof Transporter){
                return;
            }
            else {
                grid.registerItem(xCoordinate, yCoordinate, null);
                return;
            }
        }
        else {
            grid.registerItem(newX, newY, this);
            grid.registerItem(xCoordinate, yCoordinate, null);
            xCoordinate = newX;
            yCoordinate = newY;
        }
    }

    /**
     * This method processes the activity of the Tiger. If the stock is greater than zero, it calls the Consumer
     * class' consume method and increments the Tiger's longTermEnergy. If the longTermEnergy is above 3, meaning the
     * Tiger has called consume at least 3 times, it calls the moveAndEat method.
     * @param timeStep
     */
    @Override
    public void process(TimeStep timeStep) {
        if(getStock() > 0){
            consume(20, 5);
            longTermEnergy += 1;
        }

        if(longTermEnergy > 3 && timeStep.getValue() % 5 == 0){
            moveAndEat();
        }
    }

    /**
     * Overrides toString method to return the class' type attribute and along with the stock at the Beaver's cell
     * @return
     */
    @Override
    public String toString(){
        int stock = getStock();
        return type + "(" + stock + ")";
    }
}