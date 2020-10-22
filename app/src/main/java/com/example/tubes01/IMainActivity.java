package com.example.tubes01;

import com.example.tubes01.models.Food;

import org.json.JSONArray;

import java.util.List;

public interface IMainActivity {
    public void updateList(List<Food> foods);
    public void resetAddForm();
    public void saveToFile(String content);
    public String readFile();
}
