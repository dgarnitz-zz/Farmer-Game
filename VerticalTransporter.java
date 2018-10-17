/**
 * Class designed to move nutrion vertically from a farmer to a consumer on the same vertical line
 */
public class VerticalTransporter extends AbstractItem {

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
     * Method takes the one of the two abstract items found in the process method, checks if it is an instance of a
     * farmer or a consumer, if it is, returns a string with the either "farmer" or "consumer", otherwise returns a
     * null string
     * @param first
     * @return
     */
    public String isFarmerOrConsumer(AbstractItem currentItem){
        String itemType;

        if(currentItem instanceof RadishFarmer || currentItem instanceof CornFarmer){
            itemType = "Farmer";
        }
        else if (currentItem instanceof Beaver || currentItem instanceof Rabbit){
            itemType = "Consumer";
        }
        else {
            itemType = "ineligible";
        }

        return itemType;
    }

    /**
     * Takes two abstract items, a consumer and a farmer respectively, grabs the stock at the first one, checks if its
     * greater than the capacity of the transporter, then reduces the farmer's stock by either the capacity or the
     * amount of the stock if its less than the capacity. It then adds that amount to the stock at the consumer's
     * location.
     * @param first
     * @param second
     */
    public void transport(AbstractItem first, AbstractItem second){
        int farmerStock = grid.getStockAt(xCoordinate, second.yCoordinate);
        if(farmerStock > capacity){
            second.reduceStock(capacity);
            grid.addToStockAt(xCoordinate, first.yCoordinate, capacity);
        }
        else {
            second.reduceStock(farmerStock);
            grid.addToStockAt(xCoordinate, first.yCoordinate, farmerStock);
        }
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
            transport(firstFound, secondFound);
        }
        else if(firstType.equals("Farmer") && secondType.equals("Consumer")){
            transport(secondFound, firstFound);
        }
        else {
            return;
        }
    }

    /**
     * Method to get the stock at the cell the VT is in
     * @return
     */
    @Override
    protected int getStock(){
        return grid.getStockAt(yCoordinate, xCoordinate);
    }

    @Override
    protected void addToStock(int nutrition){

    }

    @Override
    protected void reduceStock(int nutrition){

    }
}