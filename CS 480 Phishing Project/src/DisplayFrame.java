/* Ben Coddington & Pete Garcia
   CIS 480 Phishing Project
   Frame that will act as the GUI of the program. */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.DecimalFormat;

public class DisplayFrame extends JFrame {
	private JPanel topPanel; //holds the button that opens the file browser and the URL text field
	private JTextField urlField; //holds the file path of the file
	private JPanel mainPanel; //holds the output text area
	private JTextArea outputArea; //holds the output that summarizes the results, or the full results if the user selects it
	private JScrollPane scroll; //allows the outputArea text area to be scrollable
	private JPanel buttonPanel; //holds the buttons that start the evaluation, release the full report, and exit the program
	
	private File emailFile; //holds the file selected when the browse button is pressed
	private String filePath; //holds the file path of the selected file to display in the URL text field
	
	//declare this object outside of the methods to make the full report button work properly
	PointAssigner stepFive = new PointAssigner();
	
	//this variable is not used, and is only included to avoid the warning message that will be received without having it
	static final long serialVersionUID = 0;
	
	//constructor of DisplayFrame
	public DisplayFrame() {
		super("Phishing message scanner");
		
		//build panels
		buildTopPanel();
		buildMainPanel();
		buildButtonPanel();
		
		//add panels to frame
		add(topPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
	} //end constructor
	
	//method to build the first panel
	private void buildTopPanel() {
		//create panel
		topPanel = new JPanel();
		
		//create browser button
		JButton browseButton = new JButton("Browse files");
		
		//create read-only text field that holds the file path of the chosen file
		urlField = new JTextField(20);
		urlField.setEditable(false);
		
		//create action listener for browser button
		browseButton.addActionListener(new BrowseButtonListener());
		
		//add the button and text field to the top panel
		topPanel.add(browseButton);
		topPanel.add(urlField);
	} //end method buildTopPanel

	//method to build the second panel
	private void buildMainPanel() {
		//create panel
		mainPanel = new JPanel();
		
		//create read-only scrollable text area that holds the results of the program's run
		outputArea = new JTextArea(10, 30);
		outputArea.setEditable(false);
		scroll = new JScrollPane(outputArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//add the output area to the main panel
		mainPanel.add(scroll);
	} //end method buildMainPanel

	//method to build the third panel
	private void buildButtonPanel() {
		//create panel
		buttonPanel = new JPanel();
		
		//create buttons
		JButton startButton = new JButton("Start evaluation");
		JButton fullButton = new JButton("Release full report");
		JButton exitButton = new JButton("Exit");
		
		//create action listeners for buttons
		startButton.addActionListener(new StartButtonListener());
		fullButton.addActionListener(new FullButtonListener());
		exitButton.addActionListener(new ExitButtonListener());
		
		//add buttons to button panel
		buttonPanel.add(startButton);
		buttonPanel.add(fullButton);
		buttonPanel.add(exitButton);
	} //end method buildButtonPanel	
	
	//browser button listener
	private class BrowseButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JFileChooser browse = new JFileChooser();
			
			int returnVal = browse.showOpenDialog(DisplayFrame.this);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            emailFile = browse.getSelectedFile();
	            
	            filePath = browse.getCurrentDirectory().getAbsolutePath();
	            
	            urlField.setText(filePath);
	            //This is where a real application would open the file.
	        }
			
		}
	}

	//start button listener
	private class StartButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//create an object for each class
			//step one is the DisplayFrame itself
			Reader stepTwo = new Reader();
			//steps three and four are performed in the Reader class
			stepFive.restartAssignment(); //reset the PointAssigner object
			Percentage stepSix = new Percentage();
			
			//array holding all the keywords to search for
			String[] keywords =
				{"Account", "Address", "Alert", "American Express", "Available", "Best Buy", "Capital One", "Confirm",
				 "Credit", "Expir", "FedEx", "Financial", "Founder", "Hope", "Information", "Introduc", " IRS", "Never",
				 "Password", "Please", "There's a problem with your", "Uncertain", "Until now", "Update", "UPS", "USAA",
				 "Verify", "Viagra", "Walmart", "Welcome", "We've noticed some suspicious activit",
				 	//indexes 0-30 = 1 point each
				 "24/7", "$", "Action", "Adobe", "Affordable", "Apple", "Apply", "Bank", "Benefits", "Bill", "Claim",
				 "Click on this link", "Comcast", "Copy", "Deal", "Deposit", "Don't delete", "Eligible", "Email", "Flight",
				 "Google", "Great", "Guarantee", "Immediately", "Important", "Invoice", "LinkedIn", "Must", "Natural", "NBC",
				 "Need", "Not enough", "Partner", "Personal", "Policy", "Potential", "Privacy", "Product", "Refund",
				 "Relationship", "Required", "Secur", "Skill", "Spent", "Statement", "Success", "Travel", "Update your",
				 "Urgent", "Warning", "We're having trouble with your", "Yahoo",
					//indexes 31-82 = 2 points each
				 "Advantage", "Bank of America", "Best", "Biggest", "Bonus", "Cash", "Charit", "Chase", "Confidential",
				 "Congratulations", "DHL", "DocuSign", "Dropbox", "Exclusive", "Extra", "Facebook", "Fantastic", "Free",
				 "Hi dear", "Microsoft", "Netflix", "No strings attached", "Offer", "Official", "Opportunity", "PayPal",
				 "Post", "Promis", "Purpose", "Rates", "Reduce", "Save", "Specialized", "Support", "VIP", "Wells Fargo",
				 "While supplies last", "Win", "You're eligible", "You've been selected", "You've earned a"};
					//indexes 83-123 = 3 points each
			
			//array of counters for the number of each keyword or keyphrase found
			int[] counterArray = new int[123];
			//start all keyword counters at 0
			for(int i = 0; i < 123; i++) {
				counterArray[i] = 0;
			}
			
		//STEP TWO
			stepTwo.setFile(emailFile); //create a file object inside the Reader class object using the file path
			stepTwo.convertFile(keywords, counterArray);
			
		//STEP THREE
			int wordTally = stepTwo.getWordTally(); //get the total amount of words in the email file
			
		//STEP FOUR
			counterArray = stepTwo.getCounterArray(); //assign the completed values of the array of keyword instance counters
			
		//STEP FIVE
			//counts the number of points the email racks up
			int pointTotal = stepFive.pointCounter(keywords, counterArray);
			
		//STEP SIX
			//find the percentage and likelihood
			double percentage = stepSix.findPercentage(pointTotal, wordTally);
			int likelihood = stepSix.findLikelihood(counterArray, wordTally);
			
			//howLikely String variable provides an easy-to-interpret way for the user to understand the file's evaluation
			String howLikely = "";
			if(likelihood < 3) {
				howLikely = "Not very likely.";
			} else if(likelihood >= 3 && likelihood < 6) {
				howLikely = "Somewhat likely.";
			} else if(likelihood >= 6) {
				howLikely = "Very likely.";
			}
			
		//STEP SEVEN
			//format the percentage to only show the first 2 digits to the right of the decimal point
			DecimalFormat decFormatter = new DecimalFormat("0.00");
			String percent = decFormatter.format(percentage);

			//output a report of the evaluation of the email
			String report = ("Word count: " + wordTally + "\nPoints accrued: " + pointTotal + "\nPercentage: " + percent + "%\nLikelihood of ill-intent: " + howLikely);
			outputArea.setText(report);
			
		}
	}
	
	//full button listener
	private class FullButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//hold a copy of the current data in the output area
			String mainReport = outputArea.getText();
			
			//if mainReport and the fullReport don't overlap yet
			if(stepFive.getFullReport().indexOf(mainReport) == -1 && mainReport.indexOf(stepFive.getFullReport()) == -1)
				//set the output area to the main report followed by the detailed report
				outputArea.setText(mainReport + "\n\n" + stepFive.getFullReport());
		}
	}
	
	//private inner class ExitButtonListener handles exit button click
	private class ExitButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) { System.exit(0); }
	} //end class ExitButtonListener
} //end class
