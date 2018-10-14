public class PlayGame {
    public static int production = 0;
    public static int consumption = 0;

    public static void main(String[] args){

        Grid grid = new Grid(5, 5);
        new RadishFarmer(grid, 0, 0);
        new Rabbit(grid, 4, 0);
        new VerticalTransporter(grid, 2, 0, 10);

        Game game = new Game(grid);
        game.run(6);

        /* Grid myGrid = new Grid (5,5);

        RadishFarmer farmerOne = new RadishFarmer(myGrid, 0, 0);
        CornFarmer farmerTwo = new CornFarmer(myGrid, 2, 1);
        HorizontalTransporter transporterOne = new HorizontalTransporter(myGrid, 2, 0, 10);
        VT transporterTwo = new VT(myGrid, 2, 3, 10);
        Rabbit rabbitTwo = new Rabbit(myGrid, 4, 0);
        Beaver beaverOne = new Beaver(myGrid, 2, 4); */

        /* not sure if there is a way to do this automatically on the class */
        /* myGrid.registerItem(farmerOne.xCoordinate, farmerOne.yCoordinate, farmerOne);
        myGrid.registerItem(farmerTwo.xCoordinate, farmerTwo.yCoordinate, farmerTwo);
        myGrid.registerItem(transporterOne.xCoordinate, transporterOne.yCoordinate, transporterOne);
        myGrid.registerItem(transporterTwo.xCoordinate, transporterTwo.yCoordinate, transporterTwo);
        myGrid.registerItem(rabbitTwo.xCoordinate, rabbitTwo.yCoordinate, rabbitTwo);
        myGrid.registerItem(beaverOne.xCoordinate, beaverOne.yCoordinate, beaverOne);

        Game myGame = new Game(myGrid);
        myGame.run(12);
        */
    }
}