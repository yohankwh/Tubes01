package com.example.tubes01;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubes01.models.Food;

import java.util.List;

public class FoodListAdapter extends BaseAdapter {
    private Activity activity;
    private List<Food> foods;

    public FoodListAdapter(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.foods.size();
    }

    @Override
    public Object getItem(int i) {
        return this.foods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder viewHolder;
//
//        if(convertView==null){
//
//        }else{
//
//        }
        return null;
    }

    private class ViewHolder implements View.OnClickListener{
        protected MainPresenter presenter;
        private int position;

        public ViewHolder(View view, MainPresenter presenter){
            //Todo: ganti view ke binding
            this.presenter = presenter;
        }

        public void updateView(Food food, int position){
            this.position = position;

        }

        @Override
        public void onClick(View view) {

        }
    }

}
