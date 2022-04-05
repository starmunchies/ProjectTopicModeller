import java.util.*;
/**
 * converts multiple array lists into a 2d array
 * for use in other classes going forward
 *  
 * @author Matthew
 *
 */
public class ConvertArrays {

	String[] fileaddresses;
	ArrayList<String> allStrings = new ArrayList<String>();
	int rangeValue;
	
	//constructor that sets the variables that are passed through
	public ConvertArrays(String[] fileaddresses,int rangeValue) {
		this.fileaddresses = fileaddresses;
		this.rangeValue = rangeValue;
	}

	/**
	 *  this converts all of the file addresses into arrays
	 *  sends it off to be parsed
	 *  and then converts them into a 2d array to be used to find the intersection
	 *  
	 * @return converted
	 */
	public String[][] toConvert() {

		String[][] converted = new String[fileaddresses.length][rangeValue+1];
		allStrings.clear();
		//System.out.println("range of common: "+rangeValue);

		for (int j = 0; j < fileaddresses.length; j++) {

			FileProcessor file = new FileProcessor(fileaddresses[j]);
			
			ArrayList<String> parsed = file.getFile();
			allStrings.addAll(parsed);
			
			// String[] parsed = inputArray1;
			 

			for (int i = 0; i < rangeValue; i++) {
				// System.out.println(parsed.get(i));
					
					converted[j][i] = parsed.get(i);
				
				
			}
		}

		// System.out.println(converted[1][4]);
		return converted;

	}

}
