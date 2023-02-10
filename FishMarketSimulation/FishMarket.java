package FishMarketSimulation;

/* Works Cited (FileReader/BufferedReader)
 * https://howtodoinjava.com/java/io/java-filereader/
 * https://www.geeksforgeeks.org/filewriter-class-in-java/
 */

/* private ArrayList<Seafood> stuff = new ArrayList<Seafood>();
 * Write a method to generate "Seafood"
 * Write a method to export your stuff
 * Write a method to import your stuff
 * In a new class test this time
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FishMarket {
	private ArrayList<Seafood> stuff = new ArrayList<Seafood>();
	
	// generate random seafood 
	public void generateSeafood(int numSeafood, int weight) {
		Random random = new Random();
		Seafood seafood = null;
		for (int i = 0; i < numSeafood; i++) {
			// .nextInt() range: ((max - min) + 1) + min
			// 1-4 seafood types
			int ranNum = random.nextInt(weight) + 1;
			// ~20-60 pounds
			int ranWeight = random.nextInt(41) + 20;
			// random price per pound ($) off of a random seafood market
			double fishPrice = 11.99;
			double shrimpPrice = 16.99;
			double scallopPrice = 21.99;
			double crabPrice = 28.99;
			
			// so if the random number isn't 1, 2, or 3 - it has to be 4 or more up to the weight-1 to be a fish
			if(ranNum == 1) {
				seafood = new Shrimp(ranWeight, shrimpPrice);
			} else if (ranNum == 2) {
				seafood = new Scallop(ranWeight, scallopPrice);
			} else if (ranNum == 3) {
				seafood = new Crab(ranWeight, crabPrice);
			} else {
				seafood = new Fish(ranWeight, fishPrice);
			}
			
			stuff.add(seafood);
		}
	}
	
	// returns ArrayList<Seafood> stuff
	public ArrayList<Seafood> getStuff() {
		return stuff;
	}
	
	public void exportStuff(ArrayList<Seafood> stuff) {
		String filePath = "src\\FishMarketSimulation\\fishmarket.csv";
		
		for (int i = 0; i < stuff.size(); i++) {
			try { 
				FileWriter fw = new FileWriter(filePath, true);
				String s = ("index" + "," + "type" + "," + "weight" + "," + "price\n");
				
				if (i == 0) { // header
					fw.write(s);
				}
				
				String s2 = (i + "," + stuff.get(i).getClass().getSimpleName() + "," + stuff.get(i).getWeight() + "," + stuff.get(i).getPrice() + "\n");
				fw.write(s2);
				fw.flush();
				
				if (i == stuff.size()-1) { // 0-299 is 300
					fw.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// import the .csv file into an array and return the array
	public String[] importStuff() {
		String filePath = "src\\FishMarketSimulation\\More CSV Files and Excel Sheets\\fishmarket1.csv";
		BufferedReader br = null;
		String line = "";
		String[] arr = new String[301]; // 301 because header + 300 lines
		int i = 0;
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			while((line = br.readLine()) != null) {
				arr[i] = line;
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}
	
	// toString takes in array and vomits out the array's values
	public void toString(String[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	
}
