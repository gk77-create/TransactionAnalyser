package org.sample.TransactionAnalyser.services;

import java.util.Map;

import org.sample.TransactionAnalyser.model.ResponseDto;
import org.sample.TransactionAnalyser.model.Transaction;

public interface TransactionObserver {

	public ResponseDto  calculateRelativebalance(String accountId, String fromDate, String toDate,Map<String, Transaction> inputList);
}
