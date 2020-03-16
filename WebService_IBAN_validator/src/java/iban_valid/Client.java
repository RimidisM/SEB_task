package iban_valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rimid
 * @readFile reads data from file
 */
public class Client {

    /**
     * Controls IBAN list verification
     *
     * @param list This is IBAN list
     * @return Verified IBAN list
     * @throws IOException
     */
    public static List<String> verifyIbanList(List<String> list) throws IOException {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (IbanCheck.ibanTest(list.get(i))) {
                result.add(list.get(i) + ";true");
            } else {
                result.add(list.get(i) + ";false");
            }
        }
        return result;
    }
}
