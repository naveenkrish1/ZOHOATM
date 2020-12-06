package atm;

import java.io.IOException;

public class withdrawcheck {
	// function to change denomination after getting no of notes to be reduced
	public void changedenomination(long money) throws IOException {
		ATMcash at = new ATMcash();
		denominationchange den = new denominationchange();
		long[] a = den.changereq(money);
		at.subtractcash("2000", a[0]);
		at.subtractcash("500", a[1]);
		at.subtractcash("100", a[2]);
	}

}