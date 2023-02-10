/* Works Cited
 * (JFreeChart Guide)
 * https://www.tutorialspoint.com/jfreechart/jfreechart_quick_guide.htm
 * (FileReader/BufferedReader: levied off of my FishMarket/Graph Program)
 * https://howtodoinjava.com/java/io/java-filereader/
 * https://www.geeksforgeeks.org/filewriter-class-in-java/
*/

import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraphLibraryProgram extends ApplicationFrame {
	private XYSeries plotter = new XYSeries("Plotter");
	private XYSeries salter = new XYSeries("Salter");
	private XYSeries smoother = new XYSeries("Smoother");
	
	// Programmer updates the x-ranges (for plotter, salter, smoother)
	private int minXRange = 1;
	private int maxXRange = 10000;

	// The serializable class GraphLibraryProgram does  not declare a static final serialVersionUID field of type long
	private static final long serialVersionUID = 1L;

	public void run() {
		System.out.println("Program Run!");
		
		pack();
		setVisible(true);
	}
	
	public GraphLibraryProgram(String applicationTitle) {
		super(applicationTitle);

		// Programmer updates this (comment out the ones that you aren't testing and uncomment the one you test)
		// Testing: May want to delete the original file in the folder so it doesn't write on top of that file by adding more rows
		// createXYLineChart("Plotter Graph 2", createPlotterDataset());
		// createXYLineChart("Salter Graph 2", createSalterDataset(2500, 7500));
		// createXYLineChart("Smoother Graph 2", createSmootherDataset(2500));
		
	}
	
	public void createXYLineChart(String chartTitle, XYDataset dataset) {
		// title, category axis title, value axis title, ...
		JFreeChart XYLineChart = ChartFactory.createXYLineChart(chartTitle, "x", "y", dataset, PlotOrientation.VERTICAL, false, false, false);
		
		ChartPanel chartPanel = new ChartPanel(XYLineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		
		setContentPane(chartPanel);
	}
	
	// fp is the filepath, i is the x-values, and y is the y-values
	public void writeToCSV(String fp, int d, int y) {
		String filePath = fp;
		try {
			FileWriter fw = new FileWriter(filePath, true);
			
			// prints header
			if(d == minXRange) {
				fw.write("x" + "," + "y\n");
			}
			
			// prints x, y values
			String s2 = (d + "," + y + "\n");
			fw.write(s2);
			fw.flush();
			
			if(d == maxXRange) {
				fw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// reads a CSV into an XY series
	public void readCSVToXYSeries(String fp, int num) {
		String filePath = fp;
		BufferedReader br = null;
		String line = "";
		int count = 0;
		String[] arr = null;
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			
			while((line = br.readLine()) != null) {
				if(count != 0) {
					arr = line.split(",");
					int xValue = Integer.parseInt(arr[0]);
					int yValue = Integer.parseInt(arr[1]);
					
					if(num == 2) {
						salter.add(xValue, yValue);
					} else if (num == 3) {
						smoother.add(xValue, yValue);
					}
				}
				
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private XYDataset createPlotterDataset() {
		
		for(int i = minXRange; i <= maxXRange; i++) {
			int y = 6*(i)+9;
			plotter.add(i, y);
			
			// programmer updates this to plotter#.csv
			writeToCSV("src\\GraphLibraryProgram CSV Files\\plotter2.csv", i, y);
		}
		
		// add the XYSeries to the dataset
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(plotter);
		
		return dataset;
	}
	
	// randomMinRange and randomMaxRange are the salting values
	private XYDataset createSalterDataset(int randomMinRange, int randomMaxRange) {
		Random ran = new Random();
		
		// programmer updates this to plotter#.csv
		readCSVToXYSeries("src\\GraphLibraryProgram CSV Files\\plotter2.csv", 2);
		
		// range starts at 0 and then goes to salter's max value

		for(int i = 0; i < salter.getMaxX(); i++) {
			
			// add random value to the y-value and update the item in the series
			int r = ran.nextInt(randomMaxRange - randomMinRange) + randomMinRange;

			// getY() returns a Number, turn it into an int with intValue() method
			
			int saltedY = salter.getY(i).intValue();
			saltedY = saltedY + r;
			
			// update() updates an the index value's x-value in the series
			salter.update(salter.getX(i), saltedY);
			
			// programmer updates this to salter#.csv
			writeToCSV("src\\GraphLibraryProgram CSV Files\\salter2.csv", salter.getX(i).intValue(), saltedY);
		}
		
		// add the XYSeries to the dataset
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(salter);
		
		return dataset;
	}
	
	// use rolling average from Apache Maths (Stats) Library
	// descriptive statistics, mean
	private XYDataset createSmootherDataset(int slidingWindowLength) {
		// create an instance of the DescriptiveStatistics 
		DescriptiveStatistics ds = new DescriptiveStatistics();
		
		// programmer updates this to salter#.csv
		readCSVToXYSeries("src\\GraphLibraryProgram CSV Files\\salter2.csv", 3);
		
		for(int i = 0; i < smoother.getMaxX(); i++) {
			// set the window size 
			ds.setWindowSize(slidingWindowLength);
			// add the values to the window and take the mean
			ds.addValue(smoother.getY(i).intValue());
			double rollingAvg = ds.getMean();
			
			smoother.update(smoother.getX(i), rollingAvg);
			
			// programmer updates this to smoother#.csv
			writeToCSV("src\\GraphLibraryProgram CSV Files\\smoother2.csv", smoother.getX(i).intValue(), (int) rollingAvg);
		}
		
		// add the XYSeries to the dataset
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(smoother);
		
		return dataset;
	}

	// getters for instance variables
	
	public XYSeries getPlotterSeries() {
		return plotter;
	}
	
	public XYSeries getSalterSeries() {
		return salter;
	}
	
	public XYSeries getSmootherSeries() {
		return smoother;
	}
	
	public int getMinXRange() {
		return minXRange;
	}
	
	public int getMaxXRange() {
		return maxXRange;
	}
}
