public class HT extends AbstractItem {

    public int capacity;
    public String type = "HT";

    public HT(Grid grid, int xCoordinate, int yCoordinate, int capacity) {
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.capacity = capacity;
    }

    @Override
    public String toString(){
        return type;
    }

    public int farmerLocation(){
        int gridWidth = grid.getWidth();
        for(int i=xCoordinate-1; i>=0;  i--){
            AbstractItem farmer = grid.getItem(i, yCoordinate);
            if(farmer instanceof RadishFarmer || farmer instanceof CornFarmer){
                return i;
            }
        }
        for(int k = xCoordinate; k<gridWidth; k++){
            AbstractItem farmer = grid.getItem(k, yCoordinate);
            if(farmer instanceof RadishFarmer || farmer instanceof CornFarmer){
                return k;
            }
        }
        return gridWidth+1;
    }

    public int consumerLocation(int farmerXCoordinate){
        int gridWidth = grid.getWidth();
        if(farmerXCoordinate < xCoordinate){

            for(int m = xCoordinate - 1; m>farmerXCoordinate; m--){
                AbstractItem checkPath = grid.getItem(m, yCoordinate);
                if(checkPath != null){
                    return gridWidth+1;
                }
            }

            for(int j = xCoordinate + 1; j<gridWidth; j++){
                AbstractItem consumer = grid.getItem(j, yCoordinate);
                if(consumer instanceof Rabbit || consumer instanceof Beaver){
                    return j;
                }
            }
        }
        else{

            for(int m = xCoordinate + 1; m<farmerXCoordinate; m++){
                AbstractItem checkPath = grid.getItem(m,yCoordinate);
                if(checkPath != null){
                    return gridWidth+1;
                }
            }

            for(int j = xCoordinate - 1; j>=0; j--){
                AbstractItem consumer = grid.getItem(j, yCoordinate);
                if(consumer instanceof Rabbit || consumer instanceof Beaver){
                    return j;
                }
            }
        }
        return gridWidth+1;
    }

    @Override
    public void process(TimeStep timeStep) {
        int farmerXCoordinate = farmerLocation();
        if(farmerXCoordinate==(grid.getWidth()+1)){
            return;
        }
        int consumerXCoordinate = consumerLocation(farmerXCoordinate);
        if(consumerXCoordinate==(grid.getWidth()+1)){
            return;
        }

        int farmerStock = grid.getStockAt(farmerXCoordinate, yCoordinate);
        if(farmerStock > capacity){
            grid.reduceStockAt(farmerXCoordinate, yCoordinate, capacity);
            grid.addToStockAt(consumerXCoordinate, yCoordinate, capacity);
        }
        else {
            grid.reduceStockAt(farmerXCoordinate, yCoordinate, farmerStock);
            grid.addToStockAt(consumerXCoordinate, yCoordinate, farmerStock);
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