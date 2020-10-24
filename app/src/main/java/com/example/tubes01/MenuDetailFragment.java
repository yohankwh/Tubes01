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
    private MainPresenter presenter;
    private FloatingActionButton btn_edit_menu;
    private FragmentListener fragmentListener;
    private Food food;
    private int position;
    private TextView title;
    private TextView tag;
    private TextView bahan;
    private TextView langkah;
    private TextView resto;

    public MenuDetailFragment(MainPresenter presenter){
        this.presenter = presenter;
    }

    public void setFood(Food food, int position){
        this.food = food;
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_desc, container, false);

        this.btn_edit_menu = view.findViewById(R.id.fragment_menu_floating_btn);
        this.btn_edit_menu.setOnClickListener(this);

        this.title = view.findViewById(R.id.tv_menu_makanan);
        this.tag = view.findViewById(R.id.tv_menu_tag);
        this.bahan = view.findViewById(R.id.tv_menu_bahan);

        this.title.setText(this.food.getName());
        this.tag.setText(this.food.getTag());
        this.bahan.setText(this.food.getBahan());

        return view;
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
        if(v==this.btn_edit_menu){
            this.fragmentListener.changePage(5);
        }
    }

    public void resetDetail(){
        this.title.setText(this.food.getName());
        this.tag.setText(this.food.getTag());
        this.bahan.setText(this.food.getBahan());
    }
}
