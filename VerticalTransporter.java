/**
 * Class designed to move nutrion vertically from a farmer to a consumer on the same vertical line
 */
public class VerticalTransporter extends Transporter {

    public int capacity;
    public String type = "VT";

    /**
     * Constructor class
     * @param grid
     * @param yCoordinate
     * @param xCoordinate
     * @param capacity
     */
    public VerticalTransporter(Grid grid, int yCoordinate, int xCoordinate, int capacity) {
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.capacity = capacity;
        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    /**
     * Overrides toString method to return the class' type attribute
     * @return
     */
    @Override
    public String toString(){
        return type;
    }

    /**
     * method that will check all the cells on the same vertical line above the VT for an abstract item,
     * then check if that item is a farmer or a consumer, and it if is, return the item, if not, it returns null
     * @return
     */
    public AbstractItem goUp() {
        AbstractItem foundFarmer = null;
        AbstractItem foundConsumer = null;
        for (int i = yCoordinate - 1; i >= 0; i--) {
            AbstractItem myItem = grid.getItem(xCoordinate, i);
            if (myItem instanceof RadishFarmer || myItem instanceof CornFarmer) {
                foundFarmer = myItem;
                return foundFarmer;
            } else if (myItem instanceof Rabbit || myItem instanceof Beaver) {
                foundConsumer = myItem;
                return foundConsumer;
            }
        }

        return null;
    }

    /**
     * method that will check all the cells on the same vertical line below the VT for an abstract item,
     * then check if that item is a farmer or a consumer, and it if is, return the item, if not, it returns null
     * @return
     */
    public AbstractItem goDown(){

        AbstractItem foundFarmer = null;
        AbstractItem foundConsumer = null;
        int gridHeight = grid.getHeight();
        for(int k = yCoordinate; k<gridHeight; k++){
            AbstractItem myItem = grid.getItem(xCoordinate, k);
            if (myItem instanceof RadishFarmer || myItem instanceof CornFarmer) {
                foundFarmer = myItem;
                return foundFarmer;
            } else if (myItem instanceof Rabbit || myItem instanceof Beaver) {
                foundConsumer = myItem;
                return foundConsumer;
            }
        }

        return null;
    }

    /**
     * This method processes the nutrition movement activity of the VT. It works by calling goUp() and goDown() to
     * grab two abstract items. It then uses isFarmerOrConsumer to check if each abstract item is a farmer or a
     * consumer. If one item is a farmer and one item is a consumer, then it will use the transport method to move the
     * stock.
     * @param timeStep
     */
    @Override
    public void process(TimeStep timeStep) {
        AbstractItem firstFound = goUp();
        AbstractItem secondFound = goDown();

        String firstType = isFarmerOrConsumer(firstFound);
        String secondType = isFarmerOrConsumer(secondFound);

        if(firstType.equals("Consumer") && secondType.equals("Farmer")){
            transport(firstFound, secondFound, capacity);
        }
        else if(firstType.equals("Farmer") && secondType.equals("Consumer")){
            transport(secondFound, firstFound, capacity);
        }
        else {
            return;
        }
    }
}