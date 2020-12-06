package atm;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class atmtohash {
	public long[] atmhassh() throws IOException {
		int index1;
		int index2;
		long noofnotes1;
		String nameNumberString;
		long[] noofnotes;
		// storing already present no of notes in an array for to denomination program
		int i = 0;
		noofnotes = new long[3];
		File file = new File("ATMCASH.txt");

		if (!file.exists()) {
			file.createNewFile();
		}
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		while (raf.getFilePointer() < raf.length()) {
			nameNumberString = raf.readLine();
			index1 = nameNumberString.indexOf('x');
			index2 = nameNumberString.indexOf('=');
			noofnotes1 = Long.parseLong(nameNumberString.substring(index1 + 1, index2));
			noofnotes[i] = (int) noofnotes1;
			i++;
		}
		raf.close();
		return noofnotes;
	}
}
