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

    public int farmerLocation(){
        int gridHeight = grid.getHeight();
        for(int i=yCoordinate-1; i>=0;  i--){
            AbstractItem farmer = grid.getItem(xCoordinate, i);
            if(farmer instanceof RadishFarmer || farmer instanceof CornFarmer){
                return i;
            }
        }
        for(int k = yCoordinate; k<gridHeight; k++){
            AbstractItem farmer = grid.getItem(xCoordinate, k);
            if(farmer instanceof RadishFarmer || farmer instanceof CornFarmer){
                return k;
            }
        }
        return gridHeight+1;
    }

    public int consumerLocation(int farmerYCoordinate){
        int gridHeight = grid.getHeight();
        if(farmerYCoordinate < yCoordinate){

            for(int m = yCoordinate - 1; m>farmerYCoordinate; m--){
                AbstractItem checkPath = grid.getItem(xCoordinate, m);
                if(checkPath != null){
                    return gridHeight+1;
                }
            }

            for(int j = yCoordinate + 1; j<gridHeight; j++){
                AbstractItem consumer = grid.getItem(xCoordinate, j);
                if(consumer instanceof Rabbit || consumer instanceof Beaver){
                    return j;
                }
            }
        }
        else{

            for(int m = yCoordinate + 1; m<farmerYCoordinate; m++){
                AbstractItem checkPath = grid.getItem(xCoordinate, m);
                if(checkPath != null){
                    return gridHeight+1;
                }
            }

            for(int j = yCoordinate - 1; j>=0; j--){
                AbstractItem consumer = grid.getItem(xCoordinate, j);
                if(consumer instanceof Rabbit || consumer instanceof Beaver){
                    return j;
                }
            }
        }
        return gridHeight+1;
    }

    @Override
    public void process(TimeStep timeStep) {
        int farmerYCoordinate = farmerLocation();
        if(farmerYCoordinate==(grid.getHeight()+1)){
            return;
        }
        int consumerYCoordinate = consumerLocation(farmerYCoordinate);
        if(consumerYCoordinate==(grid.getHeight()+1)){
            return;
        }

        int farmerStock = grid.getStockAt(xCoordinate, farmerYCoordinate);
        if(farmerStock > capacity){
            grid.reduceStockAt(xCoordinate, farmerYCoordinate, capacity);
            grid.addToStockAt(xCoordinate, consumerYCoordinate, capacity);
        }
        else {
            grid.reduceStockAt(xCoordinate, farmerYCoordinate, farmerStock);
            grid.addToStockAt(xCoordinate, consumerYCoordinate, farmerStock);
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