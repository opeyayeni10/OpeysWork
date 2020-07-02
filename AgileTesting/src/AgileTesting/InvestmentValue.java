package AgileTesting;

public class InvestmentValue {
	private final static double RATE=0.02, RATE1=0.05, RATE2=0.07;
	
public static double calculateInvestmentAmount(int years, int startAmount) {
	double finalAmount=0;
	
	if(startAmount<1000 || startAmount >10000) {
		throw new IllegalArgumentException("Invalid Investment ["+startAmount+"]");
	}
	if(years<3 || years>10) {
		throw new IllegalArgumentException("Invalid investment period ["+years+"]");
	}
	double rate=getRate(startAmount);
		finalAmount = startAmount;
		for(int i=1; i<=years; i++) {
			finalAmount = finalAmount*(1 + rate);
		}
		
	return finalAmount;
}
public static double getRate(int startAmount) {
	double rate;
	if(startAmount<2999) {
		rate=RATE;
	}
	else if(startAmount>3000) {
		rate=RATE1;
	}
	else {
		rate=RATE2;
	}
	return rate;
}
}
