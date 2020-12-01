/* Ben Coddington & Pete Garcia
   CIS 480 Phishing Project
   WordCounter class receives the email string,
   and then counts the number of words from the string. */

public class WordCounter {
	private int wordTally = 0; //count the number of words in the email
	
	public int doWordTally(String email) {
		//count the total number of words within the email using the split function
		String[] initialWords = email.split(" ");
		
		String[] emailWords; //will hold the new version of initialWords without the empty strings
		int[] badIndexes = new int[initialWords.length]; //holds the indexes of the empty strings in initialWords
		int badIndexCounter = 0;
		
		//loop until all the strings have been checked
		for(int i = 0; i < initialWords.length; i++) {
			//if an empty string is found
			if(initialWords[i].contains(" ") || initialWords[i].length() == 0) {
				badIndexes[i] = 1; //record the empty string's index
				badIndexCounter++; //increment the bad index counter
			} else { //if no empty string was found
				badIndexes[i] = 0; //mark the index as safe
			}
		} //end for loop
		
		//make the emailWords array have the length of the initialWords array without including the empty strings
		emailWords = new String[initialWords.length - badIndexCounter];
		
		//record the new array length as the number of words
		wordTally = emailWords.length;
		
		return wordTally;
	} //end method doWordTally
	
} //end class
