package edu.stevens.filter;

import edu.stevens.jwt.JwtTokenService;
import edu.stevens.mobile.session.service.CustomMobileUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtRequestValidator extends OncePerRequestFilter {


    private CustomMobileUserDetailsService customMobileUserDetailsService;

    private JwtTokenService jwtTokeService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtTokeService.extractUsername(jwt);
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.customMobileUserDetailsService.loadUserByUsername(username);

            if (jwtTokeService.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

        System.out.println(httpServletResponse.getStatus());
        System.out.println("BYE I AM DONE !!!!");
    }


    public void setJwtTokeService(JwtTokenService jwtTokeService) {
        this.jwtTokeService = jwtTokeService;
    }

    public void setCustomMobileUserDetailsService(CustomMobileUserDetailsService customMobileUserDetailsService) {
        this.customMobileUserDetailsService = customMobileUserDetailsService;
    }
}
