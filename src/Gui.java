import java.awt.Desktop;
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
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Gui {
	public String commonwords;
	ArrayList<String> fileAddresses = new ArrayList<String>();
	int count;
	boolean onClick = false;
	int rangeValue = 10;
	JFrame mainFrame;
	JButton[] totalButtons;

	public Gui() {

	}

	public void newUi() {
		
		JFrame mainFrame = new JFrame("Topic Modeller");// creating instance of JFrame
		mainFrame.getContentPane().removeAll();
		mainFrame.repaint();

		// added event listener for this
		Icon stopwordicon = new ImageIcon("icons/neww/blender/addStopWords.PNG");
		JButton addStopWords = new JButton(stopwordicon);
		addStopWords.setToolTipText("Add Stop Word");
		addStopWords.setBounds(0, 616, 200, 200);
		mainFrame.add(addStopWords);

		Icon editIcon = new ImageIcon("icons/neww/blender/editIcon.PNG");
		JButton editStopWords = new JButton(editIcon);
		editStopWords.setBounds(204, 616, 200, 200);
		editStopWords.setToolTipText("Edit Stop Words");
		mainFrame.add(editStopWords);

		// added reset event listener
		Icon resetIcon = new ImageIcon("icons/neww/blender/reset.PNG");
		JButton reset = new JButton(resetIcon);
		reset.setBounds(408, 616, 200, 200);
		reset.setToolTipText("Reset Program");
		mainFrame.add(reset);

		// added function listener
		Icon foldericon = new ImageIcon("icons/neww/blender/folder.PNG");
		JButton addFile = new JButton("Add File", foldericon);
		addFile.setToolTipText("Add File");
		addFile.setBounds(616, 0, 200, 200);
		mainFrame.add(addFile);

		Icon icon = new ImageIcon("icons/neww/blender/searchbutton.PNG");
		JButton examineFiles = new JButton("search", icon);
		examineFiles.setToolTipText("Examine Files");
		examineFiles.setBounds(616, 204, 200, 200);
		mainFrame.add(examineFiles);

		Icon deleteicon = new ImageIcon("icons/neww/blender/delete.PNG");
		JButton deletePrevious = new JButton(deleteicon);
		deletePrevious.setToolTipText("Delete Last Added");
		deletePrevious.setBounds(616, 408, 200, 200);
		mainFrame.add(deletePrevious);

		// make an App drawer to put all the other icons in so it doesn't look like a
		// mess
		// added function listener with sub functions embedded
		Icon drawerIcon = new ImageIcon("icons/appdrawer.PNG");
		JButton drawer = new JButton(drawerIcon);
		drawer.setToolTipText("App Drawer");
		drawer.setBounds(616, 616, 200, 200);
		mainFrame.add(drawer);

		mainFrame.setSize(830, 830);
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);
		getFilesUi(mainFrame);

		examineFiles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				examineFilesUi();
			}
		});

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
				getFilesUi(mainFrame);

				// mainLabel.setText("<html> <br>Files Added:<br>" + fileAddresses + "</html>");
			}
		});

		deletePrevious.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null,
						"Sucessfully deleted " + fileAddresses.get(fileAddresses.size() - 1));
				fileAddresses.remove(fileAddresses.size() - 1);
				removeFilesUi(mainFrame);
				//getFilesUi(mainFrame);
				System.out.println(fileAddresses);
			}

		});

		drawer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.getContentPane().removeAll();
				mainFrame.repaint();
				// getDrawerUi();

				Icon rangeIcon = new ImageIcon("icons/neww/blender/range.PNG");
				JButton newRange = new JButton(rangeIcon);
				newRange.setToolTipText("Select Range");
				newRange.setBounds(0, 0, 200, 200);
				mainFrame.add(newRange);

				Icon saveIcon = new ImageIcon("icons/neww/blender/save.PNG");
				JButton saveFile = new JButton(saveIcon);
				saveFile.setBounds(204, 0, 200, 200);
				saveFile.setToolTipText(" Save File");
				mainFrame.add(saveFile);

				Icon backButtonIcon = new ImageIcon("icons/neww/blender/backButton.PNG");
				JButton backButton = new JButton(backButtonIcon);
				backButton.setBounds(408, 0, 200, 200);
				backButton.setToolTipText("Return To Home");
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

							try {
								result.saveToFile();
								JOptionPane.showMessageDialog(null, "File has been saved");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else if (onClick == false) {

							JOptionPane.showMessageDialog(null, "Please Examine the files first");
						}
					}

				});

				newRange.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame sliderframe = new JFrame("Slider");

						sliderframe.setSize(400, 500);
						sliderframe.setLayout(null);
						sliderframe.setVisible(true);

						JSlider newSlider = new JSlider(0, 30, 10);
						newSlider.setBounds(60, 50, 260, 40);
						newSlider.setPaintTrack(true);
						newSlider.setPaintTicks(true);
						newSlider.setPaintLabels(true);
						newSlider.setMajorTickSpacing(10);
						newSlider.setMinorTickSpacing(1);
						sliderframe.add(newSlider);

						JLabel viewResult = new JLabel();
						sliderframe.add(viewResult);
						viewResult.setText("Value is: " + newSlider.getValue());
						viewResult.setBounds(160, 60, 200, 100);

						JButton submitButton = new JButton("submit");
						submitButton.setBounds(140, 200, 100, 40);
						sliderframe.add(submitButton);

						newSlider.addChangeListener(new ChangeListener() {
							@Override
							public void stateChanged(ChangeEvent event) {

								viewResult.setText("Value is: " + newSlider.getValue());

							}
						});

						submitButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {

								rangeValue = newSlider.getValue();
								sliderframe.dispose();
							}
						});

					}
				});

			}
		});

	}

	public void getFilesUi(JFrame mainFrame) {
		int totalFiles = fileAddresses.size();
		// System.out.println(fileAddresses.size());
		int ycount = 40;
		JButton[] totalButtons = new JButton[totalFiles];

		// mainFrame.getContentPane().removeAll();
		// mainFrame.repaint();
		for (int i = 0; i < totalFiles; i++) {
			
			final int index = i; // i is outside the scope of event listener declaring as final fixes this
			//System.out.println(i);
			totalButtons[i] = new JButton(fileAddresses.get(i)+ " " + i);
			totalButtons[i].setBounds(20, ycount, 590, 40);
			ycount += 50;
			mainFrame.add(totalButtons[i]);
			mainFrame.repaint();
			
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
		}
		
		System.out.println("total " + totalButtons.length);
	}

	public void removeFilesUi(JFrame mainFrame) {
		//int totalFiles = fileAddresses.size();
		// System.out.println(fileAddresses.size());

		//JButton[] totalButtons = new JButton[totalFiles];
		mainFrame.dispose();
		
		newUi();
		
		
		

	}
	
	public void examineFilesUi() {
	
		
		String newFileAddresses[] = fileAddresses.toArray(new String[fileAddresses.size()]);
		if (newFileAddresses.length >= 2) {
			onClick = true;
			
			JFrame resultFrame = new JFrame("Modeller Results");// creating instance of JFrame
			resultFrame.setSize(400, 400);
			resultFrame.setLayout(null);
			resultFrame.setVisible(true);
			

			ConvertArrays convertedArrays = new ConvertArrays(newFileAddresses, rangeValue);
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
//				mainLabel.setText("<html>There are no common topics between these files <BR>" + count * 10
//						+ "% in common <br>common words:<br>" + commonwords + "</html>");
				System.out.println("There are no common topics between these files");
			} else {
//				mainLabel.setText("<html>Your files have something in common!! <BR>" + count * 10
//						+ "% in common <br>common words:<br>" + commonwords + "</html>");
				System.out.println("Your files have something in common!!");
			}

		} else {
			JOptionPane.showMessageDialog(null, "You need more than one file in order to compare");
		}
		
		
		

	}

}
