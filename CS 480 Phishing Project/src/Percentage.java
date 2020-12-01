/* Ben Coddington & Pete Garcia
   CIS 480 Phishing Project
   Percentage class calculates the likelihood of the email
   being a phishing message. */

public class Percentage {
	private double percentage; //percentage of points compared to words
	private int likelihood; //number between 1 and 5/10 that represents the likelihood of the email being a phishing email
	
	//findPercentage method calculates the percentage of keyword points compared to the file's word total
	public double findPercentage(int pointTotal, int wordTotal) {
		//find the percentage of keywords within the email
		percentage = (pointTotal * 100.0) / wordTotal;
		
		//if percentage is over 100%, round it back down to 100%
		if(percentage > 100)
			percentage = 100;
		
		return percentage;
	} //end method findLikelihood
	
	//findLikelihood method calculates the likelihood of the email being a phishing email
	public int findLikelihood(int[] counterArray, int wordTotal) {
		//initialize variables
		likelihood = 0;
		int[] pointCounter = {0, 0, 0}; //holds counters for the number of instances in each point group of keywords
		double[] counterPercentage = {0, 0, 0}; //holds the percentages of keyword instances to the word total for each point group
		
		//add 1-4 points to the overall likelihood depending on the percentage, or 0 if the percentage is too low
		if(percentage == 100) {
			likelihood += 4;
		} else if(percentage >= 70) {
			likelihood += 3;
		} else if(percentage >= 40) {
			likelihood += 2;
		} else if(percentage >= 10) {
			likelihood += 1;
		}
		
		//loop until all the keyword counters have been checked
		for(int i = 0; i < counterArray.length; i++) {
			//if statements make sure the proper counter is being incremented
			if(i < 31) {
				pointCounter[0] += counterArray[i]; //increment counter for the 1-point group
			} else if (i >= 31 && i < 83) {
				pointCounter[1] += counterArray[i]; //increment counter for the 2-point group
			} else {
				pointCounter[2] += counterArray[i]; //increment counter for the 3-point group
			}
		}
		
		//calculate the percentages of keyword instances to the word total based on point groups
		counterPercentage[0] = (pointCounter[0] * 100.0) / wordTotal;
		counterPercentage[1] = (pointCounter[1] * 100.0) / wordTotal;
		counterPercentage[2] = (pointCounter[2] * 100.0) / wordTotal;
		
		//if there are 9 or more 1-point keywords or 1-point keywords make up 10% of the word total
		if(pointCounter[0] >= 9 || counterPercentage[0] >= 10) {
			likelihood++; //add 1 point to the likelihood
		}

		//if there are 9 or more 2-point keywords or 1-point keywords make up 10% of the word total
		if(pointCounter[1] >= 9 || counterPercentage[1] >= 10) {
			likelihood += 2; //add 2 points to the likelihood
		}

		//if there are 9 or more 3-point keywords or 1-point keywords make up 10% of the word total
		if(pointCounter[2] >= 9 || counterPercentage[2] >= 10) {
			likelihood += 3; //add 3 points to the likelihood
		}
		
		//return a value of 0-10 that represents the likelihood of the email being a phishing email
		return likelihood;
	} //end method findLikelihood
	
} //end class
