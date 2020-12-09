package com.restcalculator.controllers;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restcalculator.models.Result;
import com.restcalculator.models.operations.AddOperation;
import com.restcalculator.models.operations.DivideOperation;
import com.restcalculator.models.operations.MultiplyOperation;
import com.restcalculator.models.operations.OperationInterface;
import com.restcalculator.models.operations.SubtractOperation;
import com.restcalculator.util.Constants;

/**
 * Main REST Calculator Controller
 * @author pranay.kekre
 *
 */
@RestController
@RequestMapping(Constants.CALCULATOR_PATH)
//this annotation permit Cross Origin request
@CrossOrigin()
public class CalculatorController {
	
	
	private static final Logger logger = LogManager.getLogger(CalculatorController.class);

	/**
	 * this method is responsible for addition operations only
	 * @param operation
	 * @return Result of the operation
	 */
	@PostMapping("/add")
	public Result add(@RequestBody AddOperation operation) {
		
		//logging in the console the beginning of the operation
		logger.info("Calling add operation!");
		
		//the result object that is returned to the client
		Result result = new Result();
		
		//check if the operation is not null
		if(operation != null) {
			logger.info("Input data:" + operation.toString());
			result = this.doOperation(operation);
		}else {
			//if the operation is null return log it and return an message error to the client 
			logger.error("Operation is null");
			result.setError(Boolean.TRUE);
			result.setMessage("Operation is not initializzed");
		}
		
		return result;
	}
	
	/**
	 * this method is responsible for subtraction operations only
	 * @param operation
	 * @return Result of the operation
	 */
	@PostMapping("/subtract")
	public Result subtract(@RequestBody SubtractOperation operation) {
		
		//logging in the console the beginning of the subtract operation
		logger.info("Calling substract operation!");
		Result result = new Result();

		if(operation != null) {
			logger.info("Input data: " + operation.toString());
			result = this.doOperation(operation);
		}else {
			logger.error("Operation is null");
			result.setError(Boolean.TRUE);
			result.setMessage("Operation is not initializzed");
		}
		
		return result;
	}
	
	/**
	 * this method is responsible for multiplication operations only
	 * @param operation
	 * @return Result of the operation
	 */
	@PostMapping("/multiply")
	public Result multiply(@RequestBody MultiplyOperation operation) {
		
		//logging in the console the beginning of the multipy operation
		logger.info("Calling multiply operation!");
		Result result = new Result();

		if(operation != null) {
			logger.info("Input data: " + operation.toString());
			result = this.doOperation(operation);
		}else {
			logger.error("Operation is null");
			result.setError(Boolean.TRUE);
			result.setMessage("Operation is not initializzed");
		}
		
		return result;
	}
	
	/**
	 * this method is responsible for division operations only
	 * @param operation
	 * @return Result of the operation
	 */
	@PostMapping("/divide")
	public Result divide(@RequestBody DivideOperation operation) {

		//logging in the console the beginning of the operation
		logger.info("Calling divide operation!");
		Result result = new Result();
		
		if(operation != null) {
			logger.info("Input data: " + operation.toString());
			result = this.doOperation(operation);
		}else {
			logger.error("Operation is null");
			result.setError(Boolean.TRUE);
			result.setMessage("Operation is not initializzed");
		}
		return result;
	}
	
	/**
	 * service that is responsible for creating the fibonacci series
	 * it's an iterative algorithm 
	 * @param value is positive number 
	 * @return
	 */
	@GetMapping("/fibonacci/{val}")
	public Result fibonacci(@PathVariable(name = "val") String value) {
		logger.info("starting the fibonacci sequence!");
		logger.info("inserted value: " + value);
		Result result = new Result();
		//check if the value is not null
		if(value == null || org.springframework.util.StringUtils.isEmpty(value)) {
			logger.error("No value inserted");
			result.setError(Boolean.TRUE);
			result.setMessage(Constants.ERROR_MESSAGE_NO_VALUE_INSERTED);
		}else {
			try {
				//parse the string value to bigdecimal object
				Integer intValue = Integer.parseInt(value);
				if(intValue < 0) {
					//No negative number permitted
					result.setError(Boolean.TRUE);
					result.setMessage(Constants.ERROR_MESSAGE_NEGATIVE_NUMBER);
				}else if(intValue == 0) {
					//if the value is 0
					result.setMessage("0"); 
				} else if(intValue == 1){
					//if the value is 1 return the same
					result.setMessage("1"); 
				} else {

					StringBuilder sbResult = new StringBuilder();
					Integer previous = 1;
					Integer next = 1;
					
					//iterative solution to Fibonacci Series
					//iterating until the previous value isn't greater than user input value
					for(int i = 1; previous <= intValue; i++) {
						logger.info(previous);
						sbResult.append(previous + "  ");

						// On each iteration, we are assigning second number
						// to the first number and assigning the sum of last two
						// numbers to the second number
						Integer sum = previous + next;
						previous = next;
						next = sum;
					}

					//assign the result to be returned to client
					result.setMessage(sbResult.toString());
				}
					
			}catch(NumberFormatException nfe) {
				//the value isn't correctly parsed / the value inserted is invalid
				result.setError(Boolean.TRUE);
				//return the correct error message to be correctly catched by the frontend client
				result.setMessage(Constants.ERROR_MESSAGE_VALUE_NOT_VALID_NUMBER);
			}catch(Exception ex) {
				//catch the generic exception in case
				result.setError(Boolean.TRUE);
				//return the correct error message to be correctly catched by the frontend client
				result.setMessage(Constants.ERROR_MESSAGE_GENERIC_ERROR);
			}
		}
		logger.info("Result : " + result);
		return result;
	}
	
	/**
	 * this method is responsible for executing the operation and catching error if there is one.
	 * @param operation
	 * @return Result object with the result of the operation and with 
	 */
	private Result doOperation(OperationInterface operation){
		
		//The result object contain the result of the operation
		Result result = null;
		try {
			logger.info("Executing the operation.");
			//execute the operation with the run method
			result = operation.run();
		}catch(Exception ex) {
			//catch exceptions
			logger.error("Error during execution of doOperation method: ", ex);
			result.setError(Boolean.TRUE);
			result.setMessage("Ops! something wrong happened while executing doOperation!");
		}
		
		logger.info("Final operation result : " + result.toString());
		
		return result;

	}

}
