package com.example.demo.security.permission;

import com.example.demo.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("ss")
public class PermissionService {
    public boolean hasPermission()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser =(LoginUser) authentication.getPrincipal();
        System.out.println(loginUser);
        if(loginUser==null )  return false;
        else return true;
    }

}
