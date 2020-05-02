package edu.stevens.mobile.provider;

import edu.stevens.mobile.session.service.CustomUserDetailsService;
import edu.stevens.mobile.session.user.UserInfo;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MobileLoginAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MobileLoginAuthenticationProvider.class);


    @EndpointInject(value = "direct:jwt-create-token")
    private ProducerTemplate tokenProducerTemplate;

    private CustomUserDetailsService userDetailsService;

    public MobileLoginAuthenticationProvider(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        try {
            LOGGER.debug("Enterd hered");
            LOGGER.debug("Username | {}", authentication.getName());

            UserInfo userInfo = userDetailsService.loadUserByUsername(authentication.getName());
            LOGGER.debug("UserInfo !!! | {}",userInfo);
            if (userInfo.getPassword().equals(authentication.getCredentials().toString())) {
                String token = tokenProducerTemplate.requestBody(userInfo, String.class);
                userInfo.setAccessToken(token);
                return new UsernamePasswordAuthenticationToken(userInfo, userInfo.getPassword(),
                        userInfo.getAuthorities());
            } else {
                throw new BadCredentialsException("Incorrect Password");
            }
        } catch (UsernameNotFoundException notFoundException) {

            throw notFoundException;

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.debug("Login Failure !!!");
            throw new BadCredentialsException("Could not generate token.");
        }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
