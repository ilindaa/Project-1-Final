package MontyHallSolve;

public class ThreeDoorsTest {
	public static void main(String [] args) {
		ThreeDoors game = new ThreeDoors();
		int run = game.playGame(10000, false);
		int run2 = game.playGame(10000, true);
		System.out.println("No Changing Doors: ");
		game.toString(run);
		System.out.println("\nChanging Doors: ");
		game.toString(run2);
	}
}