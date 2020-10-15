package com.example.tubes01;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class MenuListFragment extends Fragment {
    private FoodListAdapter adapter;
    private ListView foods;
    private MainPresenter presenter;//nanti gak perlu kyknya?

    public MenuListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        this.adapter = new FoodListAdapter((Activity)this.presenter.ui, this.presenter);
        this.foods.setAdapter(this.adapter);


//        this.presenter.loadData();
        return view;
    }
}