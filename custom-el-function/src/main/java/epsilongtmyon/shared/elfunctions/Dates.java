package epsilongtmyon.shared.elfunctions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates {

	public static Date now() {
		return new Date();
	}

	public static String format(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
