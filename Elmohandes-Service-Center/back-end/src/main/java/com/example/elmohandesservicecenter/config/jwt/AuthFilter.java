package com.example.elmohandesservicecenter.config.jwt;

import com.example.elmohandesservicecenter.model.managermodel.Manager;
import com.example.elmohandesservicecenter.sevice.jwt.ManagerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private TokenHandler tokenHandler;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // get  the url from the request
        String path = request.getRequestURI();
        if (path.contains("login") || path.contains("create-manager")) {
            return true;
        }

        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/upload/") ) {
            filterChain.doFilter(request, response);
            return;
        }


        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            response.reset();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        token = token.substring(7);
        if (!tokenHandler.validateToken(token)) {
            response.reset();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            Manager manager = managerService.checkManagerExistByToken(token);
            if (manager == null) {
                response.reset();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }


            //  here  we need to tell the second filter which is spring  security filter that   the user signrd in

            List<GrantedAuthority> grantedAuthorities = manager.getRols().stream().map(rols -> new SimpleGrantedAuthority(rols.getCode())).collect(Collectors.toList());

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(manager, null, grantedAuthorities);


            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            filterChain.doFilter(request, response);

        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
    }
}
