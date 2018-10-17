public class Beaver extends AbstractItem {

    public String type = "Beaver";

    /**
     * Class constructor method
     * @param grid
     * @param yCoordinate
     * @param xCoordinate
     */
    public Beaver(Grid grid, int yCoordinate, int xCoordinate) {
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    /**
     * This method processes the nutrition production activity of the Rabbit. It works by checking if the stock
     * is less than the consumption amount, if it is then it consumes the stock, if not it consumes the consumption
     * amount. The beaver can store up to 50 units, but if the stock is more than 50 units at the end of the time step,
     * it is reset to 50.
     * @param timeStep
     */
    @Override
    public void process(TimeStep timeStep) {
        int stock = getStock();
        if(stock <= 5){
            grid.recordConsumption(stock);
            reduceStock(stock);
        }
        else{
            grid.recordConsumption(5);
            reduceStock(5);
        }
        stock = getStock();
        if(stock > 50){
            grid.setStockAt(xCoordinate, yCoordinate, 50);
        }
    }

    /**
     * Overrides toString method to return the class' type attribute and along with the stock at the Beaver's cell
     * @return
     */
    @Override
    public String toString(){
        int stock = getStock();
        return type + "(" + stock + ")";
    }

    /**
     * Method to return the stock located at the cell where this instance of Beaver is located
     * @return
     */
    @Override
    protected int getStock() {
        return grid.getStockAt(xCoordinate, yCoordinate);
    }

    /**
     * Method to add to the stock located at the cell where this instance of Beaver is located
     * @return
     */
    @Override
    protected void addToStock(int nutrition) {
        grid.addToStockAt(xCoordinate, yCoordinate, nutrition);
    }

    /**
     * Method to reduce the stock located at the cell where this instance of Beaver is located
     * @return
     */
    @Override
    protected void reduceStock(int nutrition) {
        grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);
    }
}