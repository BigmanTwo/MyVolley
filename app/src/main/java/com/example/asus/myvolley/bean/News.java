package com.example.asus.myvolley.bean;

import java.util.List;

/**
 * Created by Asus on 2016/5/5.
 */
public class News {
    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    private List<String> result;

    @Override
    public String toString() {
        return "News{" +
                "result=" + result +
                '}';
    }
}
