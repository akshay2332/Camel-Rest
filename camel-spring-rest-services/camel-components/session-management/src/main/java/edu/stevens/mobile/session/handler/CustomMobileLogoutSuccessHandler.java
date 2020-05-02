package edu.stevens.mobile.session.handler;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomMobileLogoutSuccessHandler implements LogoutSuccessHandler {

    @EndpointInject(value = "direct:logout")
    private ProducerTemplate logoutProducerTemplate;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
    }
}
