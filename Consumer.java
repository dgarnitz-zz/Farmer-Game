abstract class Consumer extends AbstractItem {

    /**
     * The consume method works by checking if the stock is less than the consumption rate, if it is then it consumes
     * the amount at stock and reduces the stock at the consumer's cell by that amount. If the stock is greater than
     * the consumption rate, it records consumption equal to the consumption rate and reduces the stock by that
     * amount. After consuming, it checks if the stock is greater than the storage capacity, and if it is, it sets the
     * stock equal to the storage capacity. 
     * @param consumptionRate
     * @param storage
     */
    public void consume(int consumptionRate, int storage){
        int stock = getStock();
        if(stock <= consumptionRate){
            grid.recordConsumption(stock);
            reduceStock(stock);
        }
        else{
            grid.recordConsumption(consumptionRate);
            reduceStock(consumptionRate);
        }
        stock = getStock();
        if(stock > storage){
            grid.setStockAt(xCoordinate, yCoordinate, storage);
        }
    }

    @Override
    public void process(TimeStep timeStep){}

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