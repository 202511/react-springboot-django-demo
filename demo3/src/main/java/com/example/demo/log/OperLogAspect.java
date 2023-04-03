package com.example.demo.log;

import com.example.demo.domain.Log;
import com.example.demo.domain.LoginUser;
import com.example.demo.service.LogService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OperLogAspect {
    @Autowired
    LogService logService;
    @Autowired
    LoginUser loginUser;
    @Before("@annotation(t)")
    public void  doBefore(OperLog t)
    {
        String value = t.value();
        //下面这一步 代替将记录存入数据库
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser =(LoginUser) authentication.getPrincipal();
        System.out.println(loginUser);
        Log log = new Log();
        log.setOperation(value);
        boolean b = logService.addLog(log);
    }



}
