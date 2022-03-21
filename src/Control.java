// Author: Matthew Russell
// Student-Code: c20336046
// Date Created:09/03/2022
// Description: honestly just making sure it works atm i'll do this later

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

public class Control {
	// public static String[] fileaddresses = {};
	public static String noprefix = null;
	static ArrayList<String> fileaddresses = new ArrayList<String>();

	public static void main(String[] args) {

		getui();

		// in order to get an input array i intend to get the user to either drag and
		// drop a folder into the window
		// or open it from a folder which will give me the address location
		// once they do this i will have a for loop that will create create a instance
		// of an open file class
		// i will save it to an array with a count and somehow pass it to the
		// constructor
		// not entirely sure how i will do that

		// need a dictionary to get most top common words
		// need to figure out how to sort a dictionary the stream() function exists
		// think i can use that

	}

	/**
	 * this initializes the ui interface such as the buttons the file explorer
	 * 
	 */
	public static void getui() {
		JFrame mainframe = new JFrame("Topic Modeller");// creating instance of JFrame
		//f.setLocationRelativeTo(null); 

		JButton filebutton = new JButton("Add File");// creating instance of JButton
		filebutton.setBounds(70, 400, 100, 40);// x axis, y axis, width, height

		JButton examinebutton = new JButton("Examine");// creating instance of JButton
		examinebutton.setBounds(230, 400, 100, 40);// x axis, y axis, width, height

		JButton savefilebutton = new JButton("Save Results");// creating instance of JButton
		savefilebutton.setBounds(230, 20, 140, 40);// x axis, y axis, width, height

		JLabel l = new JLabel("<html>Hello testing testing <br>1 2 3 seeing how long before</html? ");
		l.setBounds(0, -100, 400, 350);

		mainframe.add(l);// adding button in JFrame
		mainframe.add(savefilebutton);// adding button in JFrame
		mainframe.add(filebutton);// adding button in JFrame
		mainframe.add(examinebutton);// adding button in JFrame
		mainframe.setSize(400, 500);// 400 width and 500 height
		mainframe.setLayout(null);// using no layout managers
		mainframe.setVisible(true);// making the frame visible

		examinebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String newfileaddresses[] = fileaddresses.toArray(new String[fileaddresses.size()]);
				ConvertArrays convertedarrays = new ConvertArrays(newfileaddresses);
				String[][] converted = convertedarrays.toconvert();

				// System.out.println(converted[1][2]);

				// this creates an instance of the intersection class
				// this allows me to put as many transformed files as array strings into the
				// constructor
				// which in turn will tell me what they have in common

				// i intend to put the stop words inside of the intersect class to strip the
				// string array down to size

				// going to have to construct a 2d arrays of the array created
				// pass it through and reconstruct it on the other side

				int filecount = newfileaddresses.length;
				intersect createintersection = new intersect(converted, filecount);

				noprefix = createintersection.getIntersection();
				
				int count = createintersection.getCount();
				System.out.println(count);

				if (count == 0) {
					l.setText("There are no common topics between these files");
					System.out.println("There are no common topics between these files");
				} else {
					l.setText("Your files have something in common!!");
					System.out.println("Your files have something in common!!");
				}

			}
		});

		filebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(mainframe);

				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					fileaddresses.add(selectedFile.getAbsolutePath());

					System.out.println("Selected file: " + selectedFile.getAbsolutePath());

				}
			}
		});
	}
}
