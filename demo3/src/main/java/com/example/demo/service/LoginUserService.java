package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.domain.LoginUser;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
@Service
public interface LoginUserService extends IService<LoginUser> {
    public LoginUser getByUserName(String  name );
    public LoginUser getByUserMailBox(String mailBox);
    public LoginUser getByUserPhoneNumber(String phoneNumber);
    public boolean  addUser(LoginUser loginUser);

}
