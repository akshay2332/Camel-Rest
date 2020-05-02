package edu.stevens.mobile.provider;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

public class CustomEntryPoint extends BasicAuthenticationEntryPoint {

    private String name;

    public CustomEntryPoint() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.setRealmName(name);
    }
}
