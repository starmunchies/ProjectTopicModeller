import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class fileProcessor {
	// return top ten using a sorting algorithm prolly the best bet

	// still need to look up how to open .docx files
	// txt files relatively straight forward
	
	public String file;

	public fileProcessor(String file) {
		this.file = file;
	}

	/**
	 * this opens the file creates an array list
	 * goes through the multiple functions to clean it and sort it in order 
	 * of word frequency and returns the value
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
					// get rid of special characters
					check = check.replace(".", "");
					check = check.replace(",", "");
					check = check.replace("[", "");
					check = check.replace("]", "");
					check = check.replace("(", "");
					check = check.replace(")", "");
					check = check.replace("%", "");
					check = check.replace("&", "");
					check = check.replace("?", "");

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
	 * returns and saves it to a linkedhashmap
	 * @param dictionary
	 * @return
	 */
	public ArrayList<String> getOrder(Map<String, Integer> dictionary) {
		ArrayList<String> result = new ArrayList<String>();
		
		LinkedHashMap<String, Integer> newSortedMap = dictionary.entrySet().stream()
				  .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
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
	 * @return
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
	 * @return
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
