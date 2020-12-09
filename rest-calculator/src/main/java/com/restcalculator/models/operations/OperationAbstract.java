package com.restcalculator.models.operations;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class implements the OperationInterface and add the operands that are used in other 
 * classes.
 * 
 * @author pranay.kekre
 *
 */
public abstract class OperationAbstract implements OperationInterface {
	
	private static final Logger logger = LogManager.getLogger(OperationAbstract.class);
	
	//the first operand that is obtained from the client
	protected BigDecimal operand1;
	
	//the second operand that is obtained from the client
	protected BigDecimal operand2;

	/**
	 * @return the operand1
	 */
	public BigDecimal getOperand1() {
		return operand1;
	}

	/**
	 * @param operand1 the operand1 to set
	 */
	public void setOperand1(BigDecimal operand1) {
		this.operand1 = operand1;
	}

	/**
	 * @return the operand2
	 */
	public BigDecimal getOperand2() {
		return operand2;
	}

	/**
	 * @param operand2 the operand2 to set
	 */
	public void setOperand2(BigDecimal operand2) {
		this.operand2 = operand2;
	}

	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	
}
