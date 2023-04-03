package com.example.demo.websocket;

import com.example.demo.domain.LoginUser;
import com.example.demo.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ChatController {

    private AtomicInteger idProducer = new AtomicInteger();
    @Autowired
    LoginUserService loginUserService;
    @PreAuthorize("@ss.hasPermission()")
    @RequestMapping("/getName")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser =(LoginUser) authentication.getPrincipal();
        return loginUser.getUserName();

    }
}

