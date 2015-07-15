package machinbank.com;

public abstract class Transaction {

	private int accountNumber;
	private Screen screen;
	private BankDatabase bankDatabase;
	
	public Transaction(int userAccountNumber, Screen atmscreen, BankDatabase atmbankDatabase){
		
		accountNumber = userAccountNumber;
		screen = atmscreen;
		bankDatabase = atmbankDatabase;
	}
	
	public int getAccountNumber(){
		return accountNumber;
	}
	
	public Screen getScreen(){
			return screen;
	}
	
	public BankDatabase getBankDatabase(){
		return bankDatabase;
	}
	
	abstract public void execute();
}
