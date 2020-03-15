package iban_valid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
     * Controls IBAN verification from file
     * @param filePath This is file path with IBAN list
     * @return Verified IBAN list
     * @throws IOException 
     */
    public static List<String> verifyIbanFromFile(String filePath) throws IOException {
        List<String> result = new ArrayList<>();

        try {
            List<String> list = readFile(filePath);
            for (int i = 0; i < list.size(); i++) {
                if (IbanCheck.ibanTest(list.get(i))) {
                    result.add(list.get(i) + ";true");
                } else {
                    result.add(list.get(i) + ";false");
                }
            }
        } catch (IOException e) {
        }

        return result;
    }

    /**
     * Reads information from file
     * @param filePath This is full file path which we want to read
     * @return List of symbols from file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private static List<String> readFile(String filePath) throws FileNotFoundException, IOException {

        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException | NullPointerException | NumberFormatException e) {
            //Ignored
        }
        return result;
    }

}
