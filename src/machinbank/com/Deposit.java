package machinbank.com;

public class Deposit extends Transaction{

		
	private double amount;
	private Keypad keypad;
	private DepositSlot depositSlot;
	private final static int CANCELED = 0;
	
	public Deposit(int userAccountNumber, Screen atmscreen, BankDatabase atmbankDatabase, Keypad atmkeypad,
			DepositSlot atmdepositSlot ){
		
		super(userAccountNumber, atmscreen, atmbankDatabase);
		keypad = atmkeypad;
		depositSlot = atmdepositSlot;
	}
	
	@Override
	public void execute(){
		
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		amount = promptForDepositAmount();
		
		if (amount != CANCELED){
			screen.displayMessage("\nPlease insert a deposit envelope containing ");
			screen.displayDollarAmount(amount);
			screen.displayMessageLine(".");
			
			boolean envelopeReceived = depositSlot.isEnvelopeReceived();
			
			if (envelopeReceived){
				screen.displayMessageLine("\nBLA bla blla");
				bankDatabase.credit(getAccountNumber(), amount);
			}
			else{
				screen.displayMessageLine("\nYou did not insertan envelope. Transaction Canceled");
			}
		}
		else{
			screen.displayMessageLine("\nTransaction Canceled");
		}
	}
	
	private double promptForDepositAmount(){
		
		Screen screen = getScreen();
		
		screen.displayMessage("\nPlease enter a deposit amount in Cents(or 0 to Cancel)");
		int input = keypad.getInput();
		
		if(input == CANCELED)
			return CANCELED;
		else
			return (double) input /100;
	}
}
