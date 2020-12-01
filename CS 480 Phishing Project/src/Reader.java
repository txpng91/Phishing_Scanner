/* Ben Coddington & Pete Garcia
   CIS 480 Phishing Project
   Reader class receives a text file and converts it to a string. */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
	private File email; //holds the email file to be read and converted
	private String emailString = ""; //holds the String equivalent of the email file
	private int wordTally = 0; //holds the number of words in the email file
	
	//Reader class's version of the counterArray to send back to DisplayFrame after it's been populated
	private int[] counterArray;
	
	//steps three and four are performed in Reader because the class only stores one line of the file's text at a time
	WordCounter stepThree = new WordCounter();
	KeywordFinder stepFour = new KeywordFinder();
	
	//method findEmail receives the file path as a String, and creates an appropriate file object
	public void setFile(File chosenFile) {
		email = chosenFile;
	} //end method findEmail
	
	//method convertFile uses a Scanner object to copy the file's content into a String variable
	public void convertFile(String[] keywords, int[] counterArray) {
		//declare the file input stream and scanner
		FileInputStream inputStream = null;
		Scanner mailSc = null;
		
		try {
			//base the input stream off of the file, and the scanner off of the input stream
			inputStream = new FileInputStream(email);
			mailSc = new Scanner(inputStream, "UTF-8");
			
			//while there's another line to be scanned
			while(mailSc.hasNextLine()) {
				emailString = mailSc.nextLine(); //assign the next line of text to the email string
				
				//add the number of words in the current line to the total number of words counted in the whole file
				wordTally += stepThree.doWordTally(emailString);
				
				//update the counterArray based on the current line
				counterArray = stepFour.keywordScan(emailString, keywords, counterArray);
			}
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
        } finally {
        	if(mailSc != null) {
				mailSc.close();
			}	
		}
		
		//update Reader's counterArray to have it ready to send through the get method
		this.counterArray = counterArray;
	} //end method convertFile
	
	//returns the wordTally variable after processing the whole file
	public int getWordTally() {
		return wordTally;
	}
	
	//returns the counterArray array after processing the whole file
	public int[] getCounterArray() {
		return counterArray;
	}
	
	
} //end class
