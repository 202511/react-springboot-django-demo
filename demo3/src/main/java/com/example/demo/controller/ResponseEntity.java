package com.example.demo.controller;

import org.springframework.stereotype.Component;

@Component
public class ResponseEntity {
    String mess;

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
