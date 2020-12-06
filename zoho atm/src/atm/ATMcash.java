package atm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

public class ATMcash {
	// function to empty the a t m
	public void empty() {
		try {
			String atm = "2000" + "x" + "0" + "=" + "0\n";
			// initialize file writer
			FileWriter writer = new FileWriter("ATMCASH.txt");
			// writing an empty atm
			writer.write(atm);
			atm = "500" + "x" + "0" + "=" + "0\n";
			writer.write(atm);
			atm = "100" + "x" + "0" + "=" + "0\n";
			writer.write(atm);
			writer.close();
		} catch (IOException e) {
			System.out.println("error");
		}
	}

	// function to add no of notes to the denominations
	public void addcash(String moneyupdated, long numberofnotesupdated) {
		try {

			String nameNumberString;
			long noofnotes;
			int index1;
			int index2;
			long total;
			String money;
			File file = new File("ATMCASH.txt");

			if (!file.exists()) {
				file.createNewFile();
			}
			// initialize random access file to simeltaneously read and write
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			boolean found = false;
			// running through the text file and storing it in variable based on index of
			// delimiters
			while (raf.getFilePointer() < raf.length()) {
				nameNumberString = raf.readLine();
				// delimiters
				index1 = nameNumberString.indexOf('x');
				index2 = nameNumberString.indexOf('=');
				money = nameNumberString.substring(0, index1);
				noofnotes = Long.parseLong(nameNumberString.substring(index1 + 1, index2));
				total = Long.parseLong(nameNumberString.substring(index2 + 1));
				if (money.equals(moneyupdated)) {
					found = true;
					break;
				}
			}
			if (found == true) {
				// creating a temporary file with file pointer
				File tmpFile = new File("temp.txt");
				RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
				// set file pointer to start
				raf.seek(0);
				while (raf.getFilePointer() < raf.length()) {
					nameNumberString = raf.readLine();

					index1 = nameNumberString.indexOf('x');
					index2 = nameNumberString.indexOf('=');
					money = nameNumberString.substring(0, index1);
					noofnotes = Long.parseLong(nameNumberString.substring(index1 + 1, index2));
					total = Long.parseLong(nameNumberString.substring(index2 + 1));
					// check if the note is note to be updated
					if (money.equals(moneyupdated)) {
						noofnotes = noofnotes + numberofnotesupdated;

						total = Long.parseLong(money);
						total = total * noofnotes;
						// update the denominations
						nameNumberString = money + "x" + String.valueOf(noofnotes) + "=" + String.valueOf(total);
					}
					tmpraf.writeBytes(nameNumberString);
					tmpraf.writeBytes(System.lineSeparator());
				}
				raf.seek(0);
				tmpraf.seek(0);
				while (tmpraf.getFilePointer() < tmpraf.length()) {
					raf.writeBytes(tmpraf.readLine());
					raf.writeBytes(System.lineSeparator());
				}
				raf.setLength(tmpraf.length());
				tmpraf.close();
				raf.close();
				tmpFile.delete();
				System.out.println(" denominations accepted. ");
			} else {

				raf.close();
				System.out.println(" Input money" + " does not exists. ");
			}
		}

		catch (IOException ioe) {
			System.out.println(ioe);
		}

		catch (NumberFormatException nef) {
			System.out.println(nef);
		}
	}

	// function to subtract no of notes in denominations used in withdrawal similar
	// to add cash function
	public void subtractcash(String moneyupdated, long numberofnotesupdated) {
		try {

			String nameNumberString;
			long noofnotes;
			int index1;
			int index2;
			long total;
			String money;
			File file = new File("ATMCASH.txt");

			if (!file.exists()) {
				file.createNewFile();
			}
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			boolean found = false;
			while (raf.getFilePointer() < raf.length()) {
				nameNumberString = raf.readLine();
				index1 = nameNumberString.indexOf('x');
				index2 = nameNumberString.indexOf('=');
				money = nameNumberString.substring(0, index1);
				noofnotes = Long.parseLong(nameNumberString.substring(index1 + 1, index2));
				total = Long.parseLong(nameNumberString.substring(index2 + 1));
				if (money.equals(moneyupdated)) {
					found = true;
					break;
				}
			}
			if (found == true) {
				File tmpFile = new File("temp.txt");
				RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
				raf.seek(0);
				while (raf.getFilePointer() < raf.length()) {
					nameNumberString = raf.readLine();

					index1 = nameNumberString.indexOf('x');
					index2 = nameNumberString.indexOf('=');
					money = nameNumberString.substring(0, index1);
					noofnotes = Long.parseLong(nameNumberString.substring(index1 + 1, index2));
					total = Long.parseLong(nameNumberString.substring(index2 + 1));
					if (money.equals(moneyupdated)) {
						noofnotes = noofnotes - numberofnotesupdated;

						total = Long.parseLong(money);
						total = total * noofnotes;
						nameNumberString = money + "x" + String.valueOf(noofnotes) + "=" + String.valueOf(total);
					}
					tmpraf.writeBytes(nameNumberString);
					tmpraf.writeBytes(System.lineSeparator());
				}
				raf.seek(0);
				tmpraf.seek(0);
				while (tmpraf.getFilePointer() < tmpraf.length()) {
					raf.writeBytes(tmpraf.readLine());
					raf.writeBytes(System.lineSeparator());
				}
				raf.setLength(tmpraf.length());
				tmpraf.close();
				raf.close();
				tmpFile.delete();
			} else {

				raf.close();
				System.out.println(" Input money" + " does not exists. ");
			}
		}

		catch (IOException ioe) {
			System.out.println(ioe);
		}

		catch (NumberFormatException nef) {
			System.out.println(nef);
		}
	}
}
