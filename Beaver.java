public class Beaver extends Consumer {

    public String type = "Beaver";

    /**
     * Class constructor method
     * @param grid
     * @param yCoordinate
     * @param xCoordinate
     */
    public Beaver(Grid grid, int yCoordinate, int xCoordinate) {
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    /**
     * This method processes the nutrition consumption activity of the Beaver by calling the consume method with the
     * beaver's consumption rate and storage capacity as arguments.
     * @param timeStep
     */
    @Override
    public void process(TimeStep timeStep) {
        consume(5, 50);
    }

    /**
     * Overrides toString method to return the class' type attribute and along with the stock at the Beaver's cell
     * @return
     */
    @Override
    public String toString(){
        int stock = getStock();
        return type + "(" + stock + ")";
    }
}