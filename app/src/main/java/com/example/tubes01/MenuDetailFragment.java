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

import com.example.tubes01.FoodListAdapter;
import com.example.tubes01.MainPresenter;
import com.example.tubes01.models.Food;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MenuDetailFragment extends Fragment implements View.OnClickListener{
    private FoodListAdapter adapter;
    private ListView foods;
    private MainPresenter presenter;//nanti gak perlu kyknya?
    private FloatingActionButton btn_add_menu;
    private FragmentListener fragmentListener;

    public MenuDetailFragment(MainPresenter presenter){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_desc, container, false);

        this.foods = view.findViewById(R.id.lst_foods);
        this.btn_add_menu = view.findViewById(R.id.fragment_menu_floating_btn);


        this.foods.setAdapter(this.adapter);
        this.btn_add_menu.setOnClickListener(this);
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
    }
}
