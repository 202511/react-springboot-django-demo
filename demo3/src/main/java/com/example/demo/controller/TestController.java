package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.cache.RedisCache;
import com.example.demo.domain.Audios;
import com.example.demo.hutool.CodeEntity;
import com.example.demo.hutool.CodeService;
import com.example.demo.jwt.JwtService;
import com.example.demo.log.OperLog;
import com.example.demo.pretrainmodel.KeyWordsGenerateTextService;
import com.example.demo.pretrainmodel.TranslateService;
import com.example.demo.ratelimiter.RateLimiter;
import com.example.demo.repeatsubmit.RepeatSubmit;
import com.example.demo.resolvejson.GenerateWordsService;
import com.example.demo.service.AudioService;
import com.example.demo.texttovoice.TextToVoiceProperties;
import com.example.demo.texttovoice.TextToVoiceService;
import com.example.demo.domain.LoginUser;
import com.example.demo.service.Impl.LoginUserServiceImpl;
import com.example.demo.service.LoginUserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@RestController
public class TestController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    TextToVoiceService textToVoiceService;
    @Autowired
    KeyWordsGenerateTextService keyWordsGenerateTextService;
    @Autowired
    GenerateWordsService generateWordsService;
    @Autowired
    TranslateService translateService;
    @Autowired
    RedisCache redisCache;
    @Autowired
    LoginUserService loginUserService;
    @Autowired
    AudioService audioService;
    @Autowired
    CodeService codeService;
    private  String tokenPrefix="Bearer ";
    @Value("${token.headName}")
    private String headName;
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    CompletableFuture<String> stringCompletableFuture;
    CompletableFuture<byte[]> voidCompletableFuture;
    @GetMapping("/getCode")
    public CodeEntity getCode()
    {
        CodeEntity newCode = codeService.getNewCode();
        return newCode;
    }
    @GetMapping("/addA")
    public String addA()
    {
        Audios audios = new Audios();
        audios.setWords("sadasda");
        audioService.add(audios);
        return "添加成功";
    }













    @RepeatSubmit(name= "take")
    @OperLog(value = "take")
    @RateLimiter(time = 10 , count =  2)
    @PreAuthorize("@ss.hasPermission()")
    @GetMapping("/addAudio")
    public String  addAudio() throws IOException, ExecutionException, InterruptedException {
        String keyWords = generateWordsService.getKeyWords();

        String text = keyWordsGenerateTextService.getText(keyWords);
        Audios audios = new Audios();
        audios.setWords(keyWords);
        audios.setEnglish(text);
        long l = System.currentTimeMillis();
        voidCompletableFuture = CompletableFuture.supplyAsync(() ->
        {
            try {
               return  textToVoiceService.get(text);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
        stringCompletableFuture = CompletableFuture.supplyAsync(() ->
        {

            try {
                return  translateService.getText();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null ;
        }, executorService);
        long l1 = System.currentTimeMillis() - l;
        String s = stringCompletableFuture.get();
        byte[] bytes = voidCompletableFuture.get();
        audios.setAudio(bytes);
        audios.setChina(s);
        audioService.add(audios);
        System.out.println(l1);
        return "添加成功";
    }
    @GetMapping("/getAudioById/{id}")
    public Audios getAudioById(@PathVariable("id") int id)
    {
        Audios audios = audioService.selectById(id);
        return audios;
    }







    @RepeatSubmit(name= "take")
    @OperLog(value = "take")
    @RateLimiter(time = 10 , count =  2)
    @PreAuthorize("@ss.hasPermission()")
    @GetMapping("/take")
    public String take()
    {
        return "成功";
    }



   @PostMapping("/signup")
    public String signUp(LoginUser loginUser)
   {
       boolean o=loginUserService.addUser(loginUser);
       return o==true ? "成功" : "失败";
   }
   @GetMapping("/logout")
   public String logout(HttpServletRequest request)
   {
       String header = request.getHeader(headName);
       boolean b=false;
       if(header!=null &&header.startsWith(tokenPrefix)==true)
       {
           header=header.replace(tokenPrefix,"");
           Claims valid = jwtService.isValid(header);
           String key = (String)valid.get("userId");
           b = redisCache.deleteCacheObject(key);
//            接下来只要把这个 对象注入到请求的上下文中， 我们就完成了。
       }

       return  b==true ? "退出登录成功" : "请重新尝试";
   }
   @PostMapping("/login")
    public ResponseEntity login( @RequestBody JSONObject name) throws ExecutionException, InterruptedException {
        ResponseEntity responseEntity = new ResponseEntity();
        String string=name.getString("string");
        String password=name.getString("password");
        String code=name.getString("code");
        String u=name.getString("u");
        String uuid= String.valueOf(UUID.randomUUID());
        System.out.println(name);
       long l = System.currentTimeMillis();
        System.out.println(string+password+u);
       LoginUser loginUser=null;
       responseEntity.setMess("验证码不正确");
       if(codeService.isValid(u, code)!=true) return responseEntity;
       CompletableFuture<LoginUser> loginUserCompletableFuture = CompletableFuture.supplyAsync(() -> {
           return loginUserService.getByUserPhoneNumber(string);
       }, executorService);
       CompletableFuture<LoginUser> loginUserCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
           return loginUserService.getByUserMailBox(string);
       }, executorService);
       CompletableFuture<LoginUser> loginUserCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
           return loginUserService.getByUserName(string);
       }, executorService);
       if(loginUserCompletableFuture.get()!=null)
       {
           loginUser=loginUserCompletableFuture.get();
       }
       if(loginUserCompletableFuture1.get()!=null)
       {
           loginUser=loginUserCompletableFuture1.get();
       }
       if(loginUserCompletableFuture2.get()!=null)
       {
           loginUser=loginUserCompletableFuture2.get();
       }
       long l1 = System.currentTimeMillis() - l;
       System.out.println(l1);
        responseEntity.setMess("登录失败");
       if (loginUser==null) return responseEntity;
       redisCache.setCacheObject(uuid, loginUser);
       HashMap<String, Object> map = new HashMap<>();
       map.put("userId", uuid);
       String jwt = jwtService.getJwt(map);
       System.out.println(jwt);
       String token=tokenPrefix+jwt;
        responseEntity.setMess(token);
       return responseEntity;
   }



}
