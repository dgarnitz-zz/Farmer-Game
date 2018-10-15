public class HorizontalTransporter extends AbstractItem {

    public int capacity;
    public String type = "HT";

    public HorizontalTransporter(Grid grid, int yCoordinate, int xCoordinate, int capacity) {
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.capacity = capacity;
        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    @Override
    public String toString(){
        return type;
    }

    public AbstractItem goLeft() {
        AbstractItem foundFarmer = null;
        AbstractItem foundConsumer = null;
        for (int i = xCoordinate - 1; i >= 0; i--) {
            AbstractItem myItem = grid.getItem(i, yCoordinate);
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
    public AbstractItem goRight(){

        AbstractItem foundFarmer = null;
        AbstractItem foundConsumer = null;
        int gridWidth = grid.getWidth();
        for(int k = xCoordinate; k<gridWidth; k++){
            AbstractItem myItem = grid.getItem(k, yCoordinate);
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

    public String isFarmerOrConsumer(AbstractItem first){
        String itemType;

        if(first instanceof RadishFarmer || first instanceof CornFarmer){
            itemType = "Farmer";
        }
        else if (first instanceof Beaver || first instanceof Rabbit){
            itemType = "Consumer";
        }
        else {
            itemType = "ineligible";
        }

        return itemType;
    }

    @Override
    public void process(TimeStep timeStep) {
        AbstractItem firstFound = goLeft();
        AbstractItem secondFound = goRight();

        String firstType = isFarmerOrConsumer(firstFound);
        String secondType = isFarmerOrConsumer(secondFound);

        if(firstType.equals("Consumer") && secondType.equals("Farmer")){
            int farmerStock = grid.getStockAt(secondFound.xCoordinate, yCoordinate);
            if(farmerStock > capacity){
                secondFound.reduceStock(capacity);
                grid.addToStockAt(firstFound.xCoordinate, yCoordinate, capacity);
            }
            else {
                secondFound.reduceStock(farmerStock);
                grid.addToStockAt(firstFound.xCoordinate, yCoordinate, farmerStock);
            }
        }
        else if(firstType.equals("Farmer") && secondType.equals("Consumer")){
            int farmerStock = grid.getStockAt(firstFound.xCoordinate, yCoordinate);
            if(farmerStock > capacity){
                firstFound.reduceStock(capacity);
                grid.addToStockAt(secondFound.xCoordinate, yCoordinate, capacity);
            }
            else {
                firstFound.reduceStock(farmerStock);
                grid.addToStockAt(secondFound.xCoordinate, yCoordinate, farmerStock);
            }
        }
        else {
            return;
        }
    }

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