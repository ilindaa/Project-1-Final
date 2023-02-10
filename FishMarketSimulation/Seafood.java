package FishMarketSimulation;
public class Seafood {
	private double weight;
	private double priceperlb;
	
	Seafood(double weight, double priceperlb) {
		this.weight = weight;
		this.priceperlb = priceperlb;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getPrice() {
		return priceperlb;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void setPrice(double priceperlb) {
		this.priceperlb = priceperlb;
	}
}
