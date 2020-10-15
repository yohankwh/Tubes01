package com.example.tubes01;

import com.example.tubes01.models.Food;

import java.util.LinkedList;
import java.util.List;

public class MainPresenter {
    protected List<Food> foods;
    protected IMainActivity ui;

    public MainPresenter(IMainActivity activity){
        this.ui = activity;
        this.foods = new LinkedList<Food>();
    }

    public void addList(String title){
        this.foods.add(new Food(title));
        this.ui.updateList(this.foods);
        this.ui.resetAddForm();
    }
}
