package atm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class display {
	public void displa(String fname) {
		String line = null;
		try {
			/* FileReader reads text files in the default encoding */
			FileReader fileReader = new FileReader(fname);

			/* wrap the FileReader in BufferedReader */
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}

			bufferedReader.close();
		} catch (IOException ex) {
			System.out.println("Error reading file named '" + fname + "'");
		}
	}
}
