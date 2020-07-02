package AgileTestingTests;

import static org.junit.Assert.*;

import org.junit.Before;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static junitparams.JUnitParamsRunner.$;
import org.junit.Test;
import org.junit.runner.RunWith;

import AgileTesting.LoanCalculator;
import junitparams.*;

@RunWith(JUnitParamsRunner.class)
public class LoanCalculatorTest {

	private LoanCalculator loanCalc;
	
	private final String VALID_NAME = "Kate";
	private final String VALID_ACCOUNT_NUM= "123456";
	private final int VALID_TERM = 10;
	private final int VALID_LOAN_AMOUNT = 1000;
	 
	@Parameters
	private static final Object[] getInvalidLoanAmount() {
		return $(
				$(499),
				$(9001)
				);
	}
	
	@Parameters
	private static final Object[] getInvalidCustomerName() {
		return $(
				$("E"),
				$("ABcdefghijkltm"),
				$("abc de"),
				$("abdre23")
				);
	}
	
	@Parameters
	private static final Object[] getInvalidAccountNumber() {
		return $(
				$("12345"),
				$("01234"),
				$("1234567")
				);
	}
	
	@Parameters
	private static final Object[] getInvalidLoanTerm() {
		return $(
				$(0),
				$(31)
				);
	}
	@Parameters
	private static final Object[] getValidInterest() {
		return $(
				$("Jo", "123456", 500, 1, 50),
				$("Dee", "238046",501, 2, 100.2),
				$("MichelleA", "123456",4998, 29, 14494.2),
				$("Jacqueline", "123456",4999, 30, 14997),
				$("Jo", "980067",5000, 1, 750),
				$("Dee", "145075",5001, 2, 1500.3),
				$("MichelleA", "123456",8999, 29, 39145.65),
				$("Jacqueline", "123456",9000, 30, 40500)
				);
	}
	@Before
	public void setUp() {
		loanCalc = new LoanCalculator();
	}
	@Test(expected= IllegalArgumentException.class)
	@Parameters(method= "getInvalidLoanAmount")
	public void testInvalidLoanAmount(int amount) {
		loanCalc.CalculateInterest(VALID_NAME, VALID_ACCOUNT_NUM, amount, VALID_TERM);
	}
	@Test(expected= IllegalArgumentException.class)
	@Parameters(method="getInvalidCustomerName")
	public void testInvalidCustomerName(String customerName) {
		loanCalc.CalculateInterest(customerName, VALID_ACCOUNT_NUM, VALID_LOAN_AMOUNT, VALID_TERM);
	}
	@Test(expected= IllegalArgumentException.class)
	@Parameters(method="getInvalidAccountNumber")
	public void testInvalidAccountNumber(String accNumber) {
		loanCalc.CalculateInterest(VALID_NAME, accNumber, VALID_LOAN_AMOUNT, VALID_TERM);
	}
	@Test(expected= IllegalArgumentException.class)
	@Parameters(method="getInvalidLoanTerm")
	public void testInvalidLoanTerm(int term) {
		loanCalc.CalculateInterest(VALID_NAME, VALID_ACCOUNT_NUM, VALID_LOAN_AMOUNT, term);
	}
	@Test(expected= IllegalArgumentException.class)
	@Parameters(method="getValidInterest")
	public void testValidInterest(String customerName, String accNumber, int amount, int term) {
	loanCalc.CalculateInterest(customerName, accNumber, amount, term);
	}
}
