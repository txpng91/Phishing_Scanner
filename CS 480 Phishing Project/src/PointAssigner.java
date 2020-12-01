/* Ben Coddington & Pete Garcia
   CIS 480 Phishing Project
   PointAssigner class receives an array of counters
   for each keyword found, then assigns points accordingly. */

public class PointAssigner {
	private int pointTotal = 0; //holds the total number of points assigned to the email file
	private String wordFound; //holds the current word to be evaluated
	private String fullReport; //holds the full report as it's being built and when it's complete
	
	//pointCounter method checks the number of each word found and assigns points to the point total accordingly
	public int pointCounter(String[] keywords, int[] counterArray) {
		//for loop to output the number of each keyword found and how many points they're worth
		for(int i = 0; i < counterArray.length; i++) {
			int wordPoints = 0; //reset word point value
			//if the current word has at least 1 instance
			if(counterArray[i] > 0) {
				//if statements make sure the appropriate amount of points is being added
				if(i < 31) {
					wordFound = keywords[i]; //set the word found value to the appropriate keyword value
					wordPoints = counterArray[i]; //set the number of points to be the number of instances of the keyword
					pointTotal += counterArray[i]; //add the number of points assigned to this word to the point total
				} else if(i >= 31 && i < 83) {
					wordFound = keywords[i];
					wordPoints = counterArray[i] * 2; //set the number of points to be the number of instances of the keyword times 2
					pointTotal += counterArray[i] * 2;
				} else {
					wordFound = keywords[i];
					wordPoints = counterArray[i] * 3; //set the number of points to be the number of instances of the keyword times 3
					pointTotal += counterArray[i] * 3;
				}
				//add the findings of the current word to the full report
				fullReport += ("Word found: " + wordFound + "\n# of instances: " +  counterArray[i] + "\nPoint value: " + wordPoints + "\n\n");
			}
		} //end for loop
		
		return pointTotal;
	} // end method pointCounter
	
	//returns the completed full report
	public String getFullReport() {
		return fullReport;
	} //end method getFullReport
	
	//resets the persistent values of the class back to their default values
	public void restartAssignment() {
		pointTotal = 0;
		fullReport = "";
	} //end method restartAssignment
} //end class
