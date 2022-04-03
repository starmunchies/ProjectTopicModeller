import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class saveResult {
	private ArrayList<String> fileAddresses;
	private int count;
	private int rangeValue;
	private String commonWords;
	private String filePath;
	
	public ArrayList<String> getFileAddresses() {
		return fileAddresses;
	}

	public void setFileAddresses(ArrayList<String> fileAddresses) {
		this.fileAddresses = fileAddresses;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRangeValue() {
		return rangeValue;
	}

	public void setRangeValue(int rangeValue) {
		this.rangeValue = rangeValue;
	}

	public String getCommonWords() {
		return commonWords;
	}

	public void setCommonWords(String commonWords) {
		this.commonWords = commonWords;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	//class constructor sets up variables
	public saveResult(ArrayList<String> fileAddresses, int count,int rangeValue, String commonWords,String filePath) {
	
		setFileAddresses(fileAddresses);
		setCount(count);
		setCommonWords(commonWords);
		setRangeValue(rangeValue);
		setFilePath(filePath);
		
	}
	
	public void saveToFile() throws IOException 
	{
		
		@SuppressWarnings("unused")
		File newFile = new File(filePath);
			
			FileWriter newWriter = new FileWriter(filePath);
			newWriter.write("File Addresses used:");
			newWriter.write(System.lineSeparator());
			for (String i : getFileAddresses()) {
				newWriter.write(i);
				newWriter.write(System.lineSeparator());
			}		
			newWriter.write("Percentage of common words:");
			newWriter.write(System.lineSeparator());	
			newWriter.write((((double)getCount()/getRangeValue())*100) + "%");
			newWriter.write(System.lineSeparator());
			newWriter.write("common words:");
			newWriter.write(System.lineSeparator());
			newWriter.write(getCommonWords());
			newWriter.close();
	}
}
