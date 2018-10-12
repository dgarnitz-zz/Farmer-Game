public class HT extends AbstractItem {

    public int capacity;

    public HT(Grid grid, int xCoordinate, int yCoordinate, int capacity) {
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.capacity = capacity;
    }

    public int farmerLocation(){
        int gridWidth = grid.getWidth();
        for(int i=xCoordinate-1; i>=0;  i--){
            AbstractItem farmer = grid.getItem(i, yCoordinate);
            if(farmer instanceof RadishFarmer){
                return i;
            }
        }
        for(int k = xCoordinate; k<gridWidth; k++){
            AbstractItem farmer = grid.getItem(k, yCoordinate);
            if(farmer instanceof RadishFarmer){
                return k;
            }
        }
        return gridWidth+1;
    }

    public int consumerLocation(int farmerXCoordinate){
        int gridWidth = grid.getWidth();
        if(farmerXCoordinate < xCoordinate){
            for(int j = xCoordinate + 1; j<gridWidth; j++){
                AbstractItem consumer = grid.getItem(j, yCoordinate);
                if(consumer instanceof Rabbit){
                    return j;
                }
            }
        }
        else{
            for(int j = xCoordinate - 1; j>=0; j--){
                AbstractItem consumer = grid.getItem(j, yCoordinate);
                if(consumer instanceof Rabbit){
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

    }

    @Override
    protected void addToStock(int nutrition){

    }

    @Override
    protected void reduceStock(int nutrition){

    }
}