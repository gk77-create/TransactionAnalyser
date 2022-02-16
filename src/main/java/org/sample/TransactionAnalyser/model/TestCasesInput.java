package org.sample.TransactionAnalyser.model;

/**
 * 
 *  This model is used for representing test cases
 * 
 * @author Gunjan Kathpaliya
 *
 */
public class TestCasesInput {
	
	private RequestDto requestDto;
	private ResponseDto responseDto;
	
	
	
	public TestCasesInput(RequestDto requestDto, ResponseDto responseDto) {
		this.requestDto = requestDto;
		this.responseDto = responseDto;
	}
	public RequestDto getRequestDto() {
		return requestDto;
	}
	public void setRequestDto(RequestDto requestDto) {
		this.requestDto = requestDto;
	}
	public ResponseDto getResponseDto() {
		return responseDto;
	}
	public void setResponseDto(ResponseDto responseDto) {
		this.responseDto = responseDto;
	}
	
	

}
