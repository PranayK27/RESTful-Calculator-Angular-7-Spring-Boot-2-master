package com.restcalculator.models.operations;

import com.restcalculator.models.Result;
import com.restcalculator.util.Constants;

public class SubtractOperation extends OperationAbstract {

	@Override
	public Result run() throws Exception {
		Result result = new Result();

		if(this.operand1 != null && this.operand2 != null) {
			result.setResult(operand1.subtract(operand2));
			result.setError(Boolean.FALSE);
		}else {
			result.setError(Boolean.TRUE);
			result.setMessage(Constants.ERROR_MESSAGE_OPERAND_NOT_VALID);
			getLogger().error("One of the operands is not valid" + this.toString());
		}

		return result;
	}

	
	@Override
	public String toString() {
		return "SubtractOperation [getOperand1()=" + getOperand1() + ", getOperand2()=" + getOperand2() + "]";
	}
	

}
