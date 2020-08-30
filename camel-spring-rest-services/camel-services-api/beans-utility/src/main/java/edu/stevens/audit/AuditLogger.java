package edu.stevens.audit;

import org.apache.camel.Exchange;

public class AuditLogger {

    public void headerChecker(Exchange exchange) {
        System.out.println("Counter | {}" + exchange.getIn().getHeader(Exchange.INTERCEPTED_ENDPOINT));
    }

    public void responseChecker(Exchange exchange) {
        System.out.println("Counter | responseChecker");
    }
}
