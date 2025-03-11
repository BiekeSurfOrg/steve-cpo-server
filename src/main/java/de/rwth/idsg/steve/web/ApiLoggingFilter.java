package de.rwth.idsg.steve.web.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ApiLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        log.info("🚀 API CALL - Method: {}, Path: {}, Query: {}",
                req.getMethod(), req.getRequestURI(), req.getQueryString());

        // Log headers
        req.getHeaderNames().asIterator()
                .forEachRemaining(header -> log.info("🔹 Header: {} = {}", header, req.getHeader(header)));

        chain.doFilter(request, response);
    }
}
