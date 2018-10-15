public class RadishFarmer extends AbstractItem {

    public String type = "Radish";

    public RadishFarmer(AbstractGrid grid, int yCoordinate, int xCoordinate) {
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        grid.registerItem(xCoordinate, yCoordinate, this);
    }

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

    @Override
    public void process(TimeStep timeStep) {
        int step = timeStep.getValue();
        boolean check = checkOtherFarmers();
        if((step) % 3 == 0 && !check) {
            grid.recordProduction(10);
            addToStock(10);
        }
    }

    @Override
    public String toString(){
        int stock = getStock();
        return type + "(" + stock + ")";
    }

    @Override
    protected int getStock() {
        return this.grid.getStockAt(this.xCoordinate, this.yCoordinate);
    }

    @Override
    protected void addToStock(int nutrition) {
        grid.addToStockAt(xCoordinate, yCoordinate, nutrition);
    }

    @Override
    protected void reduceStock(int nutrition) {
        grid.reduceStockAt(this.xCoordinate, this.yCoordinate, nutrition);
    }
}