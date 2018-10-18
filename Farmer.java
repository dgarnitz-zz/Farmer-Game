/**
 * The Farmer class contains methods to assist with the implementation of farmer type items.
 */
public class Farmer extends AbstractItem {

    @Override
    public void process(TimeStep timeStep){}

    /**
     * Method to return the stock located at the cell where this instance of farmer is located
     * @return
     */
    @Override
    protected int getStock() {
        return this.grid.getStockAt(this.xCoordinate, this.yCoordinate);
    }

    /**
     * Method to add to stock located at the cell where this instance of farmer is located
     * @return
     */
    @Override
    protected void addToStock(int nutrition) {
        grid.addToStockAt(xCoordinate, yCoordinate, nutrition);
    }

    /**
     * Method to reduce the stock located at the cell where this instance of farmer is located
     * @return
     */
    @Override
    protected void reduceStock(int nutrition) {
        grid.reduceStockAt(this.xCoordinate, this.yCoordinate, nutrition);
    }

    /**
     * This method checks if there are any farmers within 2 cells left and right if its a CornFarmer or 1 cell left and
     * right if its a RadishFarmer, and 1 cell up or down. If another farmer is located within this range, it returns
     * true. Otherwise it returns false. It works using the grid.getItem method on each of the cells specified.
     * It uses if-statements to make sure that out-of-bounds cells are not checked.
     * @return
     */
    public boolean checkOtherFarmers(){
        AbstractItem checkFarmer;
        if(xCoordinate > 0){
            checkFarmer = grid.getItem(xCoordinate - 1, yCoordinate);
            if(checkFarmer instanceof Farmer){
                return true;
            }
        }
        if(this instanceof CornFarmer && xCoordinate > 1){
            checkFarmer = grid.getItem(xCoordinate - 2, yCoordinate);
            if(checkFarmer instanceof Farmer){
                return true;
            }
        }
        if(xCoordinate < grid.getWidth() - 1) {
            checkFarmer = grid.getItem(xCoordinate + 1, yCoordinate);
            if(checkFarmer instanceof Farmer){
                return true;
            }
        }
        if(this instanceof CornFarmer && xCoordinate < (grid.getWidth() - 2)) {
            checkFarmer = grid.getItem(xCoordinate + 2, yCoordinate);
            if(checkFarmer instanceof Farmer){
                return true;
            }
        }
        if(yCoordinate > 0){
            checkFarmer = grid.getItem(xCoordinate, yCoordinate - 1);
            if(checkFarmer instanceof Farmer){
                return true;
            }
        }
        if(yCoordinate < grid.getHeight() - 1) {
            checkFarmer = grid.getItem(xCoordinate, yCoordinate + 1);
            if(checkFarmer instanceof Farmer){
                return true;
            }
        }
        return false;
    }
}