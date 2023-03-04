/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-12
 * @version 2020-06-12
 */
package zam.framework.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
//	private static String TIMESTAMP24 = "yyyy-MM-dd HH:mm:ss";
//	private static String TIMESTAMP12 = "yyyy-MM-dd hh:mm:ss a";
	private static String TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
//	private static String TIMESTAMP_DATE_ONLY = "yyyy-MM-dd";
//	private static String TIMESTAMP24_TIME_ONLY = "HH:mm:ss";
//	private static String TIMESTAMP12_TIME_ONLY = "hh:mm:ss a";
	private static String DISPLAY_TIME = "HH:mm:ss";
	private static String DISPLAY_DATE = "yyyy-MM-dd";
	private static String SYS_DATE = "dd-MMM-yyyy HH:mm:ss"; 
	
	public DateUtil() {
		
	}
	
	public synchronized String getSysDate() {
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(SYS_DATE);
		return df.format(cal.getTime());
	}
		
	public synchronized String getTimestamp(Timestamp date) {
		DateFormat df = new SimpleDateFormat(TIMESTAMP);
		return df.format(date);
	}

	public synchronized String getDisplayTime(Timestamp date) {
		DateFormat df = new SimpleDateFormat(DISPLAY_TIME);
		return df.format(date);
	}

	public synchronized String getDisplayDate(Timestamp date) {
		DateFormat df = new SimpleDateFormat(DISPLAY_DATE);
		return df.format(date);
	}

	public synchronized String getCurrentTimestamp() {
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(TIMESTAMP);
		return df.format(cal.getTime());
	}
	
	public synchronized String getCurrentDisplayTime() {
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(DISPLAY_TIME);
		return df.format(cal.getTime());
	}
	
	public synchronized String getCurrentDisplayDate() {
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(DISPLAY_DATE);
		return df.format(cal.getTime());
	}
	
	public synchronized String formatter(String datePattern, Date date) {
		try {
			DateFormat df = new SimpleDateFormat(datePattern);
			return df.format(date);
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			return "";
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			return "";
		}
	}
	
	/**
	 * This method will parse a string into a Date object. The string must 
	 * represent a valid date or else it will throw a ParseException.
	 * @param String
	 * @return Date
	 */
	public synchronized Date parser(String date) throws ParseException {
		DateFormat df = DateFormat.getInstance();
		return df.parse(date);
	}

}
