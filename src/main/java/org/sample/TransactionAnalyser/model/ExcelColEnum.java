package org.sample.TransactionAnalyser.model;

public enum ExcelColEnum {

	TRANSACTION_ID(0),
    FROM_ACCT(1),
    TO_ACCT(2),
    CREATED_AT(3),
    AMOUNT(4),
    TXN_TYPE(5),
    RELATED_TXN(6)
    ;

    private final int value;

    ExcelColEnum(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
	
}
