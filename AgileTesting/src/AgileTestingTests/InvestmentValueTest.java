package AgileTestingTests;

import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static junitparams.JUnitParamsRunner.$;
import org.junit.Test;
import org.junit.runner.RunWith;

import AgileTesting.InvestmentValue;
import junitparams.*;

@RunWith(JUnitParamsRunner.class)
public class InvestmentValueTest {
	
	private final int VALID_INVESTMENT = 2000;
	private final int VALID_YEAR = 4;

	@Parameters
	private static final Object[] getValidInvestmentValue() {
		return $(
				$(1000,3,1061.21),       
				$(1001,4,1083.51),   
				$(2000,5,2208.16),
				$(2998,6,3376.23),
				$(2999,6,3377.36),
				$(3000,7,4221.30),
				$(3001,8,4433.84),
				$(4000,9,6205.31),
				$(4998,10,8141.22),
				$(4999,10,8142.84),
				$(5000,3,6125.22),
				$(5001,4,6555.29),
				$(7000,5,9817.86),
				$(9999,6,15005.80),
				$(10000,7,16057.81)
				);
	}
	
	@Parameters
	private static final Object[] getInvalidInvestmentValue() {
		return $(
				$(999),
				$(10001)
				);
	}
	
	@Parameters
	private static final Object[] getInvalidYears() {
		return $(
				$(0),
				$(2),
				$(11)
				);	
	}
	
	@Test
	@Parameters(method="getValidInvestmentValue")
	public void testValidInvestmentValue(int investment, int years, double finalAmount) {
		assertEquals(finalAmount, InvestmentValue.calculateInvestmentAmount(years, investment),0.01);
	}
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method="getInvalidInvestmentValue")
	public void testInvalidInvestmentValue(int investment) {
		InvestmentValue.calculateInvestmentAmount(VALID_YEAR, investment);
	}
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method="getInvalidYears")
	public void testInvalidYears(int years) {
		InvestmentValue.calculateInvestmentAmount(years, VALID_INVESTMENT);
	}

}
