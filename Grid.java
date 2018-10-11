public class Grid extends AbstractGrid {

    @override
    public int getWidth(){
        return grid[0].length;
    }

    @override
    public int getHeight(){
        return grid.length;
    }

    @override
    public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item){
        grid[xCoordinate][yCoordinate] = item;
    }

    @override
    public AbstractItem getItem(int xCoordinate, int yCoordinate){
        if(xCoordinate >= this.getWidth() || yCoordinate >= this.getHeight()){
            return null;
        }
        if(grid[xCoordinate][yCoordinate] == null){
            return null;
        }

        return grid[xCoordinate][yCoordinate];
    }

    @override
    public int getStockAt(int xCoordinate, int yCoordinate){
        return stock[xCoordinate][yCoordinate];
    }

    @override
    public void emptyStockAt(int xCoordinate, int yCoordinate){
        stock[xCoordinate][yCoordinate] = 0;
    }

    @override
    public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition){
        stock[xCoordinate][yCoordinate] += nutrition;
    }

    @override
    public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition){
        if(stock[xCoordinate][yCoordinate] < nutrition){
            stock[xCoordinate][yCoordinate] = 0;
        }
        else{
            stock[xCoordinate][yCoordinate] -= nutrition;
        }
    }

    @override
    public void setStockAt(int xCoordinate, int yCoordinate, int nutrition){
        if(nutrition < 0){
            stock[xCoordinate][yCoordinate] = 0;
        }
        else{
            stock[xCoordinate][yCoordinate] =  nutrition;
        }
    }

    /* NOT SURE HOW TO DO THIS YET
    */
    @override
    public void processItems(TimeStep timeStep);

    @override
    public void recordProduction(int nutrition){
        PlayGame.production += nutrition;
    }

    @override
    public int getTotalProduction(){
        return PlayGame.production;
    }

    @override
    public void recordConsumption(int nutrition){
        PlayGame.consumption += nutrition;
    }

    @override
    public int getTotalConsumption(){
        return PlayGame.consumption;
    }
}