package edu.stevens.mobile.session.service;

import edu.stevens.mobile.session.user.User;
import edu.stevens.mobile.session.user.UserInfo;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomMobileUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomMobileUserDetailsService.class);

    @EndpointInject(value = "direct:login")
    private ProducerTemplate loginProducerTemplate;

    @Override
    public UserInfo loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = new User(userName, null);
        UserInfo userInfo = null;
        try {
            userInfo = loginProducerTemplate.requestBody(user, UserInfo.class);
        } catch (Exception e) {
            LOGGER.debug("UserName | {} | Not found in DB", userName);
            throw new UsernameNotFoundException("Username " + userName + " not found.");
        }

        return userInfo;
    }
}
