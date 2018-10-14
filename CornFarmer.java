public class CornFarmer extends AbstractItem {
    public String type = "Corn Farmer";

    public CornFarmer(AbstractGrid grid, int xCoordinate, int yCoordinate) {
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public boolean checkOtherFarmers(){
        AbstractItem checkFarmer;
        if(xCoordinate > 0){
            checkFarmer = grid.getItem(xCoordinate - 1, yCoordinate);
            if(checkFarmer instanceof RadishFarmer || checkFarmer instanceof CornFarmer){
                return true;
            }
        }
        if(xCoordinate > 1){
            checkFarmer = grid.getItem(xCoordinate - 2, yCoordinate);
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
        if(xCoordinate < grid.getWidth() - 2) {
            checkFarmer = grid.getItem(xCoordinate + 2, yCoordinate);
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
        if((step) % 4 == 0 && !check) {
            grid.recordProduction(25);
            addToStock(25);
        }
    }

    @Override
    public String toString(){
        return type;
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