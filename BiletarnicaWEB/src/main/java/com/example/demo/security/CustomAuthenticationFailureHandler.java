package com.example.demo.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException ex) throws IOException, ServletException {
    	req.getSession().setAttribute("msgUNFE", "Neispravno korisničko ime ili lozinka");
        resp.sendRedirect("/Biletarnica/login");
    }
}
