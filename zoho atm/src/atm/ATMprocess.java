package atm;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ATMprocess {
	// function to get balance of an account with pin
	public long checkbalance(long acno, long pin) {
		try {
			long accno;
			String nameNumberString;
			int index1;
			int index3;
			int index2;
			long pinno;
			long balance;
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
				pinno = Long.parseLong(nameNumberString.substring(index2 + 1, index3));
				balance = Long.parseLong(nameNumberString.substring(index3 + 1));
				// check if acc no and pin no exists let in if only yes
				if (accno == acno && pinno == pin) {
					found = true;
					raf.close();
					return balance;
				}
			}
			if (found == false) {
				System.out.println("account no and pin doesnot match");
				raf.close();
			}
		} catch (IOException ioe) {
			System.out.println(ioe);
		}

		catch (NumberFormatException nef) {
			System.out.println(nef);
		}

		return 0;
	}

	public void withdrawal(long acno, long pin, long amount) throws NumberFormatException, IOException {
		// check if amount is in range of 100 and 10000
		if (amount % 100 == 0 && amount >= 100 && amount <= 10000) {
			// check if atm has sufficient balance
			long available = ATMbalance();
			if (available > amount) {
				try {
					withdrawcheck w = new withdrawcheck();
					long accno;
					String nameNumberString;
					int index1;
					int index3;
					int index2;
					long pinno;
					long balance;
					String accholder;
					File file = new File("CUSTOMERDETAILS.txt");

					if (!file.exists()) {
						file.createNewFile();
					}
					RandomAccessFile raf = new RandomAccessFile(file, "rw");
					boolean found = false;
					boolean f = false;
					while (raf.getFilePointer() < raf.length()) {
						nameNumberString = raf.readLine();
						index1 = nameNumberString.indexOf('|');
						index2 = nameNumberString.indexOf('<');
						index3 = nameNumberString.indexOf('>');
						accno = Long.parseLong(nameNumberString.substring(0, index1));
						pinno = Long.parseLong(nameNumberString.substring(index2 + 1, index3));
						balance = Long.parseLong(nameNumberString.substring(index3 + 1));
						// checking if accno and pin is correct
						if (accno == acno && pinno == pin) {
							found = true;
							// check if account has sufficent balance
							if (balance >= amount) {
								f = true;
							} else {
								System.out
										.println("insufficient balance account balance is lower than withdrwal amount");

							}
							break;
						}
					}
					// let in only if both balance and account is correct
					if (found == true && f == true) {
						File tmpFile = new File("temp.txt");
						RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
						raf.seek(0);
						while (raf.getFilePointer() < raf.length()) {
							nameNumberString = raf.readLine();
							index1 = nameNumberString.indexOf('|');
							index2 = nameNumberString.indexOf('<');
							index3 = nameNumberString.indexOf('>');
							accholder = nameNumberString.substring(index1 + 1, index2);
							accno = Long.parseLong(nameNumberString.substring(0, index1));
							pinno = Long.parseLong(nameNumberString.substring(index2 + 1, index3));
							balance = Long.parseLong(nameNumberString.substring(index3 + 1));
							if (accno == acno && pinno == pin) {
								balance = balance - amount;
								nameNumberString = String.valueOf(accno) + "|" + accholder + "<" + String.valueOf(pinno)
										+ ">" + String.valueOf(balance);
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
						System.out.println(amount + "$" + "withdrawn successfully ");
						w.changedenomination(amount);

					}
					if (found == false) {
						System.out.println("account no and pin doesnot match");
						raf.close();
					}
				} catch (IOException ioe) {
					System.out.println(ioe);
				}

				catch (NumberFormatException nef) {
					System.out.println(nef);
				}

			} else {
				System.out.println("ATM doesnot have enough balance");
			}
		} else {
			System.out.println(
					"Transaction not possible enter amount in range of 100 to 10000\nenter amount in multiples of 100");
		}

	}

	// function to check a t m balance
	public long ATMbalance() throws NumberFormatException, IOException {
		String nameNumberString;
		int index1;
		long money;
		long noofnotes;
		int index2;
		long balance = 0;
		File file = new File("ATMCASH.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		while (raf.getFilePointer() < raf.length()) {
			nameNumberString = raf.readLine();
			index1 = nameNumberString.indexOf('x');
			index2 = nameNumberString.indexOf('=');
			money = Long.parseLong(nameNumberString.substring(0, index1));
			noofnotes = Long.parseLong(nameNumberString.substring(index1 + 1, index2));
			balance = balance + (money * noofnotes);
		}
		raf.close();
		return balance;
	}

	// function to transfer money similar to withdrawal but only sender and reciever
	// accounts get changed not the atm
	public void TransferMoney(long sender, long reciever, long pin, long money) throws IOException {
		if (money <= 10000 && money >= 1000) {
			long accno;
			String nameNumberString;
			int index1;
			int index3;
			int index2;
			long pinno;
			long balance;
			String accholder;
			File file = new File("CUSTOMERDETAILS.txt");

			if (!file.exists()) {
				file.createNewFile();
			}
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			boolean found1 = false;
			boolean found2 = false;
			while (raf.getFilePointer() < raf.length()) {
				nameNumberString = raf.readLine();
				index1 = nameNumberString.indexOf('|');
				index2 = nameNumberString.indexOf('<');
				index3 = nameNumberString.indexOf('>');
				accno = Long.parseLong(nameNumberString.substring(0, index1));
				pinno = Long.parseLong(nameNumberString.substring(index2 + 1, index3));
				balance = Long.parseLong(nameNumberString.substring(index3 + 1));
				if ((accno == sender && pinno == pin)) {
					if (balance >= money) {
						found1 = true;
					} else {
						System.out.println("insufficient balance");
						break;
					}

				}
				if (accno == reciever) {
					found2 = true;
				}
			}
			if (found1 == true && found2 == true) {
				File tmpFile = new File("temp.txt");
				RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
				raf.seek(0);
				while (raf.getFilePointer() < raf.length()) {
					nameNumberString = raf.readLine();
					index1 = nameNumberString.indexOf('|');
					index2 = nameNumberString.indexOf('<');
					index3 = nameNumberString.indexOf('>');
					accholder = nameNumberString.substring(index1 + 1, index2);
					accno = Long.parseLong(nameNumberString.substring(0, index1));
					pinno = Long.parseLong(nameNumberString.substring(index2 + 1, index3));
					balance = Long.parseLong(nameNumberString.substring(index3 + 1));
					if (accno == sender && pinno == pin) {
						balance = balance - money;
						nameNumberString = String.valueOf(accno) + "|" + accholder + "<" + String.valueOf(pinno) + ">"
								+ String.valueOf(balance);
					}
					if (accno == reciever) {
						balance = balance + money;
						nameNumberString = String.valueOf(accno) + "|" + accholder + "<" + String.valueOf(pinno) + ">"
								+ String.valueOf(balance);
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
				System.out.println("$ " + money + " " + "transferred" + " " + " successfully ");
			} else if (found1 == false) {
				System.out.println("sender details incorrect");
			} else if (found2 == false) {
				System.out.println("reciever details incorrect");
			}
		} else {
			System.out.println("Transaction limit reached");
		}
	}
}
