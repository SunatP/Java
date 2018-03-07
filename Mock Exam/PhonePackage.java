

public class PhonePackage {
	private char packageName;
	private double monthlyFee;
	private int providedMinutes;
	private double pricePerMinute;
	
	/*
	 * Constructor Method
	 */
	public PhonePackage(char packageName, double monthlyFee, int providedMinutes, double pricePerMinute){
		// Your code goes here
                this.packageName = packageName;
                this.monthlyFee = monthlyFee;
                this.providedMinutes = providedMinutes;
                this.pricePerMinute = pricePerMinute;
	}
	
	/*
	 * return the total charge calculated from the number of minutes used 
	 */
	public double getMonthlyBill(int usedMinutes){
            double bill = this.monthlyFee;
		if(usedMinutes > providedMinutes){
			bill = bill + (usedMinutes - providedMinutes) * pricePerMinute;
		}
		return bill;
		// Your code goes here
	}
	
	public char getName(){
		return this.packageName;
	}
	
        @Override
	public String toString(){
		return "Package " + packageName + ": " + monthlyFee + " per month, " + 
				providedMinutes + " minutes are provided. \nAdditional minutes are " + 
				pricePerMinute + " per minute";
	}
}
