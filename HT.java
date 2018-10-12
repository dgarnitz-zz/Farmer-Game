public class HT extends AbstractItem {

    public HT(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    @override
    public abstract void process(TimeStep timeStep) {
        /* locate farmer, check if cell has anything in the store, move from store to consumer
           reduce the store at that cell back to 0, add it to the store at the cell where the consumer is*/
    }

    @override
    protected int getStock() {
        /* Does this need to check the stock of every space of the grid that is on the same vertical line
         * or does just need to search the vertical line for a farmer? Or do you do that in another method? */
        return Grid.getStockAt(xCoordinate, yCoordinate);
    }

    @override
    protected void addToStock(int nutrition) {
        /* This need to add stock to the where the consumer is so they can check it
         * So the arguments passed to the function below need to change */
        Grid.addToStockAt(xCoordinate, yCoordinate, nutrition);
    }

    @override
    protected void reduceStock(int nutrition) {
        /* This need to reduce stock at where the farmer is located
        *  So the arguments passed to the function below need to change*/
        Grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);
    }
}