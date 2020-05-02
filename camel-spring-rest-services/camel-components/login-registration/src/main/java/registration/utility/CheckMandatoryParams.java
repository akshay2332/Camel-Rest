package registration.utility;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

public class CheckMandatoryParams {

    public void checkRegistrationParameters(Exchange exchange) {
        Message inMessage = exchange.getIn();
    }
}
