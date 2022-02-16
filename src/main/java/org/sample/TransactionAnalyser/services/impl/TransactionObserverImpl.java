package org.sample.TransactionAnalyser.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.sample.TransactionAnalyser.model.ResponseDto;
import org.sample.TransactionAnalyser.model.Transaction;
import org.sample.TransactionAnalyser.services.TransactionObserver;
import org.sample.TransactionAnalyser.util.Constants;
import org.sample.TransactionAnalyser.util.DateUtil;

/**
 * @author Gunjan Kathpaliya
 *
 *This class contains business logic for doing the search on the basis of date range
 *
 *
 */
public class TransactionObserverImpl implements TransactionObserver {

	/**
	 *
	 *  This method takes account id, from date  and to date as input 
	 *  and provides the response on the basis of that
	 *  
	 */
	public ResponseDto  calculateRelativebalance(String accountId, final String strFromDate, String strToDate,
			Map<String, Transaction> inputList) {
		
		LocalDateTime fDate = DateUtil.convertStringToDate(strFromDate, Constants.DATE_FORMAT);
		LocalDateTime tDate = DateUtil.convertStringToDate(strToDate, Constants.DATE_FORMAT);

		
		// Fetch all the transactions for accountId and which is in range of from and to date inclusive
		Predicate<Entry<String, Transaction>> validAcctPrdicate = txn -> validAcct(accountId, txn.getValue())
				&& (DateUtil.validDateRange(fDate, tDate, txn.getValue()));

		List<Transaction> validList = inputList.entrySet().parallelStream().filter(validAcctPrdicate)
				.map(x -> x.getValue()).collect(Collectors.toList());

		//Calculate relative balance
		Optional<Double> result = validList.stream().map(t -> {
			return fetchAmount(accountId, t);
		}).reduce(Double::sum);
		
		ResponseDto dto = new ResponseDto();
		dto.setRelativeBal(result.isPresent() ? result.get() : 0.00);
		dto.setTxnCount(validList.size());

		return dto;

	}

	/**
	 * @param accountId
	 * @param txn
	 * @return
	 */
	private boolean validAcct(String accountId, Transaction txn) {
		return txn.getFromAccountId().equals(accountId) || txn.getToAccountId().equals(accountId);
	}

	/**
	 * @param accountId
	 * @param t
	 * @return
	 */
	private Double fetchAmount(String accountId, Transaction t) {
		if (t.getFromAccountId().equals(accountId)) {

			return -t.getAmount();
		} else {

			return t.getAmount();
		}
	}

}
