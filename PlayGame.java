public class PlayGame {
    public static int production = 0;
    public static int consumption = 0;

    public static void main(String[] args){

        Grid myGrid = new Grid (5,5);

        RadishFarmer farmerOne = new RadishFarmer(myGrid, 0, 0);
        CornFarmer farmerTwo = new CornFarmer(myGrid, 2, 1);
        HT transporterOne = new HT(myGrid, 3, 0, 10);
        VT transporterTwo = new VT(myGrid, 2, 3, 10);
        /* Rabbit rabbitOne = new Rabbit(myGrid, 3, 3); */
        Rabbit rabbitTwo = new Rabbit(myGrid, 2, 2);
        Beaver beaverOne = new Beaver(myGrid, 2, 4);

        /* not sure if there is a way to do this automatically on the class */
        myGrid.registerItem(farmerOne.xCoordinate, farmerOne.yCoordinate, farmerOne);
        myGrid.registerItem(farmerTwo.xCoordinate, farmerTwo.yCoordinate, farmerTwo);
        myGrid.registerItem(transporterOne.xCoordinate, transporterOne.yCoordinate, transporterOne);
        myGrid.registerItem(transporterTwo.xCoordinate, transporterTwo.yCoordinate, transporterTwo);
        /* myGrid.registerItem(rabbitOne.xCoordinate, rabbitOne.yCoordinate, rabbitOne); */
        myGrid.registerItem(rabbitTwo.xCoordinate, rabbitTwo.yCoordinate, rabbitTwo);
        myGrid.registerItem(beaverOne.xCoordinate, beaverOne.yCoordinate, beaverOne);

        Game myGame = new Game(myGrid);
        myGame.run(12);
    }
}