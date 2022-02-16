package org.sample.TransactionAnalyser.services.impl;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.sample.TransactionAnalyser.model.ExcelColEnum;
import org.sample.TransactionAnalyser.model.Transaction;
import org.sample.TransactionAnalyser.model.TransactionTypeEnum;
import org.sample.TransactionAnalyser.services.CustomCsvReader;
import org.sample.TransactionAnalyser.util.Constants;
import org.sample.TransactionAnalyser.util.DateUtil;

import com.opencsv.CSVReader;

/**
 * This Class will read from CSV file and 
 * populate transaction details in a map
 * 
 * 
 * 
 * @author Gunjan kathpaliya
 *
 */
public class CustomCsvReaderImpl implements CustomCsvReader {

	
	
	
	/**
	 * 
	 * This method will read from csv file and 
	 * populate a map with key as Transaction Id And value as Transaction Object
	 *
	 */
	@Override
	public Map<String, Transaction> readDataLineByLine(String file) {

		Map<String, Transaction> map = new HashMap<>();
		
		// file reader and csv reader will automatically be closed once all instructions completed
		try (FileReader filereader = new FileReader(file); 
				CSVReader csvReader = new CSVReader(filereader);) {
			String[] nextRecord;
			//Skip csv header
			csvReader.skip(1);

			// we are going to read data line by line
			while ((nextRecord = csvReader.readNext()) != null) {
				for (String cell : nextRecord) {
					Transaction txn = new Transaction();

					String[] values = cell.split(Constants.COMMA_SEPARATOR);

					populateTxn(txn, values);

					//  If there is a reversal transaction 
					// then remove main transaction also from map
					//else put transaction in  map
					if (TransactionTypeEnum.REVERSAL.name().equals(txn.getTransactionType())) {
						map.remove(txn.getRelatedTransaction());
					} else {
						map.put(txn.getTransactionId(), txn);
					}

				}

			}

		} catch (Exception e) {
               System.out.println("Something Went Wrong");
		}
		return map;

	}

	/**
	 * Populates Transaction Object
	 * 
	 * @param txn
	 * @param values
	 */
	private void populateTxn(Transaction txn, String[] values) {
		txn.setTransactionId(values[ExcelColEnum.TRANSACTION_ID.getValue()]);

		txn.setFromAccountId(values[ExcelColEnum.FROM_ACCT.getValue()].trim());
		txn.setToAccountId(values[ExcelColEnum.TO_ACCT.getValue()].trim());
		txn.setCreatedAt(DateUtil.convertStringToDate(values[ExcelColEnum.CREATED_AT.getValue()].trim(), Constants.DATE_FORMAT));
		txn.setAmount(Double.valueOf(values[ExcelColEnum.AMOUNT.getValue()]));

		txn.setTransactionType(values[ExcelColEnum.TXN_TYPE.getValue()].trim());
		txn.setRelatedTransaction(values.length == 7 ? values[ExcelColEnum.RELATED_TXN.getValue()].trim() : null);
	}

}
