import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Gui {
	public String commonwords;
	ArrayList<String> fileAddresses = new ArrayList<String>();
	int count;
	boolean onClick = false;
	JFrame mainFrame;

	public Gui() {

	}

	/**
	 * this initialises the UI interface such as the buttons the file explorer
	 * 
	 */
	public void getUI() {
		mainFrame = new JFrame("Topic Modeller");// creating instance of JFrame
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
					createIntersection.makeIntersect();

					commonwords = createIntersection.getIntersection();

					count = createIntersection.getCount();
					System.out.println(count);

					if (count <= 3) {
						mainLabel.setText("<html>There are no common topics between these files <BR>" + count * 10
								+ "% in common <br>common words:<br>" + commonwords + "</html>");
						System.out.println("There are no common topics between these files");
					} else {
						mainLabel.setText("<html>Your files have something in common!! <BR>" + count * 10
								+ "% in common <br>common words:<br>" + commonwords + "</html>");
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

				FileNameExtensionFilter textfilter = new FileNameExtensionFilter("Text Files", "txt");
				JFileChooser newFileChooser = new JFileChooser();
				newFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

				newFileChooser.setFileFilter(textfilter);
				// newFileChooser.getChoosableFileFilters(textfilter);

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

				String newStopWord = JOptionPane.showInputDialog(null, "word has been added");
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
					// System.out.println(fileAddresses);
					// System.out.println(count);
					// System.out.println(commonwords);
					saveResult result = new saveResult(fileAddresses, count, commonwords);
					try {
						result.saveToFile();
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

	public void newUi() {

		JFrame mainFrame = new JFrame("Topic Modeller");// creating instance of JFrame

		// added event listener for this
		Icon stopwordicon = new ImageIcon("icons/addStopWord.PNG");
		JButton addStopWords = new JButton(stopwordicon);
		addStopWords.setBounds(0, 616, 200, 200);
		mainFrame.add(addStopWords);

		Icon editIcon = new ImageIcon("icons/editIcon.PNG");
		JButton editStopWords = new JButton(editIcon);
		editStopWords.setBounds(204, 616, 200, 200);
		mainFrame.add(editStopWords);

		// added reset event listener
		Icon resetIcon = new ImageIcon("icons/reset.PNG");
		JButton reset = new JButton(resetIcon);
		reset.setBounds(408, 616, 200, 200);
		mainFrame.add(reset);
		// added function listener
		Icon foldericon = new ImageIcon("icons/folder.PNG");
		JButton addFile = new JButton("Add File", foldericon);
		addFile.setBounds(616, 0, 200, 200);
		mainFrame.add(addFile);

		Icon icon = new ImageIcon("icons/searchbutton.JPG");
		JButton examineFiles = new JButton("search", icon);
		examineFiles.setBounds(616, 204, 200, 200);
		mainFrame.add(examineFiles);

		Icon deleteicon = new ImageIcon("icons/delete.PNG");
		JButton deletePrevious = new JButton(deleteicon);
		deletePrevious.setBounds(616, 408, 200, 200);
		mainFrame.add(deletePrevious);

		// make an App drawer to put all the other icons in so it doesn't look like a
		// mess
		// added function listener with sub functions embedded
		Icon drawerIcon = new ImageIcon("icons/appdrawer.PNG");
		JButton drawer = new JButton(drawerIcon);
		drawer.setBounds(616, 616, 200, 200);
		mainFrame.add(drawer);

		mainFrame.setSize(830, 830);
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);

		examineFiles.addActionListener(new ActionListener() {

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
					createIntersection.makeIntersect();

					commonwords = createIntersection.getIntersection();

					count = createIntersection.getCount();
					System.out.println(count);

					if (count <= 3) {
//						mainLabel.setText("<html>There are no common topics between these files <BR>" + count * 10
//								+ "% in common <br>common words:<br>" + commonwords + "</html>");
						System.out.println("There are no common topics between these files");
					} else {
//						mainLabel.setText("<html>Your files have something in common!! <BR>" + count * 10
//								+ "% in common <br>common words:<br>" + commonwords + "</html>");
						System.out.println("Your files have something in common!!");
					}

				} else {
					JOptionPane.showMessageDialog(null, "You need more than one file in order to compare");
				}
			}
		});

		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onClick = false;
				fileAddresses.clear();
				JOptionPane.showMessageDialog(null, "Files have been cleared from program");
				// mainLabel.setText("<html> <br>Files Added:<br>" + fileAddresses + "</html>");
			}
		});

		addStopWords.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String newStopWord = JOptionPane.showInputDialog(null, "word has been added");
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

		addFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FileNameExtensionFilter textfilter = new FileNameExtensionFilter("Text Files", "txt");
				JFileChooser newFileChooser = new JFileChooser();
				newFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

				newFileChooser.setFileFilter(textfilter);
				// newFileChooser.getChoosableFileFilters(textfilter);

				int newReturnedResult = newFileChooser.showOpenDialog(mainFrame);

				if (newReturnedResult == JFileChooser.APPROVE_OPTION) {
					File selectedFile = newFileChooser.getSelectedFile();
					fileAddresses.add(selectedFile.getAbsolutePath());
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}

				// mainLabel.setText("<html> <br>Files Added:<br>" + fileAddresses + "</html>");
			}
		});

		drawer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.getContentPane().removeAll();
				mainFrame.repaint();
				// getDrawerUi();

				Icon rangeIcon = new ImageIcon("icons/range.PNG");
				JButton newRange = new JButton(rangeIcon);
				newRange.setBounds(0, 0, 200, 200);
				mainFrame.add(newRange);

				Icon saveIcon = new ImageIcon("icons/save.JPEG");
				JButton saveFile = new JButton(saveIcon);
				saveFile.setBounds(204, 0, 200, 200);
				mainFrame.add(saveFile);

				Icon backButtonIcon = new ImageIcon("icons/backButton.PNG");
				JButton backButton = new JButton(backButtonIcon);
				backButton.setBounds(408, 0, 200, 200);
				mainFrame.add(backButton);

				backButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						mainFrame.getContentPane().removeAll();
						mainFrame.repaint();
						newUi();
						mainFrame.dispose();
					}
				});

				saveFile.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(onClick);
						
						if (onClick == true) {
							// System.out.println(fileAddresses);
							// System.out.println(count);
							// System.out.println(commonwords);
							saveResult result = new saveResult(fileAddresses, count, commonwords);
							JOptionPane.showMessageDialog(null, "Please Examine the files first");
							try {
								result.saveToFile();
								JOptionPane.showMessageDialog(null, "File has been saved");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} 
						else if(onClick == false) {
							
							JOptionPane.showMessageDialog(null, "Please Examine the files first");
						}
					}

				});

				newRange.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

					}
				});

			}
		});

	}

	public void getDrawerUi() {
		// mainFrame.removeAll();
	}

}
