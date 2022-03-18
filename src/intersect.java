import java.util.*;

public class intersect {

	public String intersection = " ";


	HashSet<String> intersectionSet;
	
	/**
	 * we pass in the 2d array which holds the top ten words from each file address given
	 * we then iteratively intersect each one to find out what they all have in common
	 * @param inputtedarray
	 * @param filecount
	 */

	public intersect(String[][] inputtedarray,int filecount) {

		//this gets the first line to create the first intersection set
		ArrayList<String> firstset = new ArrayList<String>();
		
		int i=0;
		while(inputtedarray[0][i] != null)
		{
			firstset.add(inputtedarray[0][i]);
			//System.out.println(inputtedarray[0][i]);
			i++;
		}

		// Creating HashSet object for first input array

		intersectionSet = new HashSet<>(firstset);
		firstset.clear();
//
//		// using retain all function
//
		for (int x = 1; x < filecount; x++) {
//
			ArrayList<String> nextset = new ArrayList<String>();
//			
			int j =0;
			
			while(inputtedarray[x][j] != null)
			{
				//System.out.println(inputtedarray[x][j]);
				nextset.add(inputtedarray[x][j]);
				j++;
			}
			
			HashSet<String> nset = new HashSet<>(nextset);
			nextset.clear();
			intersectionSet.retainAll(nset);
			nset.clear();
		}

	}
	
	/**
	 * this returns what the most common interesected words 
	 * are from the top ten defined by the dictionary
	 * @return
	 */
	public String getIntersection() {
		intersection = null;

		for (String i : intersectionSet) {

			System.out.print(i + " ");
			intersection += " " + i;
		}
		System.out.println();
		return intersection;

	}
	
	public Integer getCount() {
		int count = 0;
		for (@SuppressWarnings("unused") String i: intersectionSet) {
			
			count++;
		}
		return count;
	}
}
