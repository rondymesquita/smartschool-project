package br.com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {
	
	/**
	 * @param date
	 * @return
	 * Converte um Date para uma String no formato "dd/MM/yyyy"
	 */
	public static String dateToString(Date date) {

		SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dtFormat.format(date);

	}
	
	public static String timeToString(Date hout) {

		SimpleDateFormat dtFormat = new SimpleDateFormat("HH:mm:ss");
		return dtFormat.format(hout);

	}
	
	
	/**
	 * @param str
	 * @return
	 * @throws ParseException
	 * Converte uma String no formato "dd/MM/yyyy" para um Calendar
	 */
	public static Calendar stringDateToCalendar(String str) throws ParseException {
		
		SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dtFormat.parse(str);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;

	}
	
	/**
	 * @return
	 * Converte uma String no formato "HH:mm" (hora e minuto) para um Calendar
	 * @throws ParseException 
	 */
	public static Calendar stringHourToCalendar(String str) throws ParseException{
		
		SimpleDateFormat dtFormat = new SimpleDateFormat("HH:mm");
		Date date = dtFormat.parse(str);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
		
	}

	
	public static void main(String[] args) throws ParseException {
		
//		System.out.println(dateToString(Calendar.getInstance().getTime()));
//		System.out.println(stringHourToCalendar("22:30").getTime());
		
		System.out.println(Calendar.getInstance().get(Calendar.MONTH));
		
	}

}
