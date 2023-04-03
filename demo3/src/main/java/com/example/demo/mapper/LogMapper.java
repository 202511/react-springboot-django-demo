package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper  extends BaseMapper<Log> {
}
