package atm;

import atm.display;
import atm.ATMcash;

import java.io.IOException;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		ATMcash atmcash = new ATMcash();
		/* initially an empty atm */
		customerdetails cm = new customerdetails();
		ATMprocess at = new ATMprocess();
		display dis = new display();
		Scanner sc = new Scanner(System.in);
		int f = 0;
		while (f != 1) {
			System.out.println("MAIN MENU\n1.LOAD CASH TO ATM\n2.Show Customer Details\n3.Show Atm Operations\n4EXIT");
			int option = sc.nextInt();
			if (option == 1) {
				/* asking for no of notes to be added */
				System.out.println("Enter no of 2000 rupees to be added");
				long numberofnotesupdated = sc.nextLong();
				/* function that adds noof notes in txt file */
				atmcash.addcash("2000", numberofnotesupdated);
				System.out.println("Enter no of 500 rupees to be added");
				numberofnotesupdated = sc.nextLong();
				atmcash.addcash("500", numberofnotesupdated);
				System.out.println("Enter no of 100 rupees to be added");
				numberofnotesupdated = sc.nextLong();
				atmcash.addcash("100", numberofnotesupdated);
				System.out.println(" denominations accepted. ");
				/* function to display the denominations */
				dis.displa("ATMCASH.txt");
			} else if (option == 2) {
				System.out.println("Enter 1 to add customer\n2 to view customer details");
				int optional = sc.nextInt();
				if (optional == 1) {
					System.out.println("Enter accountno");
					long acno = sc.nextLong();
					System.out.println("Enter name");
					String accholder = sc.next();
					System.out.println("Enter pin");
					long pin = sc.nextLong();
					System.out.println("Enter balance");
					long accbal = sc.nextLong();
					/* function to add a new account to text file */
					cm.addaccount(accholder, acno, pin, accbal);
				} else if (optional == 2) {
					dis.displa("CUSTOMERDETAILS.txt");
				}
			} else if (option == 3) {
				System.out.println(
						"Enter 1 to view balance\n2 to withdraw money\n3 to transfer money\n4 to check atm balance");
				int optional = sc.nextInt();
				if (optional == 1) {
					System.out.println("enter accno and pinno");
					long accno = sc.nextLong();
					long pinno = sc.nextLong();
					/* function to check balance of account */
					long balance = at.checkbalance(accno, pinno);
					if (balance != 0) {
						System.out.println("the balance is" + balance);
					}
				} else if (optional == 2) {

					System.out.println("Enter accno and pinno");
					long accno = sc.nextLong();
					long pinno = sc.nextLong();
					System.out.println("enter amount to be withdrawn");
					long amount = sc.nextLong();
					// function to withdraw amount
					at.withdrawal(accno, pinno, amount);
				} else if (optional == 3) {
					System.out.println("Enter sender accno and pinno");
					long accno = sc.nextLong();
					long pinno = sc.nextLong();
					System.out.println("Enter reciever accno and amount");
					long reciever = sc.nextLong();
					long amount = sc.nextLong();
					// function to transfer money from sender to reciever
					at.TransferMoney(accno, reciever, pinno, amount);
				} else if (optional == 4) {
					dis.displa("CUSTOMERDETAILS.txt");
					// function to check remaining amount in atm balance
					long a = at.ATMbalance();
					System.out.println("The ATM balance is" + a);
				}
			} else if (option == 4) {
				f = 1;
			}
		}
		sc.close();
	}
}