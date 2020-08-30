package edu.stevens.registration;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationRequestAnalyzer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationRequestAnalyzer.class);

    public void analyze(Exchange exchange) {

        System.out.println("Hello");

        String body = exchange.getMessage().getBody(String.class);
        System.out.println(body);

        exchange.getIn().setBody(body);
        System.out.println("bye");


    }

}
