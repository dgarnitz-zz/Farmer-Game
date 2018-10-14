public class PlayGame {
    public static int production = 0;
    public static int consumption = 0;

    public static void main(String[] args){

        Grid myGrid = new Grid (3,5);
        RadishFarmer farmerOne = new RadishFarmer(myGrid, 0, 0);

        HT transporterOne = new HT(myGrid, 1, 0, 10);
        Rabbit rabbitOne = new Rabbit(myGrid, 2, 0);

        myGrid.registerItem(0, 0, farmerOne);
        myGrid.registerItem(1, 0, transporterOne);
        myGrid.registerItem(2, 0, rabbitOne);

        Game myGame = new Game(myGrid);
        myGame.run(10);
    }
}