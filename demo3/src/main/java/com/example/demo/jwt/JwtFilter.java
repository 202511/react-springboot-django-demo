package com.example.demo.jwt;

import com.example.demo.cache.RedisCache;
import com.example.demo.domain.LoginUser;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    private  String tokenPrefix="Bearer ";
    @Value("${token.headName}")
    private String headName;
    @Autowired
    private RedisCache redisCache;
    @CrossOrigin
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!request.getMethod().equals("OPTIONS")) {
            String header = request.getHeader(headName);
            System.out.println(header);
            if (header != null && header.startsWith(tokenPrefix) == true) {
                header = header.replace(tokenPrefix, "");
                Claims valid = jwtService.isValid(header);
                String key = (String) valid.get("userId");
                LoginUser loginUser = redisCache.getCacheObject(key);
                if (loginUser != null) {
                    System.out.println(loginUser);
//            接下来只要把这个 对象注入到请求的上下文中， 我们就完成了。
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
