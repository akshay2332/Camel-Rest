package edu.stevens.web.session.service;

import edu.stevens.web.session.user.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {


    @Override
    public UserInfo loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("String s | " + s);
        //loginProducerTemplate.sendBody("{user:user,password:password}");
        return new UserInfo();
    }
}
