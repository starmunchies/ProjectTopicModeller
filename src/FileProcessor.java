import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
/**
 * class that processes the text file
 * strips it for the top common words and returns it
 * @author Matthew
 *
 */
public class FileProcessor {

	
	public String file;
	public String[] specialChars = {".",",","[","]","(",")","%","&","?","/"};
	/**
	 * constructor that sets which file is being used
	 * 
	 * @param file
	 */
	public FileProcessor(String file) {
		this.file = file;
	}

	/**
	 * this opens the file creates an array list
	 * goes through the multiple functions to clean it and sort it in order 
	 * of word frequency and returns the value
	 * 
	 * @return result
	 */
	public ArrayList<String> getFile() {
		// TODO Auto-generated method stub
		// inside here we will open it

		// send it off to be sorted
		// and return the top ten from the array list

		ArrayList<String> fileOutput = new ArrayList<String>();

		File unprocessed = new File(file);
		if (unprocessed.exists() == true) {

			try {
				Scanner myReader = new Scanner(unprocessed);

				while (myReader.hasNext()) {
					
					String check = myReader.next();
					// get rid of special chars
					for(String i: specialChars)
					{
						check = check.replace(i, "");
					}

					fileOutput.add(check.toLowerCase());
					

				}
				myReader.close();
				ArrayList<String> strippedText = stripFile(fileOutput);
				
				
				Map<String, Integer> dictionary = wordFrequency(strippedText);
				
				ArrayList<String> result = getOrder(dictionary);
				//System.out.println(result);
				
				
				System.out.println(result.size());
				//System.out.println(dictionary);
				return result;

				// return false;

			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
				// return false;
			}
		} else {
			System.out.println("An error occurred.");
			return null;
		}
		return null;

		// return null
	}


	
	/**
	 * This essentially gets the passed in dictionary sorts its it by highest value first and then 
	 * returns and saves it to a linked hash-map
	 * 
	 * Found a handy way to sort a a hash-map thanks to the stream method 
	 * implement in java 8 and above 
	 * 
	 * reference: https://www.tutorialspoint.com/java8/java8_streams.htm
	 * Date-Accessed: 03/04/2022 13:19
	 * 
	 * reference: https://stackoverflow.com/questions/42065615/how-to-sort-values-in-hashmap-by-using-treeset
	 * Date-Accessed: 03/04/2022 16:39
	 * 
	 * @param dictionary
	 * 
	 * @return result
	 */
	public ArrayList<String> getOrder(Map<String, Integer> dictionary) {
		ArrayList<String> result = new ArrayList<String>();
		// create a linked hash-map from a streamed hash-map called dictionary
		LinkedHashMap<String, Integer> newSortedMap = dictionary.entrySet().stream()
				// we sort it by using the compare by value method and reverse the order
				// so that the highest is first
				  .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				  // we then collect this and assign the word as the key and assign it 
				  // its value
				  .collect(Collectors.toMap(Map.Entry::getKey,
				                            Map.Entry::getValue,
				                            (keyname, keyvalues) -> keyname, LinkedHashMap::new));
		
		System.out.println(newSortedMap.entrySet());
	
		for (var entry : newSortedMap.entrySet()) {
		   String step = entry.getKey();
		   result.add(step);
		}
		return result;
	}
	
	/**
	 * this strips the array of any common words
	 * it does this by opening a stoptext.txt file and comparing it to see if its there
	 * if its there it removes it
	 * @param input
	 * 
	 * @return input
	 */
	public ArrayList<String> stripFile(ArrayList<String> input) {
		

		File unprocessedStrippedText = new File("stoptext.txt");
		if (unprocessedStrippedText.exists() == true) {

			try {
				Scanner myReader = new Scanner(unprocessedStrippedText);

				while (myReader.hasNext()) {
					
					String check = myReader.next();

					while(input.contains(check) == true) {
						input.remove(check);
					}
					

				}
				myReader.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
				// return false;
			}
		} else {
			System.out.println("An error occurred.");
			return null;
		}
		return input;
		// TODO Auto-generated method stub

	}
	
	/**
	 * This gets the word frequency from the array list
	 * once a word is found to be a duplicate we just add 1 to the value in the dictionary
	 * @param input
	 * 
	 * @return dictionary
	 */
	public Map<String,Integer> wordFrequency(ArrayList<String> input) {

		Map<String, Integer> dictionary = new HashMap<String, Integer>();
		
		// in this case i is the key
		// the value can be updated by using the inbuilt put
		for (String i : input) {
			if (dictionary.keySet().contains(i)) {

				int amount = dictionary.get(i) + 1;
				dictionary.put(i, amount);

			} else {

				dictionary.put(i, 1);
			}

		}
		
		return dictionary;
	
	}
}
