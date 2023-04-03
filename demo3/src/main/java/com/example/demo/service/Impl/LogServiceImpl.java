package com.example.demo.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.Log;
import com.example.demo.domain.LoginUser;
import com.example.demo.mapper.LogMapper;
import com.example.demo.mapper.LoginUserMapper;
import com.example.demo.service.LogService;
import com.example.demo.service.LoginUserService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
    @Override
    public boolean addLog(Log log) {
        boolean b = saveOrUpdate(log);
        return b;
    }

    @Override
    public boolean deleteAllLog() {
        QueryWrapper<Log> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*");
        boolean remove = remove(queryWrapper);
        return remove;
    }
}
