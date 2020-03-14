package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Keywords {
	
	public static String getDriverPath() throws Exception {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("mac")) {
			return Constants.CHROME_DRIVER_PATH_MAC;
		} else if (os.toLowerCase().startsWith("windows")) {
			return Constants.CHROME_DRIVER_PATH_WINDOWS;
		} else {
			throw new Exception("OS Name: " + os + ", No ChromeDriver availbale for this operating system.");
		}
	}
	
	public static String getCurrentTimeStamp() {
		Date date = new Date();
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
		return timeStamp;
	}
}
