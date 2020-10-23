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

    public void addList(String title, String tag, String bahan) throws JSONException {
        this.foods.add(new Food(title,tag,bahan));

        this.updateStorageJSON();

        this.ui.updateList(this.foods);
        this.ui.resetAddForm();
    }

    public void loadData() throws JSONException {
        String content = this.ui.readFile();

        JSONArray ja = new JSONArray(content);
        Food[] foods = new Food[ja.length()];
        for(int i=0;i<ja.length();i++){
            JSONObject food = ja.getJSONObject(i);
            food = food.getJSONObject("food");
            foods[i] = new Food(food.getString("name"),food.getString("tag"),food.getString("bahan"));
        }

        this.foods.addAll(Arrays.asList(foods));
        Log.d("SIZE: ",this.foods.size()+"");

        this.ui.updateList(this.foods);
    }

    public void deleteList(int position) throws JSONException {
        this.foods.remove(position);

        this.updateStorageJSON();

        this.ui.updateList(this.foods);
    }

    public String getRandomFood(){
        Random rand = new Random();
        int n = rand.nextInt(this.foods.size());

        return this.foods.get(n).getName();
    }

    public void updateStorageJSON() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for(Food food : this.foods) {
            JSONObject food_details = new JSONObject();
            food_details.put("name", food.getName());
            food_details.put("bahan", food.getBahan());
            food_details.put("tag", food.getTag());

            JSONObject food_item = new JSONObject();
            food_item.put("food",food_details);
            jsonArray.put(food_item);

//            Log.d("FOOD_ITEM: ",food_item.toString());
//            Log.d("FOOD_ARRAY: ",jsonArray.toString());
        }

        this.ui.saveToFile(jsonArray.toString());
    }

}
