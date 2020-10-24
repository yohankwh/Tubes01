package com.example.tubes01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tubes01.models.Food;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.LinkedList;
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
            viewHolder = new ViewHolder(convertView, this.presenter, this.foods);
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
        private ImageButton trash;
        private List<Food> tempFood;
        private FragmentListener fragmentListener;
        private Food food;

        public ViewHolder(View view, MainPresenter presenter, List<Food> foods){
            //Todo: ganti view ke binding
            this.presenter = presenter;
            this.title = view.findViewById(R.id.textViewFood);
            this.trash = view.findViewById(R.id.img_btn_trash);
            this.tempFood = new LinkedList<Food>();
            this.tempFood =foods;
        }

        public void updateView(Food food, int position){
            this.position = position;
            this.title.setText(food.getName());
            this.title.setOnClickListener(this);
            this.trash.setOnClickListener(this);
            this.food = food;
        }

        @Override
        public void onClick(View view) {

            if(view==this.trash){
                AlertDialog.Builder builderAlert = new AlertDialog.Builder(activity);
                builderAlert.setTitle("Information")
                        .setMessage("Are you sure want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("debug", "clicked Delete");
//                                tempFood.remove(position);

                                try {
                                    presenter.deleteList(position);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

//                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                    Log.d("debug", "clicked tidak jadi delete");
                                dialog.cancel();
                            }
                        });
                builderAlert.create();
                builderAlert.show();
            }else if(view==this.title){
                Log.d("TESTXA",this.title.getText()+"");
                this.presenter.getFoodDetails(this.food, this.position);
                Log.d("TESTXB",this.food.getName()+"");
                this.presenter.changePagePresenter(4);
            }
        }
    }

}
