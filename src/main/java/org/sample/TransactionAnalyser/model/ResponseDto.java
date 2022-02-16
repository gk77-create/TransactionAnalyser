package org.sample.TransactionAnalyser.model;


/**
 * 
 *  Model which represents response for  search Criteria provided by user to 
 * search relative balance and number of transactions
 *  in a given time frame
 * 
 * @author Gunjan Kathpaliya
 *
 */
public class ResponseDto {

	private Double relativeBal;
	private int txnCount;
	
	
	public ResponseDto(Double relativeBal, int txnCount) {
		this.relativeBal = relativeBal;
		this.txnCount = txnCount;
	}
	public ResponseDto() {
		// TODO Auto-generated constructor stub
	}
	public Double getRelativeBal() {
		return relativeBal;
	}
	public void setRelativeBal(Double relativeBal) {
		this.relativeBal = relativeBal;
	}
	public int getTxnCount() {
		return txnCount;
	}
	public void setTxnCount(int txnCount) {
		this.txnCount = txnCount;
	}
}
