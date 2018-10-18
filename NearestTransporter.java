public class NearestTransporter extends Transporter {

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
            transport(nearestConsumer, nearestFarmer, capacity);
        }
        else {
            return;
        }
    }
}