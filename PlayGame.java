/**
 * Class designed to setup and actually play the game
 */
public class PlayGame {
    public static int production = 0;
    public static int consumption = 0;

    /**
     * Main method of the PlayGame class. It instantiates the Grid, farmers, tranporters and consumers, creates
     * a new Game and runs the game.
     * @param args
     */
    public static void main(String[] args){

        Grid grid = new Grid(4, 3);
        new CornFarmer(grid, 1, 0);
        new CornFarmer(grid, 0, 2);
        new Rabbit(grid, 2, 2);
        new NearestTransporter(grid, 1, 2, 10);

        Game game = new Game(grid);
        game.run(5);


        /* Grid myGrid = new Grid (6,6);

        RadishFarmer farmerOne = new RadishFarmer(myGrid, 0, 5);
        //CornFarmer farmerTwo = new CornFarmer(myGrid, 0, 0);
        //HorizontalTransporter transporterOne = new HorizontalTransporter(myGrid, 2, 0, 10);
        //VerticalTransporter transporterTwo = new VerticalTransporter(myGrid, 1, 0, 10);
        NearestTransporter NT = new NearestTransporter(myGrid, 2, 4, 10);
        Rabbit rabbitTwo = new Rabbit(myGrid, 4, 0);
        Beaver beaverOne = new Beaver(myGrid, 4, 5);

        Game myGame = new Game(myGrid);
        myGame.run(4);
        */
    }
}