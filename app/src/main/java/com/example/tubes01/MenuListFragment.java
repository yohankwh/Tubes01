package com.example.tubes01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes01.models.Food;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MenuListFragment extends Fragment implements View.OnClickListener {
    private FoodListAdapter adapter;
    private ListView foods;
    private MainPresenter presenter;
    private FloatingActionButton btn_add_menu;
    private FragmentListener fragmentListener;
//    private TextView view_food;

    public MenuListFragment(MainPresenter presenter){
        this.presenter = presenter;
        this.adapter = new FoodListAdapter((Activity) this.presenter.ui, this.presenter );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        this.foods = view.findViewById(R.id.lst_foods);
        this.btn_add_menu = view.findViewById(R.id.fragment_menu_floating_btn);
//        this.view_food = view.findViewById(R.id.textViewFood);

        this.foods.setAdapter(this.adapter);
        this.btn_add_menu.setOnClickListener(this);
//        this.view_food.setOnClickListener(this);
        return view;
    }

    public void updateList(List<Food> foods){
        this.adapter.update(foods);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.fragmentListener = (FragmentListener) context;
        } else{
            throw new ClassCastException(context.toString()
                    + "must implement FragmentListener");
        }
    }

    @Override
    public void onClick(View v) {
        if(v==this.btn_add_menu){
            this.fragmentListener.changePage(3);
        }
//        else if(v==this.view_food){
//            this.fragmentListener.changePage(4);
//            Log.d("debug", "menu detail");
//        }
    }
}