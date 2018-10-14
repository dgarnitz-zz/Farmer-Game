public class PlayGame {
    public static int production = 0;
    public static int consumption = 0;

    public static void main(String[] args){

        Grid myGrid = new Grid (5,5);

        RadishFarmer farmerOne = new RadishFarmer(myGrid, 0, 0);
        RadishFarmer farmerTwo = new RadishFarmer(myGrid, 0, 3);
        HT transporterOne = new HT(myGrid, 2, 0, 8);
        Rabbit rabbitOne = new Rabbit(myGrid, 3, 3);
        Rabbit rabbitTwo = new Rabbit(myGrid, 4, 1);
        Beaver beaverOne = new Beaver(myGrid, 3, 0);

        /* not sure if there is a way to do this automatically on the class */
        myGrid.registerItem(farmerOne.xCoordinate, farmerOne.yCoordinate, farmerOne);
        myGrid.registerItem(farmerTwo.xCoordinate, farmerTwo.yCoordinate, farmerTwo);
        myGrid.registerItem(transporterOne.xCoordinate, transporterOne.yCoordinate, transporterOne);
        myGrid.registerItem(rabbitOne.xCoordinate, rabbitOne.yCoordinate, rabbitOne);
        myGrid.registerItem(rabbitTwo.xCoordinate, rabbitTwo.yCoordinate, rabbitTwo);
        myGrid.registerItem(beaverOne.xCoordinate, beaverOne.yCoordinate, beaverOne);

        Game myGame = new Game(myGrid);
        myGame.run(10);
    }
}