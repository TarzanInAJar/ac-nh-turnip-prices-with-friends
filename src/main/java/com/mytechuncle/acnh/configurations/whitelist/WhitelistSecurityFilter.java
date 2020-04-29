package com.mytechuncle.acnh.configurations.whitelist;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WhitelistSecurityFilter extends OncePerRequestFilter {
    List<String> whitelist;

    public WhitelistSecurityFilter(List<String> whitelist) {
        this.whitelist = whitelist;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            OAuth2User principal = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal.getAttribute("email") == null || whitelist.stream().noneMatch(w -> w.equalsIgnoreCase(principal.getAttribute("email")))){
                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Not on the list, bro");
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
