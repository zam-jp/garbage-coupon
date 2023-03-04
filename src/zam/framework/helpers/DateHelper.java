/**
 * This helper class provide date related functions to automate parsing 
 * and formatting of dates.
 * 
 * @author Shaiful Nizam
 * @since 2019-06-08
 * @version 2019-06-08
 */
package zam.framework.helpers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Deprecated
public class DateHelper {
	private static DateHelper object;
//	private static String TIMESTAMP24 = "yyyy-MM-dd HH:mm:ss";
//	private static String TIMESTAMP12 = "yyyy-MM-dd hh:mm:ss a";
	private static String TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
//	private static String TIMESTAMP_DATE_ONLY = "yyyy-MM-dd";
//	private static String TIMESTAMP24_TIME_ONLY = "HH:mm:ss";
//	private static String TIMESTAMP12_TIME_ONLY = "hh:mm:ss a";
	private static String DISPLAY_TIME = "HH:mm:ss";
	private static String DISPLAY_DATE = "yyyy-MM-dd";
	private static String SYS_OUT_DATE = "dd-MMM-yyyy HH:mm:ss"; 
	
	private DateHelper() {
		
	}
	
	public static synchronized DateHelper getInstance() {
		if (object == null) {
			object = new DateHelper();
		}
		return object;
	}
	
	public Object clone() throws CloneNotSupportedException {
		/*
		 * Do not allow this class to be cloned since it is a singleton.
		 */
		throw new CloneNotSupportedException();
	}

//	public synchronized String getTimestamp24hr(Timestamp date) {
//		DateFormat df = new SimpleDateFormat(TIMESTAMP24);
//		return df.format(date);
//	}
	
//	public synchronized String getTimestamp12hr(Timestamp date) {
//		DateFormat df = new SimpleDateFormat(TIMESTAMP12);
//		return df.format(date);
//	}
	
//	public synchronized String getTimestampWithDateOnly(Timestamp date) {
//		DateFormat df = new SimpleDateFormat(TIMESTAMP_DATE_ONLY);
//		return df.format(date);
//	}
	
//	public synchronized String getTimestamp24hrWithTimeOnly(Timestamp date) {
//		DateFormat df = new SimpleDateFormat(TIMESTAMP24_TIME_ONLY);
//		return df.format(date);
//	}
	
//	public synchronized String getTimestamp12hrWithTimeOnly(Timestamp date) {
//		DateFormat df = new SimpleDateFormat(TIMESTAMP12_TIME_ONLY);
//		return df.format(date);
//	}
	
//	public synchronized String getCurrentTime24hr() {
//		Calendar cal = Calendar.getInstance();
//		DateFormat df = new SimpleDateFormat(TIMESTAMP24);
//		return df.format(cal.getTime());
//	}
		
//	public synchronized String getCurrentTime12hr() {
//		Calendar cal = Calendar.getInstance();
//		DateFormat df = new SimpleDateFormat(TIMESTAMP12);
//		return df.format(cal.getTime());
//	}
		
//	public synchronized String getCurrentTimeWithDateOnly() {
//		Calendar cal = Calendar.getInstance();
//		DateFormat df = new SimpleDateFormat(TIMESTAMP_DATE_ONLY);
//		return df.format(cal.getTime());
//	}
		
//	public synchronized String getCurrentTime24hrWithTimeOnly() {
//		Calendar cal = Calendar.getInstance();
//		DateFormat df = new SimpleDateFormat(TIMESTAMP24_TIME_ONLY);
//		return df.format(cal.getTime());
//	}
		
//	public synchronized String getCurrentTime12hrWithTimeOnly() {
//		Calendar cal = Calendar.getInstance();
//		DateFormat df = new SimpleDateFormat(TIMESTAMP12_TIME_ONLY);
//		return df.format(cal.getTime());
//	}
		
	public synchronized String getSysOutDate() {
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(SYS_OUT_DATE);
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
	public synchronized Date parser(String date) {
		try {
			DateFormat df = DateFormat.getInstance();
			return df.parse(date);
		} catch (ParseException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}
