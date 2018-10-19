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
                System.out.println("The tiger ate the beaver because he is a BOSS");
                grid.registerItem(newX, newY, null);
                grid.registerItem(newX, newY, this);
                grid.registerItem(xCoordinate, yCoordinate, null);
                xCoordinate = newX;
                yCoordinate = newY;
            }
            else if(checkCell instanceof Rabbit){
                grid.recordConsumption(75);
                System.out.println("The tiger ate the rabbit because he is a BOSS");
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
                System.out.println("The farmer killed the tiger because he is a HATER");
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
     *
     * @param timeStep
     */
    @Override
    public void process(TimeStep timeStep) {
        if(getStock() > 0){
            consume(20, 5);
            longTermEnergy += 1;
        }

        if(longTermEnergy > 0 && timeStep.getValue() % 5 == 0){
            moveAndEat();
            longTermEnergy = 0;
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