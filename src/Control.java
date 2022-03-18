// Author: Matthew Russell
// Code: c20336046
// Date Created:09/03/2022
// Description: honestly just making sure it works atm i'll do this later

public class Control {
	public static String[] fileaddresses = { "bob.txt", "happy.txt" };
	public static String noprefix = null;

	public static void main(String[] args) {

		// in order to get an input array i intend to get the user to either drag and
		// drop a folder into the window
		// or open it from a folder which will give me the address location
		// once they do this i will have a for loop that will create create a instance
		// of an open file class
		// i will save it to an array with a count and somehow pass it to the
		// constructor
		// not entirely sure how i will do that

		// need a dictionary to get most top common words

		ConvertArrays convertedarrays = new ConvertArrays(fileaddresses);
		String[][] converted = convertedarrays.toconvert();
		// System.out.println(converted[1][2]);

		// this creates an instance of the intersection class
		// this allows me to put as many transformed files as array strings into the
		// constructor
		// which in turn will tell me what they have in common

		// i intend to put the stop words inside of the intersect class to strip the
		// string array down to size

		// gonna have to construct a 2d arrays of the array created
		// pass it through and reconstruct it on the other side

		int filecount = fileaddresses.length;
		intersect createintersection = new intersect(converted, filecount);

		noprefix = createintersection.getIntersection();
		int count = createintersection.getCount();
		System.out.println(count);
		

		if (count == 0) {

			System.out.println("There are no common topics between these files");
		} else {
			System.out.println("Your files have something in common!!");
		}

	}
}
