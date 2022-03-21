import java.util.*;

public class ConvertArrays {

	String[] fileaddresses;
	

	public ConvertArrays(String[] fileaddresses) {
		this.fileaddresses = fileaddresses;
	}

	/**
	 *  this converts all of the fileaddresses into arrays
	 *  sends it off to be parsed
	 *  and then converts them into a 2d array to be used to find the intersection
	 *  
	 * @return converted
	 */
	public String[][] toconvert() {

		String[][] converted = new String[fileaddresses.length][11];

		for (int j = 0; j < fileaddresses.length; j++) {

			fileProcessor file = new fileProcessor(fileaddresses[j]);
			
			ArrayList<String> parsed = file.getFile();
			
//
			// String[] parsed = inputArray1;
			// System.out.println(parsed[1]);

			for (int i = 0; i < 10; i++) {
				// System.out.println(parsed.get(i));
					
					converted[j][i] = parsed.get(i);
				
				
			}
		}

		// System.out.println(converted[1][4]);
		return converted;

	}

}
