
package lt.bit.rest;

import iban_valid.Client;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
@Path("ibans")
public class IbanValidatorREST {

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public List<String> getResults(List<String> ibans)
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {

        List<String> ibanList = new ArrayList<>();
        try {
            ibanList = Client.verifyIbanList(ibans);
        } catch (NullPointerException ex) {
            ibanList.add("Try again!!!");
        }
        return ibanList;
    }
}
