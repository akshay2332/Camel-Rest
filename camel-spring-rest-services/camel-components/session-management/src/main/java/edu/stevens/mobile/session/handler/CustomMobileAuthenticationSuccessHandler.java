package edu.stevens.mobile.session.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.stevens.mobile.session.user.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomMobileAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomMobileAuthenticationSuccessHandler.class);


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        LOGGER.debug("Success 1");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        LOGGER.debug("Success 2");
        LOGGER.debug(authentication.getDetails().toString());
        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Authentication Success");
        data.put("token", ((UserInfo) authentication.getPrincipal()).getAccessToken());

        httpServletResponse.getOutputStream()
                .println(objectMapper.writeValueAsString(data));
    }
}
