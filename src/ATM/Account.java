package ATM;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Account class holds the customer credentials and balances for multiple accounts.
 * 
 * Variables:
 * - customerNumber and pinNumber to identify the user.
 * - checkingBalance, savingBalance, fdBalance represent the balances of different accounts.
 *   Initialized with -1 to indicate the account is not opened.
 * 
 * Provides:
 * - Getters and setters for customer info and balances.
 * - Methods for deposits and withdrawals with balance updates.
 * - Input validation for monetary values.
 */
public class Account {
    private int customerNumber;
    private int pinNumber;

    private double checkingBalance = -1;  // -1 means account not opened
    private double savingBalance = -1;
    private double fdBalance = -1;

    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    // --- Getters and Setters for customerNumber and pinNumber ---
    public int setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
        return customerNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public int setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
        return pinNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    // --- Balance Getters and Setters ---
    public double getCheckingBalance() {
        return checkingBalance;
    }

    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public void setSavingBalance(double savingBalance) {
        this.savingBalance = savingBalance;
    }

    public double getFDBalance() {
        return fdBalance;
    }

    public void setFDBalance(double fdBalance) {
        this.fdBalance = fdBalance;
    }

    // --- Methods for calculation of withdrawal and deposit for each account ---
    public double calcCheckingWithdraw(double amount) {
        checkingBalance -= amount;
        return checkingBalance;
    }

    public double calcCheckingDeposit(double amount) {
        checkingBalance += amount;
        return checkingBalance;
    }

    public double calcSavingWithdraw(double amount) {
        savingBalance -= amount;
        return savingBalance;
    }

    public double calcSavingDeposit(double amount) {
        savingBalance += amount;
        return savingBalance;
    }

    public double calcFDWithdraw(double amount) {
        fdBalance -= amount;
        return fdBalance;
    }

    public double calcFDDeposit(double amount) {
        fdBalance += amount;
        return fdBalance;
    }

    /**
     * Reads user input safely for monetary amounts.
     * Loops until a valid double is entered.
     *
     * @return Valid double amount entered by user
     */
    private double getValidAmount() {
        while (!input.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a numeric amount.");
            input.nextLine();  // Clear invalid input
        }
        double amount = input.nextDouble();
        input.nextLine();  // consume newline
        return amount;
    }

    /**
     * Prompts the user for a withdrawal amount from Checking Account,
     * verifies sufficient balance, and updates balance.
     */
    public void getCheckWithdrawInput() {
        System.out.println("Checking Balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Amount to withdraw: ");
        double amount = getValidAmount();
        if (amount > 0 && checkingBalance >= amount) {
            calcCheckingWithdraw(amount);
            System.out.println("New Checking Balance: " + moneyFormat.format(checkingBalance));
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    /**
     * Prompts the user for a deposit amount into Checking Account
     * and updates the balance.
     */
    public void getCheckDepositInput() {
        System.out.println("Checking Balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Amount to deposit: ");
        double amount = getValidAmount();
        if (amount > 0) {
            calcCheckingDeposit(amount);
            System.out.println("New Checking Balance: " + moneyFormat.format(checkingBalance));
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    /**
     * Prompts the user for a withdrawal amount from Saving Account,
     * verifies sufficient balance, and updates balance.
     */
    public void getSavingWithdrawInput() {
        System.out.println("Saving Balance: " + moneyFormat.format(savingBalance));
        System.out.print("Amount to withdraw: ");
        double amount = getValidAmount();
        if (amount > 0 && savingBalance >= amount) {
            calcSavingWithdraw(amount);
            System.out.println("New Saving Balance: " + moneyFormat.format(savingBalance));
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    /**
     * Prompts the user for a deposit amount into Saving Account
     * and updates the balance.
     */
    public void getSavingDepositInput() {
        System.out.println("Saving Balance: " + moneyFormat.format(savingBalance));
        System.out.print("Amount to deposit: ");
        double amount = getValidAmount();
        if (amount > 0) {
            calcSavingDeposit(amount);
            System.out.println("New Saving Balance: " + moneyFormat.format(savingBalance));
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    /**
     * Prompts the user for a withdrawal amount from FD Account,
     * verifies sufficient balance, and updates balance.
     */
    public void getFDWithdrawInput() {
        System.out.println("FD Balance: " + moneyFormat.format(fdBalance));
        System.out.print("Amount to withdraw: ");
        double amount = getValidAmount();
        if (amount > 0 && fdBalance >= amount) {
            calcFDWithdraw(amount);
            System.out.println("New FD Balance: " + moneyFormat.format(fdBalance));
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    /**
     * Prompts the user for a deposit amount into FD Account
     * and updates the balance.
     */
    public void getFDDepositInput() {
        System.out.println("FD Balance: " + moneyFormat.format(fdBalance));
        System.out.print("Amount to deposit: ");
        double amount = getValidAmount();
        if (amount > 0) {
            calcFDDeposit(amount);
            System.out.println("New FD Balance: " + moneyFormat.format(fdBalance));
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
}