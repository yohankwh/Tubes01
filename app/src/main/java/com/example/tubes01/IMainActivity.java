package com.example.tubes01;

import com.example.tubes01.models.Food;

import java.util.List;

public interface IMainActivity {
    public void updateList(List<Food> foods);
    public void resetAddForm();
}
