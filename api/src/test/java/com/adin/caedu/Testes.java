package com.adin.caedu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Testes {

	public static void main(String[] dsa) {
			Calendar cal_atual = Calendar.getInstance();
			Calendar cal_data = Calendar.getInstance();
			cal_data.setTime(new Date());
			cal_atual.setTime(new Date());
			
			System.out.println(cal_atual.get(Calendar.DAY_OF_YEAR));
	}
	
	private String getCorte(String[] vencimento) {
		int[] retorno = new int[3];
		int day = Integer.parseInt(vencimento[0]);
		int month = Integer.parseInt(vencimento[1]);
		int year = Integer.parseInt(vencimento[2]);
		if(day == 1) {
			retorno[0] = 19;
			if((month - 1) == 0) {
				retorno[1] = 12;
				retorno[2] = year - 1;
			}
			else {
				retorno[1] = month - 1;
				retorno[2] = year;
			}
		}
		
		else if(day == 5) {
			retorno[0] = 23;
			if((month - 1) == 0) {
				retorno[1] = 12;
				retorno[2] = year - 1;
			}
			else {
				retorno[1] = month - 1;
				retorno[2] = year;
			}
		}
		
		else if(day == 10) {
			retorno[0] = 28;
			if((month - 1) == 0) {
				retorno[1] = 12;
				retorno[2] = year - 1;
			}
			else {
				retorno[1] = month - 1;
				retorno[2] = year;
			}
		}
		
		else if(day == 15) {
			retorno[0] = 2;
			retorno[1] = month;
			retorno[2] = year;
		}
		
		else if(day == 20) {
			retorno[0] = 8;
			retorno[1] = month;
			retorno[2] = year;
		}
		
		else {
			retorno[0] = 12;
			retorno[1] = month;
			retorno[2] = year;
		}
		
		return String.valueOf(retorno[2]) + "-"+
			String.valueOf(retorno[1]) + "-"+
			String.valueOf(retorno[0]);
	}
}
