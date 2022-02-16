package org.sample.TransactionAnalyser.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.sample.TransactionAnalyser.model.Transaction;
import org.sample.TransactionAnalyser.services.impl.CustomCsvReaderImpl;

class CustomCsvReaderImplTest {

	@Test
	void test() {
		String csvFilePath = "C:\\Users\\Satguru\\Desktop\\Transactions.csv";
		CustomCsvReaderImpl impl = new CustomCsvReaderImpl();
		Map<String, Transaction> mapCsv = impl.readDataLineByLine(csvFilePath);
		assertEquals(mapCsv.size(), 3);
	}

}
