public class Rabbit extends AbstractItem {

    public String type = "Rabbit";

    public Rabbit(Grid grid, int xCoordinate, int yCoordinate) {
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    @Override
    public void process(TimeStep timeStep) {
        int stock = getStock();
        if(stock <= 8){
            grid.recordConsumption(stock);
            reduceStock(stock);
        }
        else{
            grid.recordConsumption(8);
            reduceStock(8);
        }

    }

    @Override
    public String toString(){
        return type;
    }

    @Override
    protected int getStock() {
        return grid.getStockAt(xCoordinate, yCoordinate);
    }

    @Override
    protected void addToStock(int nutrition) {
        grid.addToStockAt(xCoordinate, yCoordinate, nutrition);
    }

    @Override
    protected void reduceStock(int nutrition) {
        grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);
    }
}