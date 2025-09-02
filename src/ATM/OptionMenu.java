package ATM;

import java.io.IOException;
import java.util.HashMap;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * OptionMenu class manages all user interface aspects of the ATM system.
 * It extends Account to access balance-related methods and variables.
 * 
 * Responsibilities include:
 * - Displaying main menu and sub-menus
 * - Handling user registration and login
 * - Allowing users to select account types (Current, Saving, FD)
 * - Facilitating balance viewing, deposits, and withdrawals per account
 * - Validating user input to prevent errors
 */
public class OptionMenu extends Account {
    // Scanner to read user input
    Scanner menuInput = new Scanner(System.in);
    
    // Format money amounts to display with dollar sign and commas
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    // Store user credentials: customer number -> PIN
    HashMap<Integer, Integer> customerData = new HashMap<>();
    
    // Store user names: customer number -> User's name
    HashMap<Integer, String> customerNames = new HashMap<>();

    // Constructor preloads some default users (for demo)
    public OptionMenu() {
        customerData.put(952141, 191904);
        customerNames.put(952141, "Faheem");

        customerData.put(989947, 71976);
        customerNames.put(989947, "Waqas");
    }

    /**
     * Main menu loop for ATM interaction.
     * Displays options to register, login, or exit.
     * Reads user choice and calls corresponding methods.
     */
    public void mainMenu() throws IOException {
        System.out.println("====================================");
        System.out.println("     Welcome to Lena Dena Bank      ");
        System.out.println("====================================\n");

        while (true) {
            System.out.println("==== ATM Main Menu ====");
            System.out.println("1. Register New Account");
            System.out.println("2. Login to Existing Account");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    registerUser();   // Handle new user registration
                    break;
                case 2:
                    getLogin();      // Handle user login and authentication
                    break;
                case 3:
                    System.out.println("Thank you for using Lena Dena Bank. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Reads and validates integer input from user to protect against exceptions.
     */
    private int getValidIntInput() {
        while (!menuInput.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            menuInput.nextLine();  // Clear invalid input
        }
        int num = menuInput.nextInt();
        menuInput.nextLine();  // Consume newline character
        return num;
    }

    /**
     * Allows new customers to register.
     * Gathers name, customer number, PIN, and desired account type.
     * Initializes chosen account balance and marks others unopened.
     */
    public void registerUser() {
        System.out.print("Enter your name: ");
        String name = menuInput.nextLine();

        System.out.print("Choose a customer number: ");
        int customerNumber = getValidIntInput();

        // Check if customer number already exists
        if (customerData.containsKey(customerNumber)) {
            System.out.println("Customer number already exists. Try logging in.");
            return;
        }

        System.out.print("Choose a PIN: ");
        int pin = getValidIntInput();

        // Ask the user which account type they want
        System.out.println("Select account type to open:");
        System.out.println("1. Current Account");
        System.out.println("2. Saving Account");
        System.out.println("3. Fixed Deposit (FD) Account");
        System.out.print("Enter choice: ");

        int accChoice = getValidIntInput();

        // Initialize chosen account with zero balance, others marked unopened (-1)
        switch (accChoice) {
            case 1:
                setCheckingBalance(0.0);
                setSavingBalance(-1.0);
                setFDBalance(-1.0);
                break;
            case 2:
                setSavingBalance(0.0);
                setCheckingBalance(-1.0);
                setFDBalance(-1.0);
                break;
            case 3:
                setFDBalance(0.0);
                setCheckingBalance(-1.0);
                setSavingBalance(-1.0);
                break;
            default:
                System.out.println("Invalid account selection. Registration cancelled.");
                return;
        }

        // Store customer information
        customerData.put(customerNumber, pin);
        customerNames.put(customerNumber, name);

        System.out.println("Registration successful! Please login.\n");
    }

    /**
     * Authenticates user login by verifying customer number and PIN.
     * On success, welcomes user and prompts for account type.
     */
    public void getLogin() {
        System.out.print("Enter your customer number: ");
        int cn = getValidIntInput();

        System.out.print("Enter your PIN: ");
        int pn = getValidIntInput();

        // Validate credentials
        if (customerData.containsKey(cn) && customerData.get(cn) == pn) {
            setCustomerNumber(cn);
            setPinNumber(pn);
            String name = customerNames.get(cn);
            System.out.println("\nLogin successful! Welcome, " + name + "!\n");
            accountTypeMenu();
        } else {
            System.out.println("Invalid customer number or PIN. Please try again.");
        }
    }

    /**
     * Allows user to select which account to access or logout.
     * Validates if the account is owned (balance >= 0).
     */
    public void accountTypeMenu() {
        while (true) {
            System.out.println("\nSelect the account to access:");
            System.out.println("1. Current Account");
            System.out.println("2. Saving Account");
            System.out.println("3. Fixed Deposit (FD) Account");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");

            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    if (getCheckingBalance() < 0) {
                        System.out.println("You do not own a Current Account.");
                    } else {
                        currentAccountMenu();
                    }
                    break;
                case 2:
                    if (getSavingBalance() < 0) {
                        System.out.println("You do not own a Saving Account.");
                    } else {
                        savingAccountMenu();
                    }
                    break;
                case 3:
                    if (getFDBalance() < 0) {
                        System.out.println("You do not own a Fixed Deposit Account.");
                    } else {
                        fdAccountMenu();
                    }
                    break;
                case 4:
                    System.out.println("Logging out...\n");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Menu for Current Account operations:
     * View Balance, Withdraw Funds, Deposit Funds, or go Back.
     */
    public void currentAccountMenu() {
        while (true) {
            System.out.println("\nCurrent Account Menu:");
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw Funds");
            System.out.println("3. Deposit Funds");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: " + moneyFormat.format(getCheckingBalance()));
                    break;
                case 2:
                    getCheckWithdrawInput();
                    break;
                case 3:
                    getCheckDepositInput();
                    break;
                case 4:
                    return;  // Go back to account selection menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Menu for Saving Account operations:
     * View Balance, Withdraw Funds, Deposit Funds, or go Back.
     */
    public void savingAccountMenu() {
        while (true) {
            System.out.println("\nSaving Account Menu:");
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw Funds");
            System.out.println("3. Deposit Funds");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    System.out.println("Saving Balance: " + moneyFormat.format(getSavingBalance()));
                    break;
                case 2:
                    getSavingWithdrawInput();
                    break;
                case 3:
                    getSavingDepositInput();
                    break;
                case 4:
                    return;  // Go back to account selection menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Menu for Fixed Deposit Account operations:
     * View Balance, Withdraw Funds, Deposit Funds, or go Back.
     */
    public void fdAccountMenu() {
        while (true) {
            System.out.println("\nFD Account Menu:");
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw Funds");
            System.out.println("3. Deposit Funds");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    System.out.println("FD Balance: " + moneyFormat.format(getFDBalance()));
                    break;
                case 2:
                    getFDWithdrawInput();
                    break;
                case 3:
                    getFDDepositInput();
                    break;
                case 4:
                    return;  // Go back to account selection menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}