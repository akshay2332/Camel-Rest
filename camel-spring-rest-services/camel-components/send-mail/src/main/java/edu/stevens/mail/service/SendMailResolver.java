package edu.stevens.mail.service;

import edu.stevens.constants.ApplicationConstants;
import edu.stevens.decryption.Decryption;
import edu.stevens.encryption.Encryption;
import edu.stevens.keys.ApplicationKeys;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SendMailResolver {

    private final Logger LOGGER = LoggerFactory.getLogger(SendMailResolver.class);

    public void resolveMailDetails(Exchange exchange) {
        String emailId = exchange.getIn().getBody(String.class);
        String encryptedEmailId = Encryption.encrypt(emailId, ApplicationKeys.EMAIL_PRIVATE_KEY,
                ApplicationKeys.EMAIL_PUBLIC_KEY);

        String verificationURL = String.format(ApplicationConstants.EMAIL_ID_VERIFICATION_URL, encryptedEmailId);
        exchange.setProperty(ApplicationConstants.EMAIL_OBJECT, emailId);
        exchange.getIn().setBody(verificationURL);
        LOGGER.debug("EmailId | {} | Encrypted Email | {} | URL | {}", emailId, encryptedEmailId, verificationURL);
    }

    public void decryptEmailRequest(Exchange exchange) {
        Map<String, String> queryParams = exchange.getIn().getHeader(Exchange.HTTP_QUERY, Map.class);
        LOGGER.debug("Query Params | {} ", exchange.getIn().getHeader(Exchange.HTTP_QUERY, String.class));
        LOGGER.debug("id | {} ", exchange.getIn().getHeader("id", String.class));
        LOGGER.debug("headers | {} ", exchange.getIn().getHeaders());


        String decryptedEmailId = Decryption.decrypt(exchange.getIn().getHeader("id", String.class), ApplicationKeys.EMAIL_PRIVATE_KEY,
                ApplicationKeys.EMAIL_PUBLIC_KEY);
        LOGGER.debug("Query Params | {} ", exchange.getIn().getHeader(Exchange.HTTP_QUERY, String.class));
        LOGGER.debug("EmailId | {} ", decryptedEmailId);
        exchange.getIn().setBody(decryptedEmailId);
    }
}
