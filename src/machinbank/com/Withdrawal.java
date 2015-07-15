package machinbank.com;

public class Withdrawal extends Transaction {

	
	private int amount;
	private Keypad keypad;
	private CashDispenser cashDispenser;
	private final static int CANCELED = 6;
	
	public Withdrawal(int userAccountNumber, Screen atmscreen, BankDatabase atmbankDatabase, Keypad atmkeypad,
			 CashDispenser atmCashDispenser){
		
		super(userAccountNumber, atmscreen, atmbankDatabase);
		keypad = atmkeypad;
		cashDispenser = atmCashDispenser;
	}
	
	@Override
	public void execute(){
		
		boolean cashDispensed = false;
		double availableBalance;
		
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		do{
			amount = displayMenuOfAmounts();
			
			if(amount != CANCELED){
				availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
				
				if(amount <= availableBalance){
					
					if(cashDispenser.isSufficientCashAvailable(amount)){
						
						bankDatabase.debit(getAccountNumber(), amount);
						cashDispenser.dispenseCash(amount);
						cashDispensed = true;
						
						screen.displayMessageLine("\nYour cash has been dispensed. Please take your cash now.");
					}
					else{
						screen.displayMessageLine("\nInsufficient cash availablein the ATM.\nPlease choose a smaller amount");
					}
				}
				else{
					screen.displayMessageLine("\nInsufficient funds in your account.\nPlease choose a smaller amount");
				}
			}
			else{
				screen.displayMessageLine("\nCanceling Transaction");
				return;
		}
	   }while(!cashDispensed);
	}
	
	
	private int displayMenuOfAmounts(){
		
		int userChoise = 0;
		
		Screen screen = getScreen();
		
		int[] amounts = {0, 20, 30, 40, 60, 100, 200};
		
		while (userChoise == 0){
			
			screen.displayMessageLine("\nWithdrawal Menu: ");
			screen.displayMessageLine("1 - $20");
			screen.displayMessageLine("2 - $40");
			screen.displayMessageLine("3 - $60");
			screen.displayMessageLine("4 - $100");
			screen.displayMessageLine("5 - $200");
			screen.displayMessageLine("6 - Cancel Transaction");
			screen.displayMessageLine("\nChoose a withdrawal amount: ");
			
			int input = keypad.getInput();
			
			switch (input){
			
			case 1 :
			case 2 :
			case 3 :
			case 4 :
			case 5 :
				userChoise = amounts[input];
				break;
			case CANCELED : 
				userChoise = CANCELED;
				break;
			default : 
				screen.displayMessageLine("\nInvalid selection. Try Again.");
			}
		}
		return userChoise;
	}
}
	
	
	
	
	
	
	
	
