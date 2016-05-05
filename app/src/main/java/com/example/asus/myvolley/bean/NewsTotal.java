package com.example.asus.myvolley.bean;

import java.util.List;

/**
 * Created by Asus on 2016/5/5.
 */
public class NewsTotal {
    private List<New> result;

    @Override
    public String toString() {
        return "NewsTotal{" +
                "result=" + result +
                '}';
    }

    public List<New> getResult() {
        return result;
    }

    public void setResult(List<New> result) {
        this.result = result;
    }
}
