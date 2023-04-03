package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.domain.Log;
import com.example.demo.domain.LoginUser;
import org.springframework.stereotype.Service;

@Service
public interface LogService extends IService<Log> {
       public boolean addLog(Log log);
       public boolean deleteAllLog();

}
