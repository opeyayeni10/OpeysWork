package AgileTesting;

public class LoanCalculator {
	
	private static final double RATE_1=0.1, RATE_2=0.15;
	public static double CalculateInterest(String customerName, String accNumber, int amount, int term) {
		double interest=0;
		if((customerName.length()<2) || (customerName.length()>10)) {
			throw new IllegalArgumentException("invalid Customer name ["+customerName+"]");
		}
		if ((customerName.length()>=2) || (customerName.length()<=10)) {
			for(int i=0; i<customerName.length(); i++) {
				char ch = customerName.charAt(i);
				if(!Character.isLetter(ch)) {
					throw new IllegalArgumentException("invalid Customer name ["+customerName+"]");
				}
			}
		}
		if(accNumber.length()!=6 || accNumber.charAt(1)==0) {
			throw new IllegalArgumentException("invalid Account Number ["+accNumber+"]");	
		}
		if(amount<500 || amount>9000) {
			throw new IllegalArgumentException("invalid amount ["+amount+"]");
		}
		if(term<1 || term>30) {
			throw new IllegalArgumentException("invalid term ["+term+"]");
		}
		interest= amount*term*getRate(amount);
		return interest;
	}
	public static double getRate(double amount) {
		double rate=0;
		if(amount<=500) {
			rate=RATE_1;
		}else if(amount<=5000) {
			rate=RATE_2;
		}
		return rate;
	}
}
