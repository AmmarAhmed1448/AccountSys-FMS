class VDM{
        public static class UnexpectedValueException extends RuntimeException {
        public UnexpectedValueException(String message) {
            super(message);
        }
    }
    // Custom exception for invariant violation
    public static class InvariantViolationException extends RuntimeException {
        public InvariantViolationException(String message) {
            super(message);
        }
    }

    public static void initTest(AccountSys tester){
        if (!tester.init()) {
            throw new InvariantViolationException("The state of the system are not null");
        }
    }

    public static void invTest(AccountSys tester){
        if (!tester.inv()) {
            throw new InvariantViolationException("System state does not conform to the invariant constraint");
        }
    }

    public static void openAccPreTest(boolean condition){
        if(condition){
            throw new UnexpectedValueException("Account already exists with the provided accId or CNIC.");
        }
    }

    public static void closeAccBalPreTest(boolean condition){
        if(condition){
            throw new UnexpectedValueException("Balance must be zero to close the account.");
        }
    }
    
    public static void closeAccExistPreTest(boolean condition){
        if(condition){
            throw new UnexpectedValueException("Balance must be zero to close the account.");
        }
    }

    public static void depositPreTest(boolean condition){
        if(condition){
            throw new UnexpectedValueException("Target account does not exist.");
        }
    }

    public static void withdrawPreTest(boolean condition){
        if(condition){
            throw new UnexpectedValueException("Source account does not exist.");
        }
    }

    public static void transferPreTest(boolean condition){
        if(condition){
            throw new UnexpectedValueException("Source or target account does not exist.");
        }
    }

    public static void getAccPreTest(boolean condition){
        if(condition){
            throw new UnexpectedValueException("Account does not exist.");
        }
    }
    
    public static void insufficientBalance(){
        throw new UnexpectedValueException("You have Insufficient Balance to perform this operation");
    }
    
    public static void closedAccountException(boolean cond){
        if(cond){
            throw new UnexpectedValueException("The account is closed");
        }
    }
    
    public static void negativeAmountException(boolean cond){
        if(cond){
            throw new UnexpectedValueException("Amount can not be negative");
        }
    }
    
    public static void sizeConstraintException(String accId, String cnic, String pNo){
        if(accId.length() != 10){
            throw new UnexpectedValueException("Length of account Id should be 10");
        }
        else if (cnic.length() != 13) {
            throw new UnexpectedValueException("Length of CNIC No should be 10");
        }
        else if(pNo.length() != 11){
            throw new UnexpectedValueException("Length of Phone No. should be 10");
        }
    }

}
