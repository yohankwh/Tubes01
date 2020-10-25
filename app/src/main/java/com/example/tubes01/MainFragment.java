package com.example.tubes01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.w3c.dom.Text;

public class MainFragment extends Fragment implements View.OnClickListener{
    private TextView foodSuggestion;
    private Button randomizerBtn;
    private MainPresenter presenter;

    public MainFragment(MainPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        this.randomizerBtn = view.findViewById(R.id.randomizer_btn);
        this.randomizerBtn.setOnClickListener(this);
        this.foodSuggestion = view.findViewById(R.id.main_body_tv);

        view.setOnClickListener(this);

        try {
            this.presenter.loadData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view==this.randomizerBtn){
            String food = this.presenter.getRandomFood();

            //basic duplicate prevention
            int i = 0;
            while(i<3){
                if(this.foodSuggestion.getText().equals(food)){
                    food = this.presenter.getRandomFood();
                    i++;
                }else{
                    break;
                }
            }

            this.foodSuggestion.setText(food);
        }
    }
}
