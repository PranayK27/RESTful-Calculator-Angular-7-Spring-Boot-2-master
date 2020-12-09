package com.restcalculator.models.operations;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.restcalculator.models.Result;
import com.restcalculator.util.Constants;

/**
 * Class responsible for division, it extends the abstract class OperationAbstract 
 * that implement the OperationInterface,
 * and inherit the operand1 and operand2.
 * 
 * @author pranay.kekre
 *
 */
public class DivideOperation extends OperationAbstract {
	
	private final BigDecimal ZERO = new BigDecimal(0);
	
	@Override
	public Result run() throws Exception {
		
		Result result = new Result();

		//check if both operand are not null
		if(this.operand1 != null && this.operand2 != null) {
			//can't divide a value by zero
			if(operand2.compareTo(ZERO) == 0) {
				//set an error and return to the client che error
				result.setError(Boolean.TRUE);
				result.setMessage(Constants.ERROR_MESSAGE_DIVIDE_BY_ZERO);
				getLogger().error("Can't divide by Zero");
			}else {
				//Scale and round che result (in some case if not setted the BigDecimal.divide can throw an ArithmeticException) 
				result.setResult(operand1.divide(operand2, 10, RoundingMode.CEILING));
				//there is no error here
				result.setError(Boolean.FALSE);
			}
		}else {
			//if one of the operand is null return an message error that can be handled by the client application
			result.setError(Boolean.TRUE);
			result.setMessage(Constants.ERROR_MESSAGE_OPERAND_NOT_VALID);
			getLogger().error("One of the operands is not valid" + this.toString());
		}

		return result;
	}

	
	@Override
	public String toString() {
		return "DivideOperation [getOperand1()=" + getOperand1() + ", getOperand2()=" + getOperand2() + "]";
	}

}
