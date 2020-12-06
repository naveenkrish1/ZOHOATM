package atm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class customerdetails {

	long accno;
	String accountholder;
	long pinno;
	long accbalance;

	// adding customer details
	public void addaccount(String accholder, long acno, long pin, long accbal) {
		try {

			String nameNumberString;
			int index1;
			int index2;
			int index3;
			File file = new File("CUSTOMERDETAILS.txt");

			if (!file.exists()) {
				file.createNewFile();
			}
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			boolean found = false;
			while (raf.getFilePointer() < raf.length()) {
				nameNumberString = raf.readLine();
				index1 = nameNumberString.indexOf('|');
				index2 = nameNumberString.indexOf('<');
				index3 = nameNumberString.indexOf('>');
				accno = Long.parseLong(nameNumberString.substring(0, index1));
				accountholder = nameNumberString.substring(index1 + 1, index2);
				pinno = Long.parseLong(nameNumberString.substring(index2 + 1, index3));
				accbalance = Long.parseLong(nameNumberString.substring(index3 + 1));
				if (accno == acno) {
					found = true;
					break;
				}
			}
			if (found == false) {

				// Enter the if block when a record
				// is not already present in the file.
				nameNumberString = String.valueOf(acno) + "|" + accholder + "<" + String.valueOf(pin) + ">"
						+ String.valueOf(accbal);

				// writeBytes function to write a string
				// as a sequence of bytes.
				raf.writeBytes(nameNumberString);

				// To insert the next record in new line.
				raf.writeBytes(System.lineSeparator());

				System.out.println(" ACCOUNT ADDED. ");

				// Closing the resources.
				raf.close();
			} else {
				System.out.println("customer detail already exists");
				raf.close();
			}
		} catch (FileNotFoundException exception) {
			System.out.println("no details exists add new customers");
		} catch (IOException ioe) {
			System.out.println(ioe);
		}

		catch (NumberFormatException nef) {
			System.out.println(nef);
		}

	}
}
