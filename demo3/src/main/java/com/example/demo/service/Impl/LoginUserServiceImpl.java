package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.LoginUser;
import com.example.demo.mapper.LoginUserMapper;
import com.example.demo.mapper.LoginUserMapper;
import com.example.demo.service.LoginUserService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginUserServiceImpl extends ServiceImpl<LoginUserMapper, LoginUser> implements  LoginUserService{
    @Override
    public LoginUser getByUserName(String name) {
        QueryWrapper<LoginUser>  queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("user_name", name);
        LoginUser one = getOne(queryWrapper, false);
        return one;
    }

    @Override
    public LoginUser getByUserMailBox(String mailBox) {
        QueryWrapper<LoginUser>  queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("mail_box", mailBox);
        LoginUser one = getOne(queryWrapper, false);
        return one;
    }

    @Override
    public LoginUser getByUserPhoneNumber(String phoneNumber) {
        QueryWrapper<LoginUser>  queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("phone_number", phoneNumber);
        LoginUser one = getOne(queryWrapper, false);
        return one;
    }

    @Override
    public boolean addUser(LoginUser loginUser) {
        boolean b = saveOrUpdate(loginUser);
        return b;
    }


}
