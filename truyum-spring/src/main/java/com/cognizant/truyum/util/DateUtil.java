package com.cognizant.truyum.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static Date convertToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date convertedDate = null;
		try {
			convertedDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedDate;
	}

}
