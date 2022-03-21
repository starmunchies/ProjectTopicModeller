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
	static ArrayList<String> fileaddresses = new ArrayList<String>();
	static int count;
	static boolean onclick = false;

	public static void main(String[] args) {

		getui();

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
	public static void getui() {
		JFrame mainframe = new JFrame("Topic Modeller");// creating instance of JFrame
		// f.setLocationRelativeTo(null);

		JButton filebutton = new JButton("Add File");// creating instance of JButton
		filebutton.setBounds(15, 400, 100, 40);// x axis, y axis, width, height

		JButton examinebutton = new JButton("Examine");// creating instance of JButton
		examinebutton.setBounds(148, 400, 100, 40);// x axis, y axis, width, height

		JButton resetButton = new JButton("Reset");// creating instance of JButton
		resetButton.setBounds(281, 400, 100, 40);// x axis, y axis, width, height

		JButton savefilebutton = new JButton("Save");// creating instance of JButton
		savefilebutton.setBounds(281, 20, 100, 40);// x axis, y axis, width, height
		
		JButton stopfilebutton = new JButton("Strip");// creating instance of JButton
		stopfilebutton.setBounds(15, 20, 100, 40);// x axis, y axis, width, height

		JLabel mainlabel = new JLabel("<html> <br>Files Added:<br>" + fileaddresses + "</html>");
		mainlabel.setBounds(15, -50, 400, 350);

		mainframe.add(mainlabel);
		mainframe.add(savefilebutton);
		mainframe.add(stopfilebutton);
		mainframe.add(resetButton);
		mainframe.add(filebutton);
		mainframe.add(examinebutton);
		mainframe.setSize(400, 500);
		mainframe.setLayout(null);
		mainframe.setVisible(true);

		examinebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String newfileaddresses[] = fileaddresses.toArray(new String[fileaddresses.size()]);
				if (newfileaddresses.length >= 2) {
					onclick = true;
					ConvertArrays convertedarrays = new ConvertArrays(newfileaddresses);
					String[][] converted = convertedarrays.toConvert();

					// System.out.println(converted[1][2]);

					// this creates an instance of the intersection class
					// this allows me to put as many transformed files as array strings into the
					// constructor
					// which in turn will tell me what they have in common

					// i intend to put the stop words inside of the fileProccessor class to strip
					// the
					// string array down to size i.e 10

					// going to have to construct a 2d array of the array created
					// pass it through and reconstruct it on the other side

					int filecount = newfileaddresses.length;
					intersect createintersection = new intersect(converted, filecount);

					commonwords = createintersection.getIntersection();

					count = createintersection.getCount();
					System.out.println(count);

					if (count <= 3) {
						mainlabel.setText("<html>There are no common topics between these files <BR>" + count
								+ "/10 in common <br>common words:<br>" + commonwords + "</html>");
						System.out.println("There are no common topics between these files");
					} else {
						mainlabel.setText("<html>Your files have something in common!! <BR>" + count
								+ "/10 in common <br>common words:<br>" + commonwords + "</html>");
						System.out.println("Your files have something in common!!");
					}

				} else {
					JOptionPane.showMessageDialog(null, "You need more than one file in order to compare");
				}
			}
		});

		filebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser newfilechooser = new JFileChooser();
				newfilechooser.setCurrentDirectory(new File(System.getProperty("user.home")));

				int newreturnedresult = newfilechooser.showOpenDialog(mainframe);

				if (newreturnedresult == JFileChooser.APPROVE_OPTION) {
					File selectedFile = newfilechooser.getSelectedFile();
					fileaddresses.add(selectedFile.getAbsolutePath());
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}

				mainlabel.setText("<html> <br>Files Added:<br>" + fileaddresses + "</html>");
			}
		});

		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onclick = false;
				fileaddresses.clear();
				JOptionPane.showMessageDialog(null, "Files have been cleared from program");
				mainlabel.setText("<html> <br>Files Added:<br>" + fileaddresses + "</html>");
			}
		});
		
		stopfilebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String newstopword = JOptionPane.showInputDialog(null, "Files have been cleared from program");
				try {
					FileWriter newWriter = new FileWriter("stoptext.txt",true);
					newWriter.write(System.lineSeparator());
					newWriter.write(newstopword);
					newWriter.close();
					JOptionPane.showMessageDialog(null, "Sucessfully added " + newstopword);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

		savefilebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (onclick == true) {
					System.out.println(fileaddresses);
					System.out.println(count);
					System.out.println(commonwords);
					try {
						saveResult result = new saveResult(fileaddresses, count, commonwords);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Please Examine the files first");
				}
				
			}
		});
	}
}
