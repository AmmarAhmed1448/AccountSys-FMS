import java.util.*;

class AccountSys {
    public static final double MAX_WITHDRAW = 20000;
    public static final double MIN_WITHDRAW = 500;
    public static final double MAX_DEPOSIT = 100000;
    public static final double MIN_DEPOSIT = 500;
    public static final double MIN_ACC_BALANCE = 100;
    public static final double MAX_ACC_BALANCE = 500000;

    private String accId;
    private String cnic;
    private String name;
    private double balance;
    private String phoneNo;
    private boolean isActive;
    private static Set<AccountSys> accountSystem = new HashSet<>();

    public AccountSys() {
        accId = null;
        cnic = null;
        balance = 0.0;
        phoneNo = null;
        isActive = true;

        // VDM.initTest(this.accId, this.cnic, this.balance, this.phoneNo,
        // this.isActive);
        VDM.initTest(this);

    }

    public boolean inv() {
        return (balance >= 0);
        // || (type = withdraw && amount >= MIN_WITHDRAW && amount <= MAX_WITHDRAW &&
        // balance >= amount) || (type = deposit && amount >= MIN_DEPOSIT && amount <=
        // MAX_DEPOSIT &&
        // balance >= amount)
    }

    public boolean init() {
        return ((accId == null) && (cnic == null) && (balance == 0.0) && (phoneNo == null) && (isActive == true));
    }

    private boolean accountExists(String accIdIn) {
        // Check if an account already exists with the provided AccId and CNIC
        for (AccountSys acc : accountSystem) {
            if (acc.accId.equals(accIdIn)) {
                return true;
            }
        }
        return false;
    }

    private boolean cnicExists(String cnicIn) {
        // Check if an account already exists with the provided AccId and CNIC
        for (AccountSys acc : accountSystem) {
            if (acc.cnic.equals(cnicIn)) {
                return true;
            }
        }
        return false;
    }

    private AccountSys findAccount(String accIdIn) {
        // Find and return an account with the provided AccId
        for (AccountSys acc : accountSystem) {
            if (acc.accId.equals(accIdIn)) {
                return acc;
            }
        }
        throw new IllegalArgumentException("Account not found.");
    }

    private boolean depositInRange(double bal){
        if(MIN_DEPOSIT <= bal && bal <= MAX_DEPOSIT){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean withdrawInRange(double bal){
        if(MIN_WITHDRAW <= bal && bal <= MAX_WITHDRAW){
            return true;
        }
        else{
            return false;
        }
    }



    public void openAcc(String accIdIn, String cnicIn, String nameIn, double balanceIn, String phoneNoIn) {
        // if (accountExists(accIdIn)) {
        // throw new IllegalArgumentException("Account already exists with the provided
        // AccId and CNIC.");
        // }

        VDM.openAccPreTest(accountExists(accIdIn) && cnicExists(cnicIn));
        VDM.negativeAmountException(balanceIn < 0);
        VDM.sizeConstraintException(accIdIn, cnicIn, phoneNoIn);
        // Create a new AccountSys object
        AccountSys newAccount = new AccountSys();
        newAccount.accId = accIdIn;
        newAccount.cnic = cnicIn;
        newAccount.name = nameIn;
        newAccount.balance = balanceIn;
        newAccount.phoneNo = phoneNoIn;
        newAccount.isActive = true;

        // Add the new account to the accountSystem HashSet
        accountSystem.add(newAccount);

        VDM.invTest(this);
    }

    public void closeAcc(String accId) {
        // if(balance != 0){
        // throw new IllegalArgumentException("Balance must be zero to close the
        // account.");
        // }

        VDM.closeAccExistPreTest(!accountExists(accId));
        VDM.closeAccBalPreTest(balance != 0);

        AccountSys acc = findAccount(accId);
        acc.isActive = false;
        VDM.invTest(this);
    }

    public void deposit(double amountDep, String toAccountDep) {
        // Preconditions
        // if (!accountExists(toAccountDep)) {
        // throw new IllegalArgumentException("Target account does not exist.");
        // }

        VDM.depositPreTest(!accountExists(toAccountDep));
        VDM.closedAccountException(!findAccount(toAccountDep).isActive);
        VDM.negativeAmountException(amountDep < 0);
        VDM.outOfRangeDepositException(depositInRange(amountDep));
        
        // Operation

        for (AccountSys acc : accountSystem) {
            if (acc.accId.equals(toAccountDep)) {
                acc.balance += amountDep;
            }
        }

        VDM.invTest(this);

    }

    public void withdraw(double amountWithdraw, String fromAccount) {
        // Preconditions
        // if (!accountExists(fromAccount)) {
        // throw new IllegalArgumentException("Source account does not exist.");
        // }

        VDM.withdrawPreTest(!accountExists(fromAccount));
        VDM.closedAccountException(!findAccount(fromAccount).isActive);
        VDM.negativeAmountException(amountWithdraw < 0);
        VDM.outOfRangeWithdrawException(withdrawInRange(amountWithdraw));

        // Operation
        for (AccountSys acc : accountSystem) {
            if (acc.accId.equals(fromAccount)) {
                if (acc.balance >= amountWithdraw) {
                    acc.balance -= amountWithdraw;
                } else {
                    VDM.insufficientBalance();
                }
            }
        }
        VDM.invTest(this);
    }

    public void transfer(double amountTrans, String toAccount, String fromAccount) {
        // Preconditions
        // if (!accountExists(fromAccount) || !accountExists(toAccount)) {
        // throw new IllegalArgumentException("Source or target account does not
        // exist.");
        // }

        VDM.negativeAmountException(amountTrans < 0);
        VDM.transferPreTest(!accountExists(fromAccount) || !accountExists(toAccount));
        VDM.closedAccountException(!findAccount(fromAccount).isActive || !findAccount(toAccount).isActive);

        // Find source and target accounts
        AccountSys sourceAccount = findAccount(fromAccount);
        AccountSys targetAccount = findAccount(toAccount);

        // Operation
        if (sourceAccount.balance >= amountTrans) {
            sourceAccount.balance -= amountTrans;
            targetAccount.balance += amountTrans;
        } else {
            VDM.insufficientBalance();
        }

        VDM.invTest(this);

    }

    public double getAccountBalance(String accountId) {
        // Preconditions
        // if (!accountExists(accountId)) {
        // throw new IllegalArgumentException("Account does not exist.");
        // }

        VDM.getAccPreTest(!accountExists(accountId));
        // Operation
        double currentBalance = findAccount(accountId).balance;

        return currentBalance;
    }

    public Map<String, Object> getAccountDetails(String accountId) {
        // Preconditions
        // if (!accountExists(accountId)) {
        // throw new IllegalArgumentException("Account does not exist.");
        // }

        VDM.getAccPreTest(!accountExists(accountId));

        // Operation
        AccountSys acc = findAccount(accountId);

        // Create a HashMap to store the attributes
        Map<String, Object> accountDetailsMap = new HashMap<>();
        accountDetailsMap.put("accId", acc.accId);
        accountDetailsMap.put("name", acc.name);
        accountDetailsMap.put("balance", acc.balance);
        accountDetailsMap.put("cnic", acc.cnic);
        accountDetailsMap.put("phoneNo", acc.phoneNo);

        return accountDetailsMap;
    }

}