package br.com.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static Date obterDataDiferencaDias(int dias) {
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.DAY_OF_MONTH, dias);
		return calender.getTime();
	}
}
