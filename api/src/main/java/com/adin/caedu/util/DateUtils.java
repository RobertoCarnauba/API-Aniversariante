package com.adin.caedu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String getDataCorte(Date data_vencimento) {
		String df_day = new SimpleDateFormat("dd").format(data_vencimento);
		String df_month = new SimpleDateFormat("MM").format(data_vencimento);
		String df_year = new SimpleDateFormat("yyyy").format(data_vencimento);
		
		int day = Integer.parseInt(df_day);
		int month = Integer.parseInt(df_month);
		int year = Integer.parseInt(df_year);
		
		int day_res = 0, month_res = 0, year_res = 0;
		
		if(day == 1) {
			day_res = 19;
			if(month - 1 >= 1) {
				month_res = month - 1;
				year_res = year;
			}
			else {
				month_res = 12;
				year_res = year - 1;
			}
		}
		
		else if(day == 5) {
			day_res = 23;
			if(month - 1 >= 1) {
				month_res = month - 1;
				year_res = year;
			}
			else {
				month_res = 12;
				year_res = year - 1;
			}
		}
		
		else if(day == 10) {
			day_res = 28;
			if(month - 1 >= 1) {
				month_res = month - 1;
				year_res = year;
			}
			else {
				month_res = 12;
				year_res = year - 1;
			}
		}
		
		else if(day == 15) {
			day_res = 2;
			month_res = month;
			year_res = year;
		}
		
		else if(day == 20) {
			day_res = 8;
			month_res = month;
			year_res = year;
		}
		
		else {
			day_res = 12;
			month_res = month;
			year_res = year;
		}
		
		return String.valueOf(
				year_res + "-" + month_res + "-" + day_res
				);
	}
}
