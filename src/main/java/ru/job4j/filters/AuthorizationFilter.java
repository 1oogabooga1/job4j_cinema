package ru.job4j.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class AuthorizationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        var uri = request.getRequestURI();
        var userLoggedIn = request.getSession().getAttribute("user") != null;
        if (!userLoggedIn && isNeverPermitted(uri)) {
            var loginPageUrl = request.getContextPath() + "/users/login";
            response.sendRedirect(loginPageUrl);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isNeverPermitted(String uri) {
        return uri.startsWith("/tickets/buy");
    }
}
