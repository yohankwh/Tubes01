package com.example.tubes01.models;

public class Food {
    protected String name;
    protected String detail;
    protected String tag;

    public Food(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetail(String detail) { this.detail = detail;}

    public String getDetail() { return this.detail;}

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
