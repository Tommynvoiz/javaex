package machinbank.com;

public class BalanceInquiry extends Transaction{

	public BalanceInquiry(int userAccountNumber, Screen atmscreen, BankDatabase atmbankDatabase){
		
		super(userAccountNumber, atmscreen, atmbankDatabase);
	}
	
	@Override
	public void execute(){
		
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
		
		double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());
		
		screen.displayMessageLine("\nBalance Information");
		screen.displayMessage(" - Available Balance: ");
		screen.displayDollarAmount(availableBalance);
		screen.displayMessage(" - Total Balance: ");
		screen.displayDollarAmount(totalBalance);
		screen.displayMessageLine("");
	}
	
}
