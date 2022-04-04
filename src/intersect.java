import java.util.*;
/**
 * this finds the intersection of more than one file
 * and contains other functions to return common words and how many
 * 
 * @author Matthew
 *
 */
public class intersect {

	public String intersection = " ";


	HashSet<String> intersectionSet;
	public String[][] inputtedArray;
	public int filecount;
	
	/**
	 * we pass in the 2d array which holds the top ten words from each file address given
	 * we then iteratively intersect each one to find out what they all have in common
	 * @param inputtedArray
	 * @param filecount
	 */
	public intersect(String[][] inputtedArray,int filecount) {
		
		this.inputtedArray = inputtedArray;
		this.filecount = filecount;
		

	}
	/**
	 * when called looks for the intersection between multiple files
	 * must be called before using any other functions associated with
	 * this class
	 * 
	 */
	public void makeIntersect()
	{
			//this gets the first line to create the first intersection set
				ArrayList<String> firstSet = new ArrayList<String>();
				
				int i=0;
				while(inputtedArray[0][i] != null)
				{
					firstSet.add(inputtedArray[0][i]);
					
					i++;
				}

				
				intersectionSet = new HashSet<>(firstSet);
				firstSet.clear();
				
				// loops through each file in the array above and finds any intersecting values
				
				for (int x = 1; x < filecount; x++) {
					// creates a temporary hashset that takes the current row in the 2d array
					
					ArrayList<String> nextSet = new ArrayList<String>();
				
					int j =0;
					
					while(inputtedArray[x][j] != null)
					{
						// adds string to the temporary hashset
						nextSet.add(inputtedArray[x][j]);
						j++;
					}
					// transfers hashet to new hash set
					HashSet<String> finalset = new HashSet<>(nextSet);
					//clears the temp hashset
					nextSet.clear();
					// use retain all function to find common intersections
					intersectionSet.retainAll(finalset);
					//clear the the final sett
					finalset.clear();
				}
	}
	
	/**
	 * this returns what the most common intersected words 
	 * are from the top ten defined by the dictionary
	 * @return intersection
	 */
	public String getIntersection() {
		

		for (String i : intersectionSet) {

			System.out.print(i + " ");
			intersection += " " + i;
		}
		System.out.println();
		return intersection;

	}
	/**
	 * returns how many common words there is
	 * 
	 * @return count
	 */
	public Integer getCount() {
		int count = 0;
		for (@SuppressWarnings("unused") String i: intersectionSet) {
			
			count++;
		}
		return count;
	}
}
