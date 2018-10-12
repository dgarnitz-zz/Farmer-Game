public class RadishFarmer extends AbstractItem {

    public RadishFarmer(AbstractGrid grid, int xCoordinate, int yCoordinate) {
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    @Override
    public void process(TimeStep timeStep) {
        int step = timeStep.getValue();
        if((step-1) % 3 == 0) {
            /* produces on step 3 or 4?? */
            grid.recordProduction(10);
            addToStock(10);
        }
        /* Need to insert method to check if there is another farmer */
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