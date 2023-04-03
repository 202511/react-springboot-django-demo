package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.Audios;
import com.example.demo.mapper.AudiosMapper;
import com.example.demo.service.AudioService;
import org.springframework.stereotype.Service;

@Service
public class AudioServiceImpl extends ServiceImpl<AudiosMapper, Audios> implements AudioService {

    @Override
    public boolean add(Audios audios) {
        boolean b = saveOrUpdate(audios);
        return b;
    }

    @Override
    public Audios selectById(int id) {
        System.out.println(id);
        Audios byId = getById(id);
        return byId ;
    }
}
