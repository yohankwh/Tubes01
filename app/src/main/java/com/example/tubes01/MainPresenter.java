package com.example.tubes01;

import android.util.Log;

import com.example.tubes01.models.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainPresenter {
    protected List<Food> foods;
    protected IMainActivity ui;

    public MainPresenter(IMainActivity activity){
        this.ui = activity;
        this.foods = new LinkedList<Food>();
    }

    public void addList(String title, String tag, String bahan){
        this.foods.add(new Food(title,tag,bahan));

//        JSONArray jsonArray = new JSONArray();
//        for(Food food : this.foods) {
//            JSONObject food_item = new JSONObject();
//            food_item.put("name", food.getName());
//            food_item.put("bahan", food.getBahan());
//            food_item.put("tag", food.getTag());
//
//            Log.d("FOOD_ITEM: ",food_item.toString());
//            jsonArray.put(food_item);
//            Log.d("FOOD_ARRAY: ",jsonArray.toString());
//        }

        this.ui.updateList(this.foods);
        this.ui.resetAddForm();
    }

    public void loadData(){
        this.foods.addAll(Arrays.asList(MockFood.foodObjectArr));
        Log.d("SIZE: ",this.foods.size()+"");

        this.ui.updateList(this.foods);
    }

    public void deleteList(int position){
        this.foods.remove(position);
        this.ui.updateList(this.foods);
    }

    public String getRandomFood(){
        Random rand = new Random();
        int n = rand.nextInt(this.foods.size());

        return this.foods.get(n).getName();
    }
}
