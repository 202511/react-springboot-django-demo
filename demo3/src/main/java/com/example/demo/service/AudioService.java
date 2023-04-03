package com.example.demo.service;


import com.example.demo.domain.Audios;
import org.springframework.stereotype.Service;

@Service
public interface AudioService {
         public boolean add(Audios audios);
         public Audios selectById(int id );
}
