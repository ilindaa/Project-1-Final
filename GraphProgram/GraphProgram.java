package GraphProgram;

/* (Levied off of FishMarket)
 * Works Cited (FileReader/BufferedReader)
 * https://howtodoinjava.com/java/io/java-filereader/
 * https://www.geeksforgeeks.org/filewriter-class-in-java/
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class GraphProgram {

	public void runProgram() {
		// uncomment and run lines 20-22 after commenting out lines 23-25
		// plotter(1, 100);
		// salter("src\\\\GraphProgram\\\\plotter.csv", 25, 60);
		// smoother("src\\\\GraphProgram\\\\salter.csv", 5, 5);
		plotter(1, 10000);
		salter("src\\\\GraphProgram\\\\plotter.csv", 2500, 7500);
		smoother("src\\\\GraphProgram\\\\salter.csv", 6000, 6000);
	}

	// give someone a range to plot the points
	public void plotter(int minXRange, int maxXRange) {
		String filePath = "src\\GraphProgram\\plotter.csv";
		
		for (int i = minXRange; i <= maxXRange; i++) {
			try {
				FileWriter fw = new FileWriter(filePath, true);
				// y = mx + b -> y = 6x + 9
				String s = ("x" + "," + "y\n");
				int m = 6;
				int b = 9;
				int y = 0;
				int x = i;
				
				// prints header first
				if (i == minXRange) {
					fw.write(s);
				}
				
				y = m*x+b;
				
				// prints x, y values
				String s2 = (i + "," + y + "\n");
				fw.write(s2);
				fw.flush();
				
				if (i == maxXRange) {
					fw.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// fp takes in the plotter file
	public void salter(String fp, int randomMinRange, int randomMaxRange) {
		Random ran = new Random();
		String filePath = fp;
		BufferedReader br = null;
		String line = "";
		int count = 0;
		String salterFilePath = "src\\\\GraphProgram\\\\salter.csv";
		String[] arr = null;
		FileWriter fw = null;
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			fw = new FileWriter(salterFilePath, true);
			while((line = br.readLine()) != null) {
				if(count == 0) {
					// first line prints out the header
					String s = ("x" + "," + "y\n");
					fw.write(s);
				} else {
					// remaining lines randomize the salt value and adds it to the y-value
					// turn the y-value String numbers into Integers
					// add the salted randomly generated number (user inputs the range) and save it into y-value
					int r = ran.nextInt(randomMaxRange - randomMinRange + 1) + randomMinRange;
					arr = line.split(",");
					int x = Integer.parseInt(arr[1]);
					int newY = (r + x);
					arr[1] = Integer.toString(newY);

					// write to the .csv file x, y values
					String s2 = (arr[0] + "," + arr[1]+ "\n");
					fw.write(s2);
					fw.flush();
				}
				count++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// close the BufferedReader and FileWriter
				br.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// fp takes in the salted file
	public void smoother(String fp, int leftWindowPoints, int rightWindowPoints) {
		String filePath = fp;
		BufferedReader br = null;
		String line = "";
		int count = 0;
		String salterFilePath = "src\\\\GraphProgram\\\\smoother.csv";
		FileWriter fw = null;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		String[] arr2 = new String[2];
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			fw = new FileWriter(salterFilePath, true);
			while((line = br.readLine()) != null) {
				if(count == 0) {
					// first line prints out the header
					String s = ("x" + "," + "y\n");
					fw.write(s);
				} else {
					arr2 = line.split(",");
					int xValue = Integer.parseInt(arr2[0]);
					int yValue = Integer.parseInt(arr2[1]);
					arr.add(yValue);
					
					// find the current x position to get the window left and right (index-1 because x starts at 1)
					int index = (xValue-1);
					int sum = 0;
					double average = 0;
					
					// get the left bound and get the right bound
					int leftBound = index-leftWindowPoints;
					int rightBound = index-rightWindowPoints;
					// need to count how many points were summed up
					int countPoints = 0;
					
					for (int i = 0; i < arr.size(); i++) {
						if(i >= leftBound || i <= rightBound) {
							sum += arr.get(i);
							countPoints++;
						}
					}
					
					average = sum/countPoints;
					String s2 = (arr2[0] + "," + average + "\n");
					fw.write(s2);
					fw.flush();
				}
				count++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// close the BufferedReader and FileWriter
				br.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
