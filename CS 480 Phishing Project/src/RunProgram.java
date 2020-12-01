/* Ben Coddington & Pete Garcia
   CIS 480 Phishing Project
   Main file that runs the program. */
import javax.swing.JFrame;

public class RunProgram {
	public static void main(String[] args) {
		DisplayFrame stepOne = new DisplayFrame();
		stepOne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    stepOne.setSize(375, 275);
	    stepOne.setVisible(true);
	} //end main
} //end class
