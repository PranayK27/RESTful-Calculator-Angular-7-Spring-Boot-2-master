package com.restcalculator.models;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;

/**
 * Contain the end result/error of the operation
 * @author pranay.kekre
 */

public class Result {

	//Message of the result (error message, or simply the result of fibonacci calculation)
	private String message = "";
	
	//If there is any error during the execution of the operation
	private Boolean error = Boolean.FALSE;
	
	//The final result of the aritmetic operation
	private BigDecimal result = null;
	
	//empty constructor
	public Result() {}
	
	public Result(String message, Boolean error, BigDecimal result) {
		super();
		this.message = message;
		this.error = error;
		this.result = result;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the error
	 */
	public Boolean getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(Boolean error) {
		this.error = error;
	}
	/**
	 * @return the result
	 */
	public BigDecimal getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(BigDecimal result) {
		this.result = result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Result [message=" + message + ", error=" + error + ", result=" + result + "]";
	}
	
}
