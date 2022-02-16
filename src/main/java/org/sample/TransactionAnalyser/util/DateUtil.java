package org.sample.TransactionAnalyser.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.sample.TransactionAnalyser.model.Transaction;

public class DateUtil {

	
	public static LocalDateTime  convertStringToDate(String str,String dateFormat)
	{
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

		return dateTime;
	}

	public static boolean validDateRange(LocalDateTime fDate, LocalDateTime tDate, Transaction txn) {
		return (!txn.getCreatedAt().isBefore(fDate)) && (!txn.getCreatedAt().isAfter(tDate));
	}
}
