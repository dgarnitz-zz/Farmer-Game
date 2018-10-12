public class Rabbit extends AbstractItem {

    public Rabbit(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    @override
    public abstract void process(TimeStep timeStep) {
        int stock = getStock();
        if(stock <= 8){
            Grid.recordConsumption(stock);
            reduceStock(stock);
        }
        else{
            Grid.recordConsumption(8);
            reduceStock(8);
        }

    }

    @override
    protected int getStock() {
        return Grid.getStockAt(xCoordinate, yCoordinate);
    }

    @override
    protected void addToStock(int nutrition) {
        Grid.addToStockAt(xCoordinate, yCoordinate, nutrition);
    }

    @override
    protected void reduceStock(int nutrition) {
        Grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);
    }
}