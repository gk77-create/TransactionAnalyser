package org.sample.TransactionAnalyser.services;

import java.util.Map;

import org.sample.TransactionAnalyser.model.Transaction;

public interface CustomCsvReader {
	
	public Map<String, Transaction> readDataLineByLine(String file);

}
