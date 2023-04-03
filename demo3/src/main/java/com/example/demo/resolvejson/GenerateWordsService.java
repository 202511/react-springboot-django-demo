package com.example.demo.resolvejson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class GenerateWordsService {
    HashMap<Integer, Integer> hashMap = new HashMap<>();
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static  String getWords() throws IOException {
        String jsonStr="";
        File file=new File("C:\\Users\\86136\\Desktop\\demo\\src\\main\\java\\com\\example\\demo\\resolvejson\\CET6luan_1.json");
        FileReader fileReader= new FileReader(file);
        Reader reader = new InputStreamReader(new FileInputStream(file),"Utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        jsonStr = sb.toString();
        return jsonStr;
    }
   public String getKeyWords() throws IOException, ExecutionException, InterruptedException {
       long l = System.currentTimeMillis();
       CompletableFuture<String[]> completableFuture = CompletableFuture.supplyAsync(() ->
       {
           try {
               return getWords().split("(\n)");
           } catch (IOException e) {
               e.printStackTrace();
           }
           return null;
       }, executorService);
       String[] split;

           Random random = new Random(1228);
           Integer i1 = -1;
           for (int i = 1; i <= 10; i++) {
               i1 = (int) (Math.random() * (1228 - 1) + 1);
               if (hashMap.containsKey(i1)) {
                   i--;
                   continue;
               }
               hashMap.put(i1, i1);
           }
       StringBuffer t=new StringBuffer();
       split = completableFuture.get();
       for (Integer integer : hashMap.keySet()) {
            JSONObject jsonObject = JSONObject.parseObject(split[integer]);
            String headWord = jsonObject.get("headWord").toString();
            t.append(headWord+" ");
       }
       long l1 = System.currentTimeMillis() - l;
       System.out.println(l1);
       return t.toString();

    }
}
