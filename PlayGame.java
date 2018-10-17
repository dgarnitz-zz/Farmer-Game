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

        Grid myGrid = new Grid (5,5);

        RadishFarmer farmerOne = new RadishFarmer(myGrid, 0, 0);
        //CornFarmer farmerTwo = new CornFarmer(myGrid, 2, 1);
        //HorizontalTransporter transporterOne = new HorizontalTransporter(myGrid, 2, 0, 10);
        VerticalTransporter transporter = new VerticalTransporter(myGrid, 1, 0, 10);
        Rabbit rabbitTwo = new Rabbit(myGrid, 4, 0);
        Beaver beaverOne = new Beaver(myGrid, 2, 4);

        Game myGame = new Game(myGrid);
        myGame.run(12);
    }
}