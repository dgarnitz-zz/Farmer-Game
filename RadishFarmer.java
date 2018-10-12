public class RadishFarmer extends AbstractItem {

    public RadishFarmer(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    @override
    public abstract void process(TimeStep timeStep) {
        int step = timeStep.getValue();
        if((step-1) % 3 == 0) {
            /* produces on step 3 or 4?? */
            Grid.recordProduction(10);
            addToStock(10);
        }
        /* Need to insert method to check if there is another farmer */
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