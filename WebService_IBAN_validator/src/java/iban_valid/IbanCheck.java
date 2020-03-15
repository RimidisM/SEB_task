package iban_valid;

import java.math.BigInteger;
import java.util.HashMap;

/**
 *
 * @author rimid
 */
public class IbanCheck {

    public static final BigInteger MOD97 = new BigInteger("97");

    /**
     * Test IBAN according rules from https://en.wikipedia.org/wiki/International_Bank_Account_Number
     * @param ibanNumber String representing IBAN
     * @return True if IBAN is valid and False if not valid
     */
    public static boolean ibanTest(String ibanNumber) {
        String newIbanNumber = ibanNumber.trim();
        String firstChars = getTwoFirstCharacters(newIbanNumber);
        Integer ibanLenght = formIbanData().get(firstChars);

        // 1. Check that the total IBAN length is correct as per the country. If not, the IBAN is invalid
        try {
            if (newIbanNumber.length() != ibanLenght) {
                return false;
            }
        } catch (NullPointerException ex) {
            return false;
        }

        // 2. Move the four initial characters to the end of the string
        newIbanNumber = newIbanNumber.substring(4) + newIbanNumber.substring(0, 4);

        // 3. Replace each letter in the string with two digits, thereby expanding the string, where A = 10, B = 11, ..., Z = 35
        StringBuilder numericAccountNumber = new StringBuilder();
        for (int i = 0; i < newIbanNumber.length(); i++) {
            numericAccountNumber.append(Character.getNumericValue(newIbanNumber.charAt(i)));
        }

        // 4. Interpret the string as a decimal integer and compute the remainder of that number on division by 97
        BigInteger ibanComputeRemainder = new BigInteger(numericAccountNumber.toString());
        return ibanComputeRemainder.mod(MOD97).intValue() == 1;
    }

    /**
     * Extracts two first characters from IBAN
     * @param ibanNumber String representing IBAN
     * @return The two first characters
     */
    private static String getTwoFirstCharacters(String ibanNumber) {
        String firstCharacters = null;
        try {
            firstCharacters = ibanNumber.substring(0, 2);
        } catch (StringIndexOutOfBoundsException es) {
            //Ignored
        }
        return firstCharacters;
    }

    /**
     * Forms object from IBAN Country code and IBAN length
     * @return The object which connects IBAN country code and IBAN length
     */
    private static HashMap<String, Integer> formIbanData() {
        HashMap<String, Integer> ibanForm;
        ibanForm = new HashMap<>();
        IbanData ibanProperties = new IbanData();
        Integer[] ibanCharactersAmount = ibanProperties.getIBAN_LENGTH();
        String[] ibanFirstTwoCharacters = ibanProperties.getCOUNTRY_NAME();

        for (int i = 0; i < ibanFirstTwoCharacters.length; i++) {
            ibanForm.put(ibanFirstTwoCharacters[i], ibanCharactersAmount[i]);
        }
        return ibanForm;
    }
}
