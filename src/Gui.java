import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;




  
/**
 * Creates the UI of the whole program
 * uses a range of static buttons and dynamic buttons and functions
 * UI influenced by the old xbox 360 tiles
 * 
 * @author Matthew
 *
 */
public class Gui {
	// where common words found from the examine function is placed
	// left public so any other function within gui can use it
	public String commonwords;
	// use to store and delete and view the file addresses
	ArrayList<String> fileAddresses = new ArrayList<String>();
	// keeps a count of how many words the files have in common
	int count;
	// checks to make sure user has examined files before using an other of the programs functions
	boolean onClick = false;
	// default common words set
	// can be changed at any time using the slider function
	int rangeValue = 10;
	// makes the main window public to the class so all functions can use it
	JFrame mainFrame;
	JButton[] totalButtons;
	/**
	 * Constructor not in use as no variables are passed to it
	 * may be used for error check in the future
	 */
	public Gui() {

	}
	/**
	 * functions thats called by control class
	 * this instantiates the entire ui interface and is a core part of the program
	 * 
	 */
	public void newUi() {

		JFrame mainFrame = new JFrame("Topic Modeller");// creating instance of JFrame
		mainFrame.getContentPane().removeAll();
		mainFrame.repaint();

		// added event listener for this
		Icon stopwordicon = new ImageIcon("icons/addStopWords.PNG");
		JButton addStopWords = new JButton(stopwordicon);
		addStopWords.setToolTipText("Add Stop Word");
		addStopWords.setBounds(0, 612, 200, 190);
		addStopWords.setBackground(Color.white);
		mainFrame.add(addStopWords);

		Icon editIcon = new ImageIcon("icons/editIcon.PNG");
		JButton editStopWords = new JButton(editIcon);
		editStopWords.setBounds(204, 612, 200, 190);
		editStopWords.setToolTipText("Edit Stop Words");
		editStopWords.setBackground(Color.white);
		mainFrame.add(editStopWords);

		// added reset event listener
		Icon resetIcon = new ImageIcon("icons/reset.PNG");
		JButton reset = new JButton(resetIcon);
		reset.setBounds(408, 612, 200, 190);
		reset.setToolTipText("Reset Program");
		reset.setBackground(Color.white);
		mainFrame.add(reset);

		// added function listener
		Icon foldericon = new ImageIcon("icons/folder.PNG");
		JButton addFile = new JButton("Add File", foldericon);
		addFile.setToolTipText("Add File");
		addFile.setBounds(616, 0, 200, 200);
		addFile.setBackground(Color.white);
		mainFrame.add(addFile);
		// added examine button
		Icon icon = new ImageIcon("icons/searchbutton.PNG");
		JButton examineFiles = new JButton("search", icon);
		examineFiles.setToolTipText("Examine Files");
		examineFiles.setBounds(616, 204, 200, 200);
		examineFiles.setBackground(Color.white);
		mainFrame.add(examineFiles);

		Icon deleteicon = new ImageIcon("icons/delete1.PNG");
		JButton deletePrevious = new JButton(deleteicon);
		deletePrevious.setToolTipText("Delete Last Added");
		deletePrevious.setBounds(616, 408, 200, 200);
		deletePrevious.setBackground(Color.white);
		mainFrame.add(deletePrevious);

		// make an App drawer to put all the other icons in so it doesn't look like a
		// mess
		// added function listener with sub functions embedded
		Icon drawerIcon = new ImageIcon("icons/appdrawer.PNG");
		JButton drawer = new JButton(drawerIcon);
		drawer.setToolTipText("App Drawer");
		drawer.setBounds(616, 612, 200, 200);
		drawer.setBackground(Color.white);
		mainFrame.add(drawer);

		mainFrame.setSize(830, 830);
		mainFrame.setBackground(Color.LIGHT_GRAY);
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		getFilesUi(mainFrame);
		
/**
 * this listener calls examineFiles()
 * which examines the commonalities between an unlimited amount of files
 * 
 */
		examineFiles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				examineFilesUi();
			}
		});
		/**
		 * this deletes all the addresses in fileAddresses
		 * and updates the UI accordingly
		 */

		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onClick = false;

				fileAddresses.clear();
				JOptionPane.showMessageDialog(null, "Files have been cleared from program");
				removeFilesUi(mainFrame);
				getFilesUi(mainFrame);
				// mainLabel.setText("<html> <br>Files Added:<br>" + fileAddresses + "</html>");
			}
		});
		
		/**
		 * this opens the desktops default text editor so the user can edit and delete 
		 * the words with a familiar interface that they are used to
		 */
		editStopWords.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				File File = new File("stoptext.txt");

				try {
					Desktop.getDesktop().edit(File);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		/**
		 * function that allows you to add stop words to the list
		 */
		addStopWords.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String newStopWord = JOptionPane.showInputDialog(null, "word to be added");
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
		
		/**
		 * opens a new file chooser window and adds it to the array list
		 * 
		 */

		addFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FileNameExtensionFilter textfilter = new FileNameExtensionFilter("Text Files", "txt",".");
				JFileChooser newFileChooser = new JFileChooser();
				newFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				newFileChooser.setFileHidingEnabled(true);

				newFileChooser.setFileFilter(textfilter);
				// newFileChooser.getChoosableFileFilters(textfilter);

				int newReturnedResult = newFileChooser.showOpenDialog(mainFrame);

				if (newReturnedResult == JFileChooser.APPROVE_OPTION) {
					File selectedFile = newFileChooser.getSelectedFile();

					fileAddresses.add(selectedFile.getAbsolutePath());
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}
				getFilesUi(mainFrame);

				// mainLabel.setText("<html> <br>Files Added:<br>" + fileAddresses + "</html>");
			}
		});
		/**
		 * deletes the last file address in the array list
		 */
		deletePrevious.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null,
						"Sucessfully deleted " + fileAddresses.get(fileAddresses.size() - 1));
				fileAddresses.remove(fileAddresses.size() - 1);
				removeFilesUi(mainFrame);
				// getFilesUi(mainFrame);
				System.out.println(fileAddresses);
			}

		});
		/**
		 * this in an appdrawer this is needed as i cant fit all the buttons on the one frame
		 * the can click on this appdrawer and see a range of many more options
		 */

		drawer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.getContentPane().removeAll();
				mainFrame.repaint();
				// getDrawerUi();
				//creates a static icon button that changes the amount of common words
				Icon rangeIcon = new ImageIcon("icons/range.PNG");
				JButton newRange = new JButton(rangeIcon);
				newRange.setToolTipText("Select Range");
				newRange.setBounds(0, 0, 200, 200);
				newRange.setBackground(Color.white);
				mainFrame.add(newRange);
				
				//creates a static save button to save a file
				Icon saveIcon = new ImageIcon("icons/save.PNG");
				JButton saveFile = new JButton(saveIcon);
				saveFile.setBounds(204, 0, 200, 200);
				saveFile.setToolTipText(" Save File");
				saveFile.setBackground(Color.white);
				mainFrame.add(saveFile);
				//creates a static button in jframe for a back button
				Icon backButtonIcon = new ImageIcon("icons/backButton.PNG");
				JButton backButton = new JButton(backButtonIcon);
				backButton.setBounds(408, 0, 200, 200);
				backButton.setToolTipText("Return To Home");
				backButton.setBackground(Color.white);
				mainFrame.add(backButton);
				
				/**
				 * creates a backButton Listener in appdrawer so they can go back to the main page
				 * 
				 */
				backButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						mainFrame.getContentPane().removeAll();
						mainFrame.repaint();
						newUi();
						mainFrame.dispose();
					}
				});
				/**
				 * adds a listener to the save file button
				 * 
				 * this creates a a file chooser window that allows the user
				 * to save the results file to any directory they want 
				 * for ease of use
				 */
				saveFile.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						//System.out.println(onClick);

						if (onClick == true) {
							
							//creates a pop up frame
							JFrame secondaryFrame = new JFrame();
							 
							JFileChooser saveFileChooser = new JFileChooser();
							saveFileChooser.setDialogTitle("Save To File"); 
							// filters what the user will see in the pane for ease of use 
							
							FileNameExtensionFilter textfilter = new FileNameExtensionFilter("Text Files", "txt",".");
							saveFileChooser.setFileFilter(textfilter);
							saveFileChooser.setFileHidingEnabled(true);
							//adds the saveFileChooser to the secondar frame
							int userSelection = saveFileChooser.showSaveDialog(secondaryFrame);
							
							 // if the user selects or types in a file location and submits
							
							if (userSelection == JFileChooser.APPROVE_OPTION) {
							    File fileToSave = saveFileChooser.getSelectedFile();
							    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
							    
							    saveResult result = new saveResult(fileAddresses,count,rangeValue ,commonwords,fileToSave.getAbsolutePath());

								try {
									result.saveToFile();
									JOptionPane.showMessageDialog(null, "File has been saved");
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							    
							}
							
							
							
						} else if (onClick == false) {

							JOptionPane.showMessageDialog(null, "Please Examine the files first");
						}
					}

				});
				
				/**
				 * adds a listener to the range button that will 
				 * call sliderUi()
				 * 
				 */
				newRange.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						sliderUi();
					}
				});

			}
		});

	}
	/**
	 * called when the user selects to change the range.
	 * the range updates the amount of common words that are checked for 
	 * the Next time they click examinFiles()
	 * 
	 */
	public void sliderUi() {
		// creates a new frame called sliderFrame
		// not tied to mainFrame
		JFrame sliderframe = new JFrame("Slider");
		sliderframe.setSize(400, 500);
		sliderframe.setLayout(null);
		sliderframe.setVisible(true);
		
		// creates a new slider that will be used to update the amount of
		// common words that are checked
		JSlider newSlider = new JSlider(0, 30, 10);
		newSlider.setBounds(60, 50, 260, 40);
		newSlider.setPaintTrack(true);
		newSlider.setPaintTicks(true);
		newSlider.setPaintLabels(true);
		newSlider.setMajorTickSpacing(10);
		newSlider.setMinorTickSpacing(1);
		sliderframe.add(newSlider);
		// creates a label that can be updated dynamically with the 
		// sliders current value
		JLabel viewResult = new JLabel();
		sliderframe.add(viewResult);
		viewResult.setText("Value is: " + newSlider.getValue());
		viewResult.setBounds(160, 60, 200, 100);
		
		// creates a static submit button withing the slider ui
		JButton submitButton = new JButton("submit");
		submitButton.setBounds(140, 200, 100, 40);
		sliderframe.add(submitButton);
		
		/**
		 * adds a listener to the slider so we can look for changes in the slider
		 * and check its value at any given time
		 */
		newSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {

				viewResult.setText("Value is: " + newSlider.getValue());

			}
		});
		
		/**
		 * once the rangeSLider Value has been selected you can click the submit button which will 
		 * set the rangeValue specified at the top of the class
		 * 
		 */
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				rangeValue = newSlider.getValue();
				sliderframe.dispose();
			}
		});
	}

	/**
	 * creates dynamic functions and buttons within the mainFrame
	 * it does this by checking the public String fileAddresses
	 * and assigns buttons accordingly
	 * 
	 * @param mainFrame
	 */
	public void getFilesUi(JFrame mainFrame) {
		int totalFiles = fileAddresses.size();
		// System.out.println(fileAddresses.size());
		int ycount = 40;
		JButton[] totalButtons = new JButton[totalFiles];
		JButton[] DeletetotalButtons = new JButton[totalFiles];
		JLabel heading = new JLabel("Files Added:");
		
		heading.setFont(new Font("Verdana", Font.PLAIN, 18));
		heading.setBounds(20, 0, 400, 30);
		mainFrame.add(heading);

		// mainFrame.getContentPane().removeAll();
		// mainFrame.repaint();
		for (int i = 0; i < totalFiles; i++) {

			final int index = i; // i is outside the scope of event listener declaring as final fixes this
			// System.out.println(i);
			totalButtons[i] = new JButton("File: "+i+" "+fileAddresses.get(i));
			totalButtons[i].setBounds(20, ycount, 500, 40);
			totalButtons[i].setBackground(Color.white);
			totalButtons[i].setHorizontalAlignment(SwingConstants.LEFT);
			
			Icon deleteIcon = new ImageIcon("icons/delete.png");
			DeletetotalButtons[i] = new JButton(deleteIcon);
			DeletetotalButtons[i].setBounds(530, ycount, 80, 40);
			DeletetotalButtons[i].setBackground(Color.white);
			//DeletetotalButtons[i].setHorizontalAlignment(SwingConstants.LEFT);
			
			ycount += 50;
			mainFrame.add(totalButtons[i]);
			mainFrame.add(DeletetotalButtons[i]);
			mainFrame.repaint();
			
			/**
			 * creates an array of button in a descending order based on how many files are
			 * in the fileAddresses specified at the top of this program
			 */
			totalButtons[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					File File = new File(fileAddresses.get(index));

					try {
						Desktop.getDesktop().edit(File);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
			
			/**
			 * dynamic function that creates a function that deletes a specific file
			 * tied to whichever file button that was also created dynamically
			 * 
			 */
			
			DeletetotalButtons[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					JOptionPane.showMessageDialog(null,
							"Sucessfully deleted " + fileAddresses.get(index));
					fileAddresses.remove(index);
					removeFilesUi(mainFrame);

				}
			});
		}

		//System.out.println("total " + totalButtons.length);
	}

	public void removeFilesUi(JFrame mainFrame) {

		// int totalFiles = fileAddresses.size();
		// System.out.println(fileAddresses.size());

		// JButton[] totalButtons = new JButton[totalFiles];
		mainFrame.dispose();

		newUi();

	}

	/**
	 * this examines the commonalities between the files 
	 * it instantiates multiple different classes such as converting an array 
	 * generating an intersection between the files
	 * and making a new frame to show the results using a jlabel
	 */
	public void examineFilesUi() {

		String newFileAddresses[] = fileAddresses.toArray(new String[fileAddresses.size()]);
		
	
		
		if (newFileAddresses.length >= 2) {
			onClick = true;
			
			JFrame resultFrame = new JFrame("Modeller Results");// creating instance of JFrame
			resultFrame.setSize(400, 600);
			resultFrame.setLayout(null);
			resultFrame.setVisible(true);

			ConvertArrays convertedArrays = new ConvertArrays(newFileAddresses, rangeValue);
			String[][] converted = convertedArrays.toConvert();
			
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
			//System.out.println((((double)count/rangeValue)*100));
			JLabel mainLabel = new JLabel();
			mainLabel.setBounds(10, 10, 300, 100);
			resultFrame.add(mainLabel);
			
			DefaultPieDataset dataSet = new DefaultPieDataset( );
			dataSet.setValue( "In Common" , count );  
		    dataSet.setValue( "Not In Common" , rangeValue );
		    
		    JFreeChart chart = ChartFactory.createPieChart(      
		            "Commanality",   // chart title 
		            dataSet,          // data    
		            true,             // include legend   
		            true, 
		            false);
		    
		    ChartPanel pichart = new ChartPanel( chart );
		    pichart.setBounds(0,210,400,300);
		 
		    resultFrame.add(pichart);
		    resultFrame.setResizable(false);
		    
			

			if (((double)count/rangeValue)*100 <= 20) {
			mainLabel.setText("<html>There are no common topics between these files <BR>" + ((double)count/rangeValue)*100
						+ "% in common <br>common words:<br>" + commonwords + "</html>");
				System.out.println("There are no common topics between these files");
			} else {
				mainLabel.setText("<html>Your files have something in common!! <BR>" +((double)count/rangeValue)*100
					+ "% in common <br>common words:<br>" + commonwords + "</html>");
				System.out.println("Your files have something in common!!");
			}

		} else {
			JOptionPane.showMessageDialog(null, "You need more than one file in order to compare");
		}

	}

}
