package com.example.tubes01.models;

public class Food {
    protected String name;
    protected String tag;
    protected String bahan;
    protected String langkah;
    protected String resto;

    public Food(String name,String tag,String bahan, String langkah, String resto){
        this.name = name;
        this.tag = tag;
        this.bahan = bahan;
        this.langkah = langkah;
        this.resto = resto;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBahan(){return this.bahan;}

    public void setBahan(String bahan) { this.bahan = bahan;}

    public String getLangkah(){return this.langkah;}

    public void setLangkah(String langkah){this.langkah = langkah;}

    public String getResto(){return this.resto;}

    public void setResto(String resto){this.resto=resto;}
}
