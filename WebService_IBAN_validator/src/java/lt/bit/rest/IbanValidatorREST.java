/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.rest;

import iban_valid.Client;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author rimid
 */
@Path("result")
public class IbanValidatorREST {

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public List<String> getResults(String path)
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        String result;
        List<String> ibanList = new ArrayList<>();
        File f;

        if (path.equals("\\")) {
            ibanList.add("Insert valid informatio!!!");
            return ibanList;
        } else {
            try {
                f = new File(path);
                if (f.exists()) {
                    Method verifyIban = Client.class.getMethod("verifyIbanFromFile", String.class);
                    Client clientInstance = new Client();
                    ibanList = (List<String>) verifyIban.invoke(clientInstance, path);
                } else {
                    throw new FileNotFoundException();
                }
            } catch (NullPointerException | FileNotFoundException ex) {
                ibanList.add("Insert valid informatio!!!");
            }
        }

        return ibanList;
    }
}
