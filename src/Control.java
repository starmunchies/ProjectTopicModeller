import java.util.ArrayList;

// Author: Matthew Russell
// Student-Code: c20336046
// Date Created:09/03/2022
// Description: honestly just making sure it works atm i'll do this later

public class Control {
	// public static String[] fileaddresses = {};
	public static String commonwords;
	static ArrayList<String> fileAddresses = new ArrayList<String>();
	static int count;
	static boolean onClick = false;

	public static void main(String[] args) {

		Gui newgui = new Gui();
		//newgui.newUi();
		newgui.getUI();

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

	
}
