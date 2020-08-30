package registration.helper;

import edu.stevens.response.Response;
import org.apache.camel.Exchange;
import org.apache.camel.util.json.JsonObject;
import registration.model.RegistrationResp;

public class ResponseGenerator {

    public void generateRegistrationSuccessResponse(Exchange exchange) {
        exchange.getIn().setBody(null);
        Response response = new Response();
        response.setReturnCode(0);
        response.setErrorCode(0);
        response.setMessage("Successfully created the user");
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("response",response);
        exchange.getIn().setBody(jsonObject);

        System.out.println("Done");
    }
}
