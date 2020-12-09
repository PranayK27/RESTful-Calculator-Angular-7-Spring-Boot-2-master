package com.restcalculator.util;

public class Constants {
	
	//Api version 
	public static final String API_VERSION = "v1";
	
	//calculator URL 
	public static final String CALCULATOR_PATH = "/api/" + API_VERSION + "/calculator";
	
	//MESSAGE ERRORS 
	public static final String ERROR_MESSAGE_DIVIDE_BY_ZERO = "Error: Can't divide by zero";
	public static final String ERROR_MESSAGE_OPERAND_NOT_VALID = "Error: One of the operands is not valid";
	public static final String ERROR_MESSAGE_NO_VALUE_INSERTED = "Error: no value is selected for the fibonacci sequence";
	public static final String ERROR_MESSAGE_VALUE_NOT_VALID_NUMBER = "Error: the value inserted isn't a correct number";
	public static final String ERROR_MESSAGE_GENERIC_ERROR = "Error: something went wrong!";
	public static final String ERROR_MESSAGE_NEGATIVE_NUMBER = "Error: negative number not permitted";


}
