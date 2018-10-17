public class Rabbit extends AbstractItem {

    public String type = "Rabbit";

    /**
     * Class constructor method
     * @param grid
     * @param yCoordinate
     * @param xCoordinate
     */
    public Rabbit(Grid grid, int yCoordinate, int xCoordinate) {
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    /**
     * This method processes the nutrition production activity of the Rabbit. It works by checking if the stock
     * is less than the consumption amount, if it is then it consumes the stock, if not it consumes the consumption
     * amount. The rabbit cannot store anything so at the end of each timestep, the stock is emptied.
     * @param timeStep
     */
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
        stock = getStock();
        if(stock > 0){
            grid.emptyStockAt(xCoordinate, yCoordinate);
        }
    }

    /**
     * Overrides toString method to return the class' type attribute and along with the stock at the Rabbit's cell
     * @return
     */
    @Override
    public String toString(){
        int stock = getStock();
        return type + "(" + stock + ")";
    }

    /**
     * Method to return the stock located at the cell where this instance of Rabbit is located
     * @return
     */
    @Override
    protected int getStock() {
        return grid.getStockAt(xCoordinate, yCoordinate);
    }

    /**
     * Method to add to the stock located at the cell where this instance of Rabbit is located
     * @return
     */
    @Override
    protected void addToStock(int nutrition) {
        grid.addToStockAt(xCoordinate, yCoordinate, nutrition);
    }

    /**
     * Method to reduce the stock located at the cell where this instance of Rabbit is located
     * @return
     */
    @Override
    protected void reduceStock(int nutrition) {
        grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);
    }
}