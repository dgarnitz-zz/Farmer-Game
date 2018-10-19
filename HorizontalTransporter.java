public class HorizontalTransporter extends Transporter {

    public int capacity;
    public String type = "HT";

    public HorizontalTransporter(Grid grid, int yCoordinate, int xCoordinate, int capacity) {
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
     * method that will check all the cells on the same horizontal line left of the HT for an abstract item,
     * then check if that item is a farmer or a consumer, and it if is, return the item, if not, it returns null
     * @return
     */
    public AbstractItem goLeft() {
        AbstractItem foundFarmer = null;
        AbstractItem foundConsumer = null;
        for (int i = xCoordinate - 1; i >= 0; i--) {
            AbstractItem myItem = grid.getItem(i, yCoordinate);
            if (myItem instanceof Farmer) {
                foundFarmer = myItem;
                return foundFarmer;
            } else if (myItem instanceof Consumer) {
                foundConsumer = myItem;
                return foundConsumer;
            }
        }

        return null;
    }

    /**
     * method that will check all the cells on the same horizontal line right of the HT for an abstract item,
     * then check if that item is a farmer or a consumer, and it if is, return the item, if not, it returns null
     * @return
     */
    public AbstractItem goRight(){

        AbstractItem foundFarmer = null;
        AbstractItem foundConsumer = null;
        int gridWidth = grid.getWidth();
        for(int k = xCoordinate; k<gridWidth; k++){
            AbstractItem myItem = grid.getItem(k, yCoordinate);
            if (myItem instanceof Farmer) {
                foundFarmer = myItem;
                return foundFarmer;
            } else if (myItem instanceof Consumer) {
                foundConsumer = myItem;
                return foundConsumer;
            }
        }

        return null;
    }

    /**
     * This method processes the nutrition movement activity of the HT. It works by calling goLeft() and goRight() to
     * grab two abstract items. It then uses isFarmerOrConsumer to check if each abstract item is a farmer or a
     * consumer. If one item is a farmer and one item is a consumer, then it will use the transport method to move the
     * stock.
     * @param timeStep
     */
    @Override
    public void process(TimeStep timeStep) {
        AbstractItem firstFound = goLeft();
        AbstractItem secondFound = goRight();

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