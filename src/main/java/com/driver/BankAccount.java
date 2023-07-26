package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
            this.name = name;
            this.balance = balance;
            this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        int maxPossibleSum = 9 * digits;
        if (sum <0 || sum > maxPossibleSum)
        {
            throw new Exception("Account Number can not be generated");
        }

        // Generate the account number
        char[] accountNumber = new char[digits];
        for (int i = 0; i < digits; i++)
        {
            // Determine the current digit value
            int digitValue = Math.min(9, sum);
            accountNumber[i] = (char)('0' + digitValue);

            // Reduce the remaining sum
            sum -= digitValue;
        }

        return new String(accountNumber);

    }

    public void deposit(double amount) {
        //add amount to balance

        this.balance += amount;

    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance

        if((balance - amount) < minBalance)
        {
            throw new Exception("Insufficient Balance");
        }
        else {
            this.balance -= amount;
        }
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public double getMinBalance() {
        return minBalance;
    }


}