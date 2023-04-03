package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.LoginUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LoginUserMapper extends BaseMapper<LoginUser> {

}
