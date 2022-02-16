package org.sample.TransactionAnalyser;

import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

import org.sample.TransactionAnalyser.model.ResponseDto;
import org.sample.TransactionAnalyser.model.Transaction;
import org.sample.TransactionAnalyser.services.CustomCsvReader;
import org.sample.TransactionAnalyser.services.TransactionObserver;
import org.sample.TransactionAnalyser.services.impl.CustomCsvReaderImpl;
import org.sample.TransactionAnalyser.services.impl.TransactionObserverImpl;
import org.sample.TransactionAnalyser.util.Constants;

/**
 * @author Satguru
 *
 */
public class App {

    TransactionObserver txnObserver;
    CustomCsvReader csvreader; 
    
	/**
	 * Main method to invoke the flow
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		App app =new App();
		
		String csvFilePath = "src/main/resources/Transactions.csv";
		app.processData(csvFilePath);
		
	}
	
	
	
    /**
     * @param csvFilePath : Path of Transactions CSV
     * 
     * This method will take read csv, 
     * populate a map ,
     *  process the map for fetching relative balance and no of transactions during a time frame
     */
    public void processData(String csvFilePath)
    {
    	txnObserver = new TransactionObserverImpl();
    	csvreader   = new CustomCsvReaderImpl();
    	
    	Map<String, Transaction> inputMap = csvreader.readDataLineByLine(csvFilePath);

    	System.out.println("Please provide account ID and then press enter::");
		
    	try(Scanner scanner = new Scanner(new InputStreamReader(System.in));)
    	{
    		
    		String accountId = scanner.nextLine();
    		System.out.println("Please provide From Date and then press enter:");
    		String fromDate = scanner.nextLine();
    		System.out.println("Please provide To Date and then press enter:");
    		String toDate = scanner.nextLine();
    		
    		ResponseDto result = txnObserver.calculateRelativebalance(accountId, fromDate,
    				toDate, inputMap);
    	    
    		DecimalFormat format1 = new DecimalFormat(Constants.DECIMAL_FORMAT);
    		Double val = result.getRelativeBal();
    		String formatted = format1.format(val);
    		printOutput(result, formatted);
    	}
    	catch(Exception e)
    	{
    		System.out.println("Something Went Wrong");
    	}
		
		
		
    }

    
	/**
	 * This method will print the response
	 * 
	 * @param result
	 * @param formatted
	 */
	private void printOutput(ResponseDto result, String formatted) {
		System.out.println();
		System.out.println("Relative balance for the period is:" + formatted);
		System.out.println("Number of transactions included is: " + result.getTxnCount());
	}

	

}
