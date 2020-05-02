package edu.stevens.mobile.session.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomMobileAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomMobileAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException exception) throws IOException, ServletException {

        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Authentication Failure");

        httpServletResponse.getOutputStream()
                .println(objectMapper.writeValueAsString(data));

    }
}

