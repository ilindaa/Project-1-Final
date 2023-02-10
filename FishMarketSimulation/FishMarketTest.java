package FishMarketSimulation;
public class FishMarketTest {
	public static void main(String [] args) {
		FishMarket fm = new FishMarket();
		fm.generateSeafood(300, 20);
		fm.exportStuff(fm.getStuff());
		String[] arr = fm.importStuff();
		fm.toString(arr);
	}
}
