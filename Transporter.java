/**
 * The Transporter class contains methods to assist with the implementation of transporter type items.
 */
abstract class Transporter extends AbstractItem {

    /**
     * Method takes the one of the two abstract items found in the process method, checks if it is an instance of a
     * farmer or a consumer, if it is, returns a string with the either "farmer" or "consumer", otherwise returns a
     * null string
     * @param first
     * @return
     */
    public String isFarmerOrConsumer(AbstractItem currentItem){
        String itemType;

        if(currentItem instanceof Farmer){
            itemType = "Farmer";
        }
        else if (currentItem instanceof Consumer){
            itemType = "Consumer";
        }
        else {
            itemType = "ineligible";
        }

        return itemType;
    }

    /**
     * Takes two abstract items, a consumer and a farmer respectively, grabs the stock at the first one, checks if its
     * greater than the capacity of the transporter, then reduces the farmer's stock by either the capacity or the
     * amount of the stock if its less than the capacity. It then adds that amount to the stock at the consumer's
     * location.
     * @param first
     * @param second
     */
    public void transport(AbstractItem first, AbstractItem second, int capacity){
        int farmerStock = grid.getStockAt(second.xCoordinate, second.yCoordinate);
        if(farmerStock > capacity){
            second.reduceStock(capacity);
            grid.addToStockAt(first.xCoordinate, first.yCoordinate, capacity);
        }
        else {
            second.reduceStock(farmerStock);
            grid.addToStockAt(first.xCoordinate, first.yCoordinate, farmerStock);
        }
    }

    @Override
    public void process(TimeStep timeStep){}

    @Override
    protected int getStock(){ return grid.getStockAt(xCoordinate, yCoordinate); }

    @Override
    protected void addToStock(int nutrition){}

    @Override
    protected void reduceStock(int nutrition){}
}