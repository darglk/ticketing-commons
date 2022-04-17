package com.darglk.ticketingcommons.config.security.filter;

import com.darglk.ticketingcommons.exception.CustomException;
import com.darglk.ticketingcommons.exception.ErrorResponse;
import com.darglk.ticketingcommons.exception.NotAuthorizedException;
import com.darglk.ticketingcommons.utils.JSONUtils;
import com.darglk.ticketingcommons.utils.JwtUtils;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = null;
        response.setContentType("application/json");

        try {
             authentication = getAuthentication(request);
        } catch (CustomException error) {
            response.setStatus(error.getStatusCode());
            response.getWriter().write(JSONUtils.toJson(Map.of("errors", error.serializeErrors())));
            return;
        } catch (Exception exception) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter()
                    .write(JSONUtils.toJson(
                            Map.of("errors",
                                    List.of(new ErrorResponse("Something went wrong", null)))));
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            try {
                Jws<Claims> parsedToken = JwtUtils.parseToken(token);

                String username = parsedToken.getBody().getSubject();

                if (!username.isEmpty()) {
                    return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                }
            } catch (ExpiredJwtException exception) {
                log.warn("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
            } catch (UnsupportedJwtException exception) {
                log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
            } catch (MalformedJwtException exception) {
                log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
            } catch (SignatureException exception) {
                log.warn("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
            } catch (IllegalArgumentException exception) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
            }
        }
        throw new NotAuthorizedException();
    }
}
