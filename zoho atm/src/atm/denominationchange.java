package atm;

import java.io.IOException;

public class denominationchange {
//function to get possibility of notes which can be given
	public long[] changereq(long money) throws IOException {
		atmtohash at = new atmtohash();
		long[] available = at.atmhassh();
		long[] notesgiven = { 0, 0, 0 };
		long[] notes = { 2000, 500, 100 };
		int i = 0;
		long r;
		while (i < 3) {
			// coming from the maximum possibility to reduce the number of 100 rupee notes
			// given so that giving maximum possible 2000 and 500 rupee note
			r = money / notes[i];
			if (r > available[i]) {
				notesgiven[i] = available[i];
				money = money - (available[i] * notes[i]);
			} else {
				notesgiven[i] = r;
				money = money % notes[i];
			}
			i++;
		}

		return notesgiven;

	}
}
