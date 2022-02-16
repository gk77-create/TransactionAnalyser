package org.sample.TransactionAnalyser.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sample.TransactionAnalyser.model.RequestDto;
import org.sample.TransactionAnalyser.model.ResponseDto;
import org.sample.TransactionAnalyser.model.TestCasesInput;
import org.sample.TransactionAnalyser.model.Transaction;
import org.sample.TransactionAnalyser.util.Constants;

public final class TransactionObserverImplTest {
	@ParameterizedTest
	@MethodSource("fetchinput")
	void test(TestCasesInput tc) {
		TransactionObserverImpl txn = new TransactionObserverImpl();
		CustomCsvReaderImpl csvreader = new CustomCsvReaderImpl();
		String csvFilePath = "src/main/resources/Transactions.csv";
		Map<String, Transaction> inputMap = csvreader.readDataLineByLine(csvFilePath);

		RequestDto request = tc.getRequestDto();
		ResponseDto expectedRes = tc.getResponseDto();
		ResponseDto response = txn.calculateRelativebalance(request.getAccountId(), request.getFromDate(),
				request.getToDate(), inputMap);

		DecimalFormat format1 = new DecimalFormat(Constants.DECIMAL_FORMAT);
		String eformatted = format1.format(expectedRes.getRelativeBal());
		String aformatted = format1.format(response.getRelativeBal());

		assertEquals(eformatted, aformatted);
		assertEquals(expectedRes.getTxnCount(), response.getTxnCount());

	}

	private static List<TestCasesInput> fetchinput() {
		List<TestCasesInput> list = new ArrayList<TestCasesInput>();
		TestCasesInput tcInput = new TestCasesInput(
				new RequestDto("ACC334455", "20/10/2018 12:00:00", "20/10/2018 19:00:00"),
				new ResponseDto(new Double("-25.0"), 1));

		TestCasesInput tcInput1 = new TestCasesInput(
				new RequestDto("ACC778899", "20/10/2018 12:00:00", "20/10/2018 19:00:00"),
				new ResponseDto(new Double("30.0"), 2));

		TestCasesInput tcInput2 = new TestCasesInput(
				new RequestDto("ACC778899", "20/10/2018 12:00:00", "25/10/2018 19:00:00"),
				new ResponseDto(new Double("37.25"), 3));

		TestCasesInput tcInput3 = new TestCasesInput(
				new RequestDto("ACC998877", "20/10/2018 12:00:00", "25/10/2018 19:00:00"),
				new ResponseDto(new Double("-5.0"), 1));

		TestCasesInput tcInput4 = new TestCasesInput(
				new RequestDto("ACC9988771", "20/10/2018 12:00:00", "25/10/2018 19:00:00"),
				new ResponseDto(new Double("0.0"), 0));

		list.add(tcInput);
		list.add(tcInput1);
		list.add(tcInput2);
		list.add(tcInput3);
		list.add(tcInput4);
		return list;

	}

}
