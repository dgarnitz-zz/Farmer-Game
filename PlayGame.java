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

        Grid grid = new Grid(6, 6);
        new CornFarmer(grid, 0, 0);
        new CornFarmer(grid, 5, 2);
        new Rabbit(grid, 0, 5);
        new Rabbit(grid, 3, 3);
        new HorizontalTransporter(grid, 0, 3, 10);
        new HorizontalTransporter(grid, 5, 3, 10);
        new Tiger(grid, 5, 4);

        Game game = new Game(grid);
        game.run(20);



    }
}