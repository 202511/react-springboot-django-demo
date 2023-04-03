package com.example.demo.hutool;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class CodeService {
    HashMap<String ,String> map =new HashMap<>();
    String key="code";
    public boolean isValid(String uuid, String code)
    {
        System.out.println(map.containsKey(uuid));
        System.out.println(code);
        if(map.containsKey(uuid))
        {
            System.out.println(map.get(uuid));
            if(map.get(uuid).equals(code))
            return true;
        }
        return false;
    }
    public CodeEntity getNewCode()
    {
        CodeEntity codeEntity = new CodeEntity();
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        String s = uuid.toString();
        codeEntity.setUuid(s);
        //利用hutool工具生成验证码图片资源
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 5);
        //生成验证码字符
        String code = circleCaptcha.getCode();
        System.out.println(code);

        codeEntity.setCode(circleCaptcha.getImageBytes());
        map.put(s, code);
        return codeEntity;
    }
}
