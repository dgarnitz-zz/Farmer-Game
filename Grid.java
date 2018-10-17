public class Grid extends AbstractGrid {

    private int height;
    private int width;

    /**
     * Grid constructor method
     * @param height
     * @param width
     */
    public Grid(int height, int width){
        this.height = height;
        this.width = width;
        grid = new AbstractItem[height][width];
        stock = new int[height][width];
    }

    /**
     * Grid width getter method
     * @return
     */
    @Override
    public int getWidth(){
        return width;
    }

    /**
     * Grid height getter method
     * @return
     */
    @Override
    public int getHeight(){
        return height;
    }

    /**
     * Takes (x,y) coordinate and sets the AbstractItem array at that point equal to the AbstractItem passed in as an
     * argument
     * @param xCoordinate
     * @param yCoordinate
     * @param item
     */
    @Override
    public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item){
        grid[yCoordinate][xCoordinate] = item;
    }

    /**
     * Takes an (x,y) coordinate and returns the AbstractItem at that index in the AbstractItem array if its not
     * out of bounds
     * @param xCoordinate
     * @param yCoordinate
     * @return
     */
    @Override
    public AbstractItem getItem(int xCoordinate, int yCoordinate){
        if(xCoordinate >= width || yCoordinate >= height){
            return null;
        }
        else {
            return grid[yCoordinate][xCoordinate];
        }
    }

    /**
     * Takes an (x,y) coordinate and returns the stock at that index in the Stock array
     * @param xCoordinate
     * @param yCoordinate
     * @return
     */
    @Override
    public int getStockAt(int xCoordinate, int yCoordinate){
        return stock[yCoordinate][xCoordinate];
    }

    /**
     * Takes an (x,y) coordinate and empties the stock at that index in the Stock array
     * @param xCoordinate
     * @param yCoordinate
     * @return
     */
    @Override
    public void emptyStockAt(int xCoordinate, int yCoordinate){
        stock[yCoordinate][xCoordinate] = 0;
    }

    /**
     * Takes an (x,y) coordinate and adds to the stock at that index in the Stock array by the amount of the
     * integer arguments nutrition
     * @param xCoordinate
     * @param yCoordinate
     * @param nutrition
     */
    @Override
    public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition){
        stock[yCoordinate][xCoordinate] += nutrition;
    }

    /**
     * Takes an (x,y) coordinate and reduces to the stock at that index in the Stock array by the amount of the
     * integer arguments nutrition
     * @param xCoordinate
     * @param yCoordinate
     * @param nutrition
     */
    @Override
    public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition){
        int currentStock = getStockAt(xCoordinate, yCoordinate);
        if(currentStock < nutrition){
            setStockAt(xCoordinate, yCoordinate,0);
            //stock[yCoordinate][xCoordinate] = 0;
        }
        else{
            currentStock -= nutrition;
            setStockAt(xCoordinate, yCoordinate, currentStock);
            //stock[yCoordinate][xCoordinate] -= nutrition;
        }
    }

    /**
     * Takes an (x,y) coordinate and sets the stock at that index in the Stock array by the amount of the
     * integer arguments nutrition
     * @param xCoordinate
     * @param yCoordinate
     * @param nutrition
     */
    @Override
    public void setStockAt(int xCoordinate, int yCoordinate, int nutrition){
        if(nutrition < 0){
            stock[yCoordinate][xCoordinate] = 0;
        }
        else{
            stock[yCoordinate][xCoordinate] =  nutrition;
        }
    }

    /**
     *This method searches the grid three separate times using nested for-loops. It gets the item in each cell in the
     * grid, and checks if the instance is a farmer, transporter or consumer. The first set of nested for-loops
     * processes farmers, calling their process method with the timeStep as an argument, the second set processes
     * transporters, calling their process method with the timeStep as an argument, the third processes consumers,
     * calling their process method with the timeStep as an argument.
     * @param timeStep
     */
    @Override
    public void processItems(TimeStep timeStep){
        /* Processs the farmers */
        for (int i=0; i<height; i++){
            for (int j=0; j<width; j++){
                AbstractItem currentCell = getItem(j, i);
                if(currentCell instanceof RadishFarmer || currentCell instanceof CornFarmer){
                    currentCell.process(timeStep);
                }
            }
        }
        /* Processs the transporters */
        for (int k=0; k<height; k++){
            for (int l=0; l<width; l++){
                AbstractItem currentCell = getItem(l, k);
                if(currentCell instanceof HorizontalTransporter || currentCell instanceof VerticalTransporter){
                    currentCell.process(timeStep);
                }
            }
        }
        /* Processs the consumers */
        for (int m=0; m<height; m++){
            for (int n=0; n<width; n++){
                AbstractItem currentCell = getItem(n, m);
                if(currentCell instanceof Rabbit || currentCell instanceof Beaver){
                    currentCell.process(timeStep);
                }
            }
        }
    }

    /**
     * Changes the PlayGame's production attribute by the amount specified through the nutrition argument
     * @param nutrition
     */
    @Override
    public void recordProduction(int nutrition){
        PlayGame.production += nutrition;
    }

    /**
     * Returns the value of the PlayGame class' production attribute
     * @return
     */
    @Override
    public int getTotalProduction(){
        return PlayGame.production;
    }

    /**
     * Changes the PlayGame's consumption attribute by the amount specified through the nutrition argument
     * @param nutrition
     */
    @Override
    public void recordConsumption(int nutrition){
        PlayGame.consumption += nutrition;
    }

    /**
     * Returns the value of the PlayGame class' comsumption attribute
     * @return
     */
    @Override
    public int getTotalConsumption(){
        return PlayGame.consumption;
    }
}