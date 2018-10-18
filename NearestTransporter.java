public class NearestTransporter extends AbstractItem {

    public int capacity;
    public String type = "NT";
    public AbstractItem nearestFarmer = null;
    public double NFDistance = Double.POSITIVE_INFINITY;
    public AbstractItem nearestConsumer = null;
    public double NCDistance = Double.POSITIVE_INFINITY;
    boolean equidistant = false;

    public NearestTransporter(Grid grid, int yCoordinate, int xCoordinate, int capacity) {
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
     * This method takes an AbstractItem as an argument and calculates its straight line distance from this instance of
     * the NearestTransporters using the Pythagorean distance formula. It breaks the formula into two parts so it can
     * type cast the grid distances, stored as integers, into doubles.
     * @param currentItem
     * @return
     */
    public double distance(AbstractItem currentItem) {
        double distanceSquared = (double)((currentItem.yCoordinate - yCoordinate)*(currentItem.yCoordinate - yCoordinate) + (currentItem.xCoordinate - xCoordinate)*(currentItem.xCoordinate - xCoordinate));
        return Math.sqrt(distanceSquared);
    }

    public void search(TimeStep timeStep){
        for (int i=0; i<grid.getHeight(); i++){
            for (int j=0; j<grid.getWidth(); j++){
                AbstractItem currentCell = grid.getItem(j, i);
                if(currentCell != null){
                    double checkDistance = distance(currentCell);
                    if(isFarmerOrConsumer(currentCell).equals("Farmer") && checkDistance == NFDistance && currentCell != nearestFarmer){
                        equidistant = true;
                    }
                    if(isFarmerOrConsumer(currentCell).equals("Farmer") && checkDistance < NFDistance){
                        nearestFarmer = currentCell;
                        NFDistance = checkDistance;
                    }
                    if (isFarmerOrConsumer(currentCell).equals("Consumer") && checkDistance == NCDistance && currentCell != nearestConsumer) {
                        equidistant = true;
                    }
                    if (isFarmerOrConsumer(currentCell).equals("Consumer") && checkDistance < NCDistance) {
                        nearestConsumer = currentCell;
                        NCDistance = checkDistance;
                    }
                }
            }
        }
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
        int farmerStock = grid.getStockAt(second.xCoordinate, second.yCoordinate);
        if(farmerStock > capacity){
            second.reduceStock(capacity);
            grid.addToStockAt(first.xCoordinate, first.yCoordinate, capacity);
        }
        else {
            second.reduceStock(farmerStock);
            grid.addToStockAt(first.xCoordinate, first.yCoordinate, farmerStock);
        }
    }

    /**
     * This method processes the activity of the NearestTransporter by first calling the search method to find the
     * nearest consumer and farmer. Then it double checks that they are instances of farmer or consumer classes, and
     * that those obejcts are not equidistant this instance of NearestTransporter as other farmers or consumers are.
     * It then calls the transport method to carry out the movement of stock. 
     * @param timeStep
     */
    @Override
    public void process(TimeStep timeStep) {
        search(timeStep);
        String checkConsumer = isFarmerOrConsumer(nearestConsumer);
        String checkFarmer = isFarmerOrConsumer(nearestFarmer);

        if(checkConsumer.equals("Consumer") && checkFarmer.equals("Farmer") && !equidistant){
            transport(nearestConsumer, nearestFarmer);
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
        return grid.getStockAt(xCoordinate, yCoordinate);
    }

    @Override
    protected void addToStock(int nutrition){

    }

    @Override
    protected void reduceStock(int nutrition){

    }
}