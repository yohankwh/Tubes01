package com.example.tubes01;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tubes01.models.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodListAdapter extends BaseAdapter {
    private Activity activity;
    private List<Food> foods;
    private MainPresenter presenter;

    public FoodListAdapter(Activity activity, MainPresenter presenter){
        this.activity = activity;
        this.foods = new ArrayList<Food>();
        this.presenter = presenter;
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
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView==null){
            convertView = LayoutInflater.from(this.activity).inflate(R.layout.item_list_food, parent, false);
            viewHolder = new ViewHolder(convertView, this.presenter);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.updateView((Food)this.getItem(i), i);

        // returns the view for the current row
        return convertView;
    }

    public void update(List<Food> foods){
        this.foods.clear();
        this.foods.addAll(foods);
        this.notifyDataSetChanged();
    }

    private class ViewHolder implements View.OnClickListener{
        protected MainPresenter presenter;
        protected TextView title;
        private int position;

        public ViewHolder(View view, MainPresenter presenter){
            //Todo: ganti view ke binding
            this.presenter = presenter;
            this.title = view.findViewById(R.id.textViewFood);
        }

        public void updateView(Food food, int position){
            this.position = position;
            this.title.setText(food.getName());

        }

        @Override
        public void onClick(View view) {

        }
    }

}
