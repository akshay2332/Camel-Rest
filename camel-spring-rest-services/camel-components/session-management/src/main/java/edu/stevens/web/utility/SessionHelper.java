package edu.stevens.web.utility;

import edu.stevens.web.session.user.UserInfo;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionHelper {

    private final Logger LOGGER = LoggerFactory.getLogger(SessionHelper.class);

    public void createUserSessionObject(Exchange exchange) {

        UserInfo currentUser = exchange.getIn().getBody(UserInfo.class);
    }

    public void checkUserSessionObject(Exchange exchange) {


        LOGGER.info("Check Session");
    }
}


