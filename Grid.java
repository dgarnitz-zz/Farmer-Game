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
        }
        else{
            currentStock -= nutrition;
            setStockAt(xCoordinate, yCoordinate, currentStock);
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
     * This method takes a TimeStep and a string containing an item type that is supposed to be processed. It then
     * searches through every cell in the Grid, calls getItem on that method and passes it to checkTypeAndProcess
     * along with the item type string and TimeStep so it can be processed.
     * @param timeStep
     * @param itemType
     */
    public void search(TimeStep timeStep, String itemType){
        for (int i=0; i<height; i++){
            for (int j=0; j<width; j++){
                AbstractItem currentCell = getItem(j, i);
                checkTypeAndProcess(currentCell, itemType, timeStep);
            }
        }
    }

    /**
     * This method takes an AbstractItem and depending on the itemType string it was passsed, checks if teh AbstractItem
     * is an instance of a particular object. If it is an instance of the specified type, it calls that object's
     * process method with the given TimeStep.
     * @param currentCell
     * @param typeToCheck
     * @param timeStep
     */
    public void checkTypeAndProcess(AbstractItem currentCell, String typeToCheck, TimeStep timeStep){
        if(typeToCheck.equals("Farmer")){
            if(currentCell instanceof Farmer){
                currentCell.process(timeStep);
            }
        }
        else if(typeToCheck.equals("Transporter")){
            if(currentCell instanceof Transporter){
                currentCell.process(timeStep);
            }
        }
        else if(typeToCheck.equals("Consumer")){
            if(currentCell instanceof Consumer){
                currentCell.process(timeStep);
            }
        }
    }

    /**
     *This method initates the grid searching of processing of each type of item located in the grid.
     * @param timeStep
     */
    @Override
    public void processItems(TimeStep timeStep){
        search(timeStep, "Farmer");
        search(timeStep, "Transporter");
        search(timeStep, "Consumer");
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