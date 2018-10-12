public class Grid extends AbstractGrid {

    private int height;
    private int width;

    public Grid(int width, int height){
        this.height = height;
        this.width = width;
        grid = new AbstractItem[height][width];
        stock = new int[height][width];
    }

    @Override
    public int getWidth(){
        return width;
    }

    @Override
    public int getHeight(){
        return height;
    }

    @Override
    public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item){
        grid[yCoordinate][xCoordinate] = item;
    }

    @Override
    public AbstractItem getItem(int xCoordinate, int yCoordinate){
        if(xCoordinate >= width || yCoordinate >= height){
            return null;
        }
        if(grid[yCoordinate][xCoordinate] == null){
            return null;
        }

        return grid[yCoordinate][xCoordinate];
    }

    @Override
    public int getStockAt(int xCoordinate, int yCoordinate){
        return stock[yCoordinate][xCoordinate];
    }

    @Override
    public void emptyStockAt(int xCoordinate, int yCoordinate){
        stock[yCoordinate][xCoordinate] = 0;
    }

    @Override
    public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition){
        stock[yCoordinate][xCoordinate] += nutrition;
    }

    @Override
    public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition){
        if(stock[yCoordinate][xCoordinate] < nutrition){
            stock[yCoordinate][xCoordinate] = 0;
        }
        else{
            stock[yCoordinate][xCoordinate] -= nutrition;
        }
    }

    @Override
    public void setStockAt(int xCoordinate, int yCoordinate, int nutrition){
        if(nutrition < 0){
            stock[yCoordinate][xCoordinate] = 0;
        }
        else{
            stock[yCoordinate][xCoordinate] =  nutrition;
        }
    }

    /* NOT SURE HOW TO DO THIS YET
    */
    @Override
    public void processItems(TimeStep timeStep);

    @Override
    public void recordProduction(int nutrition){
        PlayGame.production += nutrition;
    }

    @Override
    public int getTotalProduction(){
        return PlayGame.production;
    }

    @Override
    public void recordConsumption(int nutrition){
        PlayGame.consumption += nutrition;
    }

    @Override
    public int getTotalConsumption(){
        return PlayGame.consumption;
    }
}