public class PlayGame {
    public static int production = 0;
    public static int consumption = 0;

    public static void main(String[] args){

        Grid myGrid = new Grid (1,5);
        new RadishFarmer(myGrid, 0, 0);
        new Rabbit(myGrid, 0, 2);
        new HT(myGrid, 0,1, 10);

        Game myGame = new Game(myGrid);
        myGame.run(10);
    }
}