package edu.stevens.customexceptions.resolver;

import edu.stevens.customexceptions.CustomException;
import edu.stevens.customexceptions.model.ExceptionResponse;
import org.apache.camel.Exchange;

public class ExceptionResolver {

    public void resolveException(Exchange exchange) {
        CustomException exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, CustomException.class);
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getResponse());
        exchange.getIn().setBody(exceptionResponse);
    }
}
