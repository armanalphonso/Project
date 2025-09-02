package ATM;

import java.io.IOException;

/**
 * This is the main class to start the ATM application.
 * 
 * It creates an OptionMenu object and calls its mainMenu method
 * which handles user input and all ATM operations such as registration,
 * login, and transactions.
 * 
 * The main method throws IOException because user input operations
 * might throw it.
 * 
 * This separation keeps the main entry simple and delegates 
 * functionality to OptionMenu.
 */
public class ATM {
    public static void main(String[] args) throws IOException {
        // Create an OptionMenu instance to handle ATM interactions
        OptionMenu optionMenu = new OptionMenu();
        
        // Start the ATM interface
        optionMenu.mainMenu();
    }
}