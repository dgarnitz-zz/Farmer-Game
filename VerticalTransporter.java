public class VerticalTransporter extends AbstractItem {

    public int capacity;
    public String type = "VT";

    public VerticalTransporter(Grid grid, int yCoordinate, int xCoordinate, int capacity) {
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
        AbstractItem firstFound = goUp();
        AbstractItem secondFound = goDown();

        String firstType = isFarmerOrConsumer(firstFound);
        String secondType = isFarmerOrConsumer(secondFound);

        if(firstType.equals("Consumer") && secondType.equals("Farmer")){
            int farmerStock = grid.getStockAt(xCoordinate, secondFound.yCoordinate);
            if(farmerStock > capacity){
                secondFound.reduceStock(capacity);
                grid.addToStockAt(xCoordinate, firstFound.yCoordinate, capacity);
            }
            else {
                secondFound.reduceStock(farmerStock);
                grid.addToStockAt(xCoordinate, firstFound.yCoordinate, farmerStock);
            }
        }
        else if(firstType.equals("Farmer") && secondType.equals("Consumer")){
            int farmerStock = grid.getStockAt(xCoordinate, firstFound.yCoordinate);
            if(farmerStock > capacity){
                firstFound.reduceStock(capacity);
                grid.addToStockAt(xCoordinate, secondFound.yCoordinate, capacity);
            }
            else {
                firstFound.reduceStock(farmerStock);
                grid.addToStockAt(xCoordinate, secondFound.yCoordinate, farmerStock);
            }
        }
        else {
            return;
        }
    }

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