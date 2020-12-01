/* Ben Coddington & Pete Garcia
   CIS 480 Phishing Project
   KeywordFinder class receives the email string
   and searches for each instance of each keyword. */

public class KeywordFinder {
	
	public static int[] keywordScan(String email, String[] keywords, int[] counterArray) {
		
		String capEmail = email.toUpperCase(); //create a copy of the email that's entirely in uppercase format
		String capKey; //capKey will hold an all-caps version of the current keyword to be compatible with capEmail
		
		//for loop to scan the email and record the number of each keyword found
		for(int x = 0; x < counterArray.length; x++) {
			int currentIndex = 0; //reset currentIndex at the start of the loop
			capKey = keywords[x].toUpperCase(); //turn they entire keyword to caps to prevent case mismatching
			
			//while there's more of the email to search through
			while(currentIndex < email.length()) {
				//if another instance of the keyword still exists
				if(capEmail.indexOf(capKey, currentIndex) != -1) {
					counterArray[x]++; //increment the appropriate keyword counter
					
					//change the index to prevent it from duplicating a find
					currentIndex = (capEmail.indexOf(capKey, currentIndex) + 1);
				} else
					break; //exit the while loop
			} //end while
		} //end for
		
		
		return counterArray;
	} //end method keywordScan
	
} //end class
