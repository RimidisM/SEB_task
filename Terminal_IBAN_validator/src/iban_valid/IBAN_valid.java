package iban_valid;

import java.io.IOException;

/**
 *
 * @author rimid
 */
public class IBAN_valid {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.println("International Bank Account Number (IBAN) validation");
        System.out.println("");

        Client.getMenu();

        Client.getCommands();
    }

}
