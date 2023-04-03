package com.example.demo.domain;

import org.springframework.stereotype.Component;

import java.sql.Blob;
@Component
public class Audios {
    int id ;
    byte[] audio;
    String china;
    String english;
    int support;
    int collect;
    String words;

    public byte[] getAudio() {
        return audio;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "Audios{" +
                "id=" + id +
                ", audio=" + audio +
                ", english='" + english + '\'' +
                ", china='" + china + '\'' +
                ", support=" + support +
                ", collect=" + collect +
                '}';
    }
    public void setAudio(byte[] audio) {
        this.audio = audio;
    }


    public String getEnglish() {
        return english;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChina() {
        return china;
    }

    public void setChina(String china) {
        this.china = china;
    }

    public int getSupport() {
        return support;
    }

    public void setSupport(int support) {
        this.support = support;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }
}
