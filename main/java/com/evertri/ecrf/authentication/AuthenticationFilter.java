package com.evertri.ecrf.authentication;

import com.evertri.ecrf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
@Profile("test")
public class AuthenticationFilter extends GenericFilterBean {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Check if the request is a preflight (OPTIONS) request and let it pass through the filter chain
        if (request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
            filterChain.doFilter(request, response);
            return;
        }

        String macAddress = request.getHeader("macAddress");
        Long studyId = Long.parseLong(request.getParameter("studyId"));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (authenticationService.isAuthorized(user.getUsername(), macAddress, studyId)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }
}
