package com.example.demo.hutool;

import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class CodeEntity {
    byte[] code;
    String uuid;

    @Override
    public String toString() {
        return "CodeEntity{" +
                "code=" + Arrays.toString(code) +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
