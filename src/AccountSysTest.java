import java.util.Map;

public class AccountSysTest {
  public static void main(String[] args) {
    AccountSys accountSys = new AccountSys();
    char choice;
    try {
      do {
        System.out.println("\n\t\tAccountSystem Tester\n");
        System.out.println("1. Open Account");
        System.out.println("2. Close Account");
        System.out.println("3. Deposit Cash");
        System.out.println("4. Withdraw Cash");
        System.out.println("5. Transfer Cash");
        System.out.println("6. Get Balance");
        System.out.println("6. Get account your Details");
        System.out.println("7. Quit");
        System.out.print("Enter choice (1-7): ");
        choice = EasyIn.getChar();
        try {
          switch (choice) {
            case '1':
            System.out.println("Open Account");
              option1(accountSys);
              break;
            case '2':
            System.out.println("Close Account");
              option2(accountSys);
              break;
            case '3':
            System.out.println("Deposit Amount");
              option3(accountSys);
              break;
            case '4':
              System.out.println("Withdraw Amount");
              option4(accountSys);
              break;
            case '5':
              System.out.println("Transfer Amount");
              option5(accountSys);
              break;
            case '6':
              System.out.println("Get Balance");
              option6(accountSys);
              break;
            case '7':
              System.out.println("Get your account details");
              option7(accountSys);
              break;
            default:
              System.out.println("\n Invalid choice. Please enter a number between 1 and 6.");
              break;
          }
        } catch (VDM.UnexpectedValueException e) {
          System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
          e.printStackTrace();
        }
      } while (choice != '7');
    } catch (VDM.UnexpectedValueException e) {
      System.out.println("Error: Initial object breaks invariant. " + e.getMessage());
      EasyIn.pause("\nPress Enter to quit");
    }
  }

  // Open Account
  public static void option1(AccountSys accSystem) throws VDM.UnexpectedValueException {
    System.out.print("Enter accID: ");
    String accID = EasyIn.getString();
    System.out.print("Enter cnic: ");
    String cnic = EasyIn.getString();
    System.out.print("Enter name: ");
    String name = EasyIn.getString();
    System.out.print("Enter Phone No: ");
    String phone = EasyIn.getString();
    System.out.print("Enter initial balance");
    double balance = EasyIn.getDouble();
    accSystem.openAcc(accID, cnic, name, balance, phone);
    System.out.println("Initial Account opened successfully.");
  }

  // Close Account
  public static void option2(AccountSys accSystem) throws VDM.UnexpectedValueException {
    System.out.print("Enter accID: ");
    String accId = EasyIn.getString();
    accSystem.closeAcc(accId);
    System.out.println("Account closed successfully");
  }

  // Deposit amount
  public static void option3(AccountSys accSystem) throws VDM.UnexpectedValueException {
    System.out.print("Enter Amount");
    double amount = EasyIn.getDouble();
    System.out.print("Enter target account");
    String toAcc = EasyIn.getString();
    accSystem.deposit(amount, toAcc);
    System.out.println("Amount deposited successfully.");
  }

  // Withdraw amount
  public static void option4(AccountSys accSystem) throws VDM.UnexpectedValueException {
    System.out.print("Enter Amount");
    double amount = EasyIn.getDouble();
    System.out.print("Enter target account");
    String fromAcc = EasyIn.getString();
    accSystem.withdraw(amount, fromAcc);
    System.out.println("Amount Withdrawn successfully.");
  }

  // transfer amount
  public static void option5(AccountSys accSystem) throws VDM.UnexpectedValueException {
    System.out.print("Enter Amount");
    double amount = EasyIn.getDouble();
    System.out.print("Enter target account");
    String toAcc = EasyIn.getString();
    System.out.print("Enter source account");
    String fromAcc = EasyIn.getString();
    accSystem.transfer(amount, toAcc, fromAcc);
    System.out.println("Amount transferred successfully.");
  }

  // get account balance
  public static void option6(AccountSys accSystem) throws VDM.UnexpectedValueException {
    System.out.print("Enter account id");
    String accId = EasyIn.getString();

    double balance = accSystem.getAccountBalance(accId);
    System.out.println("Balance = " + balance);
    // System.out.println();
  }
  
  public static void option7(AccountSys accSystem) throws VDM.UnexpectedValueException {
    System.out.print("Enter account id");
    String accId = EasyIn.getString();
    
    Map<String, Object> details = accSystem.getAccountDetails(accId);
    System.out.print("Account Details" + details);
  }

}
