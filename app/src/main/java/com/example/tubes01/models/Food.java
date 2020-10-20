package com.example.tubes01.models;

public class Food {
    protected String name;
    protected String tag;
    protected String bahan;

    public Food(String name,String tag,String bahan){
        this.name = name;
        this.tag = tag;
        this.bahan = bahan;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setBahan(String bahan) { this.bahan = bahan;}

    public String getBahan(){return this.bahan;}
}
