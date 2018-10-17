public class RadishFarmer extends AbstractItem {

    public String type = "Radish";

    /**
     * Class constructor method
     * @param grid
     * @param yCoordinate
     * @param xCoordinate
     */
    public RadishFarmer(AbstractGrid grid, int yCoordinate, int xCoordinate) {
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    /**
     * This method checks if there are any farmers within 1 cells left, right, up or down. If another
     * farmer is located within this range, it returns true. Otherwise it returns false. It works using the
     * grid.getItem method on each of the cells specified. It uses if-statements to make sure that out-of-bounds cells
     * are not checked.
     * @return
     */
    public boolean checkOtherFarmers(){
        AbstractItem checkFarmer;
        if(xCoordinate > 0){
            checkFarmer = grid.getItem(xCoordinate - 1, yCoordinate);
            if(checkFarmer instanceof RadishFarmer || checkFarmer instanceof CornFarmer){
                return true;
            }
        }
        if(xCoordinate < grid.getWidth() - 1) {
            checkFarmer = grid.getItem(xCoordinate + 1, yCoordinate);
            if(checkFarmer instanceof RadishFarmer || checkFarmer instanceof CornFarmer){
                return true;
            }
        }
        if(yCoordinate > 0){
            checkFarmer = grid.getItem(xCoordinate, yCoordinate - 1);
            if(checkFarmer instanceof RadishFarmer || checkFarmer instanceof CornFarmer){
                return true;
            }
        }
        if(yCoordinate < grid.getHeight() - 1) {
            checkFarmer = grid.getItem(xCoordinate, yCoordinate + 1);
            if(checkFarmer instanceof RadishFarmer || checkFarmer instanceof CornFarmer){
                return true;
            }
        }
        return false;
    }

    /**
     * This method processes the nutrition production activity of the Radish Farmer. It works by checking if the
     * conditions for production are met, and calling recordProduction and addToStock on 10 units of nutritions if they
     * are. This condition check consists of checking that the timeStep's value is a multple of 3 and the
     * checkOtherFarmers() method has returned false, meaning there are not any farmers located adjacent to this
     * instace. If those conditions are not met, this method does nothing.
     * @param timeStep
     */
    @Override
    public void process(TimeStep timeStep) {
        int step = timeStep.getValue();
        boolean check = checkOtherFarmers();
        if((step) % 3 == 0 && !check) {
            grid.recordProduction(10);
            addToStock(10);
        }
    }

    /**
     * Overrides toString method to return the class' type attribute and along with the stock at the farmer's cell
     * @return
     */
    @Override
    public String toString(){
        int stock = getStock();
        return type + "(" + stock + ")";
    }

    /**
     * Method to return the stock located at the cell where this instance of RadishFarmer is located
     * @return
     */
    @Override
    protected int getStock() {
        return this.grid.getStockAt(this.xCoordinate, this.yCoordinate);
    }

    /**
     * Method to add to stock located at the cell where this instance of RadishFarmer is located
     * @return
     */
    @Override
    protected void addToStock(int nutrition) {
        grid.addToStockAt(xCoordinate, yCoordinate, nutrition);
    }

    /**
     * Method to reduce the stock located at the cell where this instance of RadishFarmer is located
     * @return
     */
    @Override
    protected void reduceStock(int nutrition) {
        grid.reduceStockAt(this.xCoordinate, this.yCoordinate, nutrition);
    }
}