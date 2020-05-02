package edu.stevens.audit;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

public class AuditLogger {

    private static int counter = 1;

    public void headerChecker(Exchange exchange) {

        Message inMessage = exchange.getIn();
        exchange.setIn(inMessage);
        System.out.println("Counter | {}" + counter++);
    }

}
