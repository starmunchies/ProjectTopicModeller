// Author: Matthew Russell
// Student-Code: c20336046
// Date Created:09/03/2022
// Description: honestly just making sure it works atm i'll do this later

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class Control {
	// public static String[] fileaddresses = {};
	public static String commonwords;
	static ArrayList<String> fileAddresses = new ArrayList<String>();
	static int count;
	static boolean onClick = false;

	public static void main(String[] args) {

		getUI();

		// in order to get an input array I intend to get the user to either drag and
		// drop a folder into the window
		// or open it from a folder which will give me the address location
		// once they do this i will have a for loop that will create create a instance
		// of an open file class
		// i will save it to an array with a count and pass it to the
		// constructor
		// along with a 2d array which will containt the top ten from each file
		// each rown represents a file addresse

		// need a dictionary to get most top common words
		// need to figure out how to sort a dictionary the stream() function exists
		// think I can use that

	}

	/**
	 * this initializes the ui interface such as the buttons the file explorer
	 * 
	 */
	public static void getUI() {
		JFrame mainFrame = new JFrame("Topic Modeller");// creating instance of JFrame
		// f.setLocationRelativeTo(null);

		JButton fileButton = new JButton("Add File");// creating instance of JButton
		fileButton.setBounds(15, 400, 100, 40);// x axis, y axis, width, height

		JButton examineButton = new JButton("Examine");// creating instance of JButton
		examineButton.setBounds(148, 400, 100, 40);// x axis, y axis, width, height

		JButton resetButton = new JButton("Reset");// creating instance of JButton
		resetButton.setBounds(281, 400, 100, 40);// x axis, y axis, width, height

		JButton saveFileButton = new JButton("Save");// creating instance of JButton
		saveFileButton.setBounds(281, 20, 100, 40);// x axis, y axis, width, height

		JButton stopFileButton = new JButton("Exclude");// creating instance of JButton
		stopFileButton.setBounds(15, 20, 100, 40);// x axis, y axis, width, height

		JLabel mainLabel = new JLabel("<html> <br>Files Added:<br>" + fileAddresses + "</html>");
		mainLabel.setBounds(15, -50, 400, 350);

		mainFrame.add(mainLabel);
		mainFrame.add(saveFileButton);
		mainFrame.add(stopFileButton);
		mainFrame.add(resetButton);
		mainFrame.add(fileButton);
		mainFrame.add(examineButton);
		mainFrame.setSize(400, 500);
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);

		examineButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String newFileAddresses[] = fileAddresses.toArray(new String[fileAddresses.size()]);
				if (newFileAddresses.length >= 2) {
					onClick = true;
					ConvertArrays convertedArrays = new ConvertArrays(newFileAddresses);
					String[][] converted = convertedArrays.toConvert();

					// System.out.println(converted[1][2]);

					// this creates an instance of the intersection class
					// this allows me to put as many transformed files as array strings into the
					// constructor
					// which in turn will tell me what they have in common

					// i intend to put the stop words inside of the fileProccessor class to strip
					// the
					// string array down to size i.e 10S

					// going to have to construct a 2d array of the array created
					// pass it through and reconstruct it on the other side

					int fileCount = newFileAddresses.length;
					intersect createIntersection = new intersect(converted, fileCount);

					commonwords = createIntersection.getIntersection();

					count = createIntersection.getCount();
					System.out.println(count);

					if (count <= 3) {
						mainLabel.setText("<html>There are no common topics between these files <BR>" + count
								+ "/10 in common <br>common words:<br>" + commonwords + "</html>");
						System.out.println("There are no common topics between these files");
					} else {
						mainLabel.setText("<html>Your files have something in common!! <BR>" + count
								+ "/10 in common <br>common words:<br>" + commonwords + "</html>");
						System.out.println("Your files have something in common!!");
					}

				} else {
					JOptionPane.showMessageDialog(null, "You need more than one file in order to compare");
				}
			}
		});

		fileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser newFileChooser = new JFileChooser();
				newFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

				int newReturnedResult = newFileChooser.showOpenDialog(mainFrame);

				if (newReturnedResult == JFileChooser.APPROVE_OPTION) {
					File selectedFile = newFileChooser.getSelectedFile();
					fileAddresses.add(selectedFile.getAbsolutePath());
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}

				mainLabel.setText("<html> <br>Files Added:<br>" + fileAddresses + "</html>");
			}
		});

		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onClick = false;
				fileAddresses.clear();
				JOptionPane.showMessageDialog(null, "Files have been cleared from program");
				mainLabel.setText("<html> <br>Files Added:<br>" + fileAddresses + "</html>");
			}
		});

		stopFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String newStopWord = JOptionPane.showInputDialog(null, "Files have been cleared from program");
				try {
					FileWriter newWriter = new FileWriter("stoptext.txt", true);
					newWriter.write(System.lineSeparator());
					newWriter.write(newStopWord);
					newWriter.close();
					JOptionPane.showMessageDialog(null, "Sucessfully added " + newStopWord);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		saveFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (onClick == true) {
					System.out.println(fileAddresses);
					System.out.println(count);
					System.out.println(commonwords);
					try {
						saveResult result = new saveResult(fileAddresses, count, commonwords);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please Examine the files first");
				}

			}
		});
	}
}
