package hellocucumber;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Keywords {
	
	public static String getCurrentTimeStamp() {
		Date date = new Date();
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
		return timeStamp;
	}
}
