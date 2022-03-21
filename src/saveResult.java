import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class saveResult {

	public saveResult(ArrayList<String> fileaddresses, int count, String noprefix) throws IOException {
		File newFile = new File("results.txt");
		FileWriter newWriter = new FileWriter("results.txt");
		newWriter.write("File Addresses used:");
		newWriter.write(System.lineSeparator());
		for (String i : fileaddresses) {

			newWriter.write(i);
			newWriter.write(System.lineSeparator());
		}
		
		newWriter.write("Percentage of common words:");
		newWriter.write(System.lineSeparator());
		newWriter.write(count*10 + "%");
		newWriter.write(System.lineSeparator());
		newWriter.write("common words:");
		newWriter.write(System.lineSeparator());
		newWriter.write(noprefix);
		
		newWriter.close();
	}

}
