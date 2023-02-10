package MontyHallSolve;

/*
 * Planning: have the program take in a count of how many times played and if the door will be randomized
 * then while the timesPlayed is not equal to 0 it will continue running (runs 10k times)
 * 3 doors
 * User and prize is randomized
 * Remove the user's door from the remaining doors to pick from
 * Monty guy reveals the dud door of the remaining (if two are duds or if one is the prize)
 * Swap the user's door with the other remaining unrevealed door
 */

import java.util.Random;
import java.util.ArrayList;
public class ThreeDoors {
	public int playGame (int timesPlayed, boolean changeDoor) {
		
		int winCount = 0;
		while (timesPlayed != 0) {
			Random ran = new Random();
			// 2 duds, 1 prize, 1 other door, 1 user door
			int userDoor = ran.nextInt(3)+1;
			int prizeDoor = ran.nextInt(3)+1;
			ArrayList<Integer> remDoors = new ArrayList<Integer>();
			remDoors.add(1);
			remDoors.add(2);
			remDoors.add(3);
			
			for (int i = 0; i < remDoors.size(); i++) {
				if(userDoor == remDoors.get(i)) {
					remDoors.remove(i);
				}
			}
			
			// if the prize is not the remaining door make it the door opened by host (removed)
			// remaining, unremoved door is the other door user swaps to
			if(changeDoor == true) {
				if (remDoors.get(0) != prizeDoor) {
					remDoors.remove(0);
				} else if (remDoors.get(1) != prizeDoor) {
					remDoors.remove(1);
				}
				userDoor = remDoors.get(0);
			}
			
			if(userDoor == prizeDoor) {
				winCount++;
			}
	
			timesPlayed--;
		}
		
		return winCount;
	}
	
	public void toString(int winCount) {
		double result = 0;
		System.out.println("Win Count: " + winCount);
		result = (winCount/10000.00)*100;
		System.out.println("Win Percentage: " + result + "%");
	}

}
