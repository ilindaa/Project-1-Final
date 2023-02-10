package BirthdayProgram;
import java.util.ArrayList;
import java.util.Random;
/* Birthday program
 * make constructor for person/class
 * then code a method that solves the probability
 * compare the probability of objects until classSize is over
 * make a list of Person objects and then passing it into the run method/compareBirthdays (check all possible pairs with no duplicates)
 */

public class Birthday {
	private ArrayList<Person> psClass = new ArrayList<Person>();
	private double countSuccessfulTrials = 0;
	private double numRuns = 0;
	
	public void runProgram(int numRuns, int numPeople) {
		Random ran = new Random();
		Person p = null;
		int countRun = 1;
		
		// every run - clear the array list and fill it with new values
		while (numRuns != 0) {
			printNumRuns(countRun);
			
			// randomize the class' birthdays
			for (int i = 0; i < numPeople; i++) {
				// .nextInt() range: ((max - min) + 1) + min
				int m = ran.nextInt(12)+1;
				int d = 0;
				if (m == 9 || m == 4 || m == 6 || m == 11) {
					d = ran.nextInt(30)+1;
				} else if (m == 2) {
					d = ran.nextInt(28)+1;
				} else {
					d = ran.nextInt(31)+1;
				}
				p = new Person(m, d);
				psClass.add(p);
			}
			// before clearing, calculate the probability with the P(A) = 1 - P(A')
			getPsClass();
			double solve = solveBirthdayProbability();
			printMatchResult(solve);
			
			// test a group of people against an actual simulation with trials and how many times this stands true
			boolean booleanValue = birthdayProbabilitySimulation();
			double result = calculateTrials(booleanValue);
			printRunResults(result);
			
			psClass.clear();
			numRuns--; // for the while loop
			countRun++; // for the header (to print which run is which)
		}
		
	}
	
	// gets the num of runs (the header each time we run a new trial)
	public void printNumRuns(int numRuns) {
		System.out.println("Run " + numRuns);
	}
	
	// gets each birthday in the class
	public void getPsClass() {
		for (int i = 0; i < psClass.size(); i++) {
			System.out.println("Person " + (i+1) + ": " + psClass.get(i).getMonth() + "/" + psClass.get(i).getDay());
		}
	}
	
	// solves the straightforward probability
	// 1 - P(no matching birthdays) = P(matching birthdays)
	public double solveBirthdayProbability() {
		int numPeople = psClass.size();
		double pNoMatch = 1;
		double daysInYear = 365;
		double birthdaysLeft = 365;
		double matchResult = 0;
		
		// "factorial"
		for (int i = 0; i < numPeople; i++) {
			pNoMatch *= (birthdaysLeft/daysInYear);
			birthdaysLeft--;
		}
		
		matchResult = (1 - pNoMatch);
		return matchResult;

	}
	
	// prints the result from solveBirthdayProbability()
	public void printMatchResult(double matchResult) {
		System.out.println("Expected Match Result: " + (matchResult)*100 + "%");
	}
	
	// runs the actual simulation between the number of people
	// checks if all possible pairs (no duplicates) in the list have the same month and day for a birthday
	// if this is true, go ahead and return true - else return false
	public boolean birthdayProbabilitySimulation() {
		int matchBday = 0;
		for (int i = 0; i < psClass.size(); i++) {
			for (int j = (i+1); j < psClass.size(); j++) {
				if(psClass.get(i).getMonth() == psClass.get(j).getMonth() && psClass.get(i).getDay() == psClass.get(j).getDay()) {
					matchBday++;
				}
			}
		}
		
		if (matchBday > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// calculates how many trials of the total trials (or runs) have had a matching birthday
	public double calculateTrials(boolean success) {
		numRuns++;
		if(success == true) {
			countSuccessfulTrials++;
		}
		double result = (countSuccessfulTrials/numRuns);
		return result;
	}
	
	// print out the run results from calculateTrials()
	public void printRunResults(double result) {
		System.out.println("Run Results: " + result*100 + "%\n");
	}
	
}
