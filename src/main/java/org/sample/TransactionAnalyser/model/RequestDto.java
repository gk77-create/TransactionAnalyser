package org.sample.TransactionAnalyser.model;

/**
 * @author Gunjan Kathpaliya
 *
 *
 * Model which represents search Criteria provided by user to 
 * search relative balance and number of transactions
 *  in a given time frame
 */
public class RequestDto {
	
	private String accountId;
	private String fromDate;
	private String toDate ;
	
	public RequestDto(String accountId, String fromDate, String toDate) {
		this.accountId=accountId;
		this.fromDate=fromDate;
		this.toDate=toDate;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
}
