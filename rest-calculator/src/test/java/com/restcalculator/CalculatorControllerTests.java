package com.restcalculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.restcalculator.controllers.CalculatorController;
import com.restcalculator.util.Constants;

@RunWith(SpringRunner.class)
@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTests {
	
	@Autowired
    private MockMvc mockMvc;
	
	
	//this method test the add operation of 1 + 1
	@Test
	public void addPositiveOperatorTest() throws Exception{

		String jsonBody = "{\"operand1\": 1, \"operand2\": 1}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Constants.CALCULATOR_PATH + "/add").accept(
				MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonBody);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expectedResult = "{\"message\":\"\",\"error\":false,\"result\":2}";

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals(expectedResult, response.getContentAsString());
	}
	
	@Test
	public void addNegativeOperatorTest() throws Exception{

		String jsonBody = "{\"operand1\": -1, \"operand2\": -1}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Constants.CALCULATOR_PATH + "/add").accept(
				MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonBody);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expectedResult = "{\"message\":\"\",\"error\":false,\"result\":-2}";

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals(expectedResult, response.getContentAsString());
	}
	
	@Test
	public void addNullValueOperatorTest() throws Exception{

		String jsonBody = "{\"operand1\": null, \"operand2\": null}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Constants.CALCULATOR_PATH + "/add").accept(
				MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonBody);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expectedResult = "{\"message\":\"Error: One of the operands is not valid\",\"error\":true,\"result\":null}";

		assertEquals(HttpStatus.OK.value(), response.getStatus());




/*gfhgghxcfhgvjhkj.hjghfxfyjdhfxhfgchgv*/

		assertEquals(expectedResult, response.getContentAsString());

	}
	
	@Test
	public void divideZeroOperatorTest() throws Exception{

		String jsonBody = "{\"operand1\": 10, \"operand2\": 0}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Constants.CALCULATOR_PATH + "/divide").accept(
				MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonBody);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expectedResult = "{\"message\":\"Error: Can't divide by zero\",\"error\":true,\"result\":null}";

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		assertEquals(expectedResult, response.getContentAsString());

	}
	
	@Test
	public void negativeDivisionOperatorTest() throws Exception{

		String jsonBody = "{\"operand1\": 10, \"operand2\": -4}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Constants.CALCULATOR_PATH + "/divide").accept(
				MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonBody);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expectedResult = "{\"message\":\"\",\"error\":false,\"result\":-2.5000000000}";

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		assertEquals(expectedResult, response.getContentAsString());

	}
	
	@Test
	public void fibonacciOperationTests_testOk() throws Exception{

		String valueParam = "30";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(Constants.CALCULATOR_PATH + "/fibonacci/" + valueParam).accept(
				MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
						
		String expectedResult = "{\"message\":\"1  1  2  3  5  8  13  21  \",\"error\":false,\"result\":null}";

		assertEquals(expectedResult, response.getContentAsString());
	}
	
	@Test
	public void fibonacciOperationTests_zeroInput() throws Exception{

		String valueParam = "0";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(Constants.CALCULATOR_PATH + "/fibonacci/" + valueParam).accept(
				MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		String expectedResult = "{\"message\":\"0\",\"error\":false,\"result\":null}";

		assertEquals(expectedResult, response.getContentAsString());

	}
	
	@Test
	public void fibonacciOperationTests_oneInput() throws Exception{

		String valueParam = "1";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(Constants.CALCULATOR_PATH + "/fibonacci/" + valueParam).accept(
				MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		String expectedResult = "{\"message\":\"1\",\"error\":false,\"result\":null}";

		assertEquals(expectedResult, response.getContentAsString());

	}
	
	
	@Test
	public void fibonacciOperationTests_negativeInput() throws Exception{

		String valueParam = "-10";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(Constants.CALCULATOR_PATH + "/fibonacci/" + valueParam).accept(
				MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		String expectedResult = "{\"message\":\"Error: negative number not permitted\",\"error\":true,\"result\":null}";

		assertEquals(expectedResult, response.getContentAsString());

	}



}
