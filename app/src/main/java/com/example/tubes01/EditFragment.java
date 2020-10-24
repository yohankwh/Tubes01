package com.example.tubes01;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tubes01.models.Food;

import org.json.JSONException;

public class EditFragment extends Fragment implements View.OnClickListener{
    private MainPresenter presenter;
    private FragmentListener fragmentListener;
    private Food food;
    private int position;
    private EditText title;
    private EditText tag;
    private EditText bahan;
    private Button updateBtn;

    public EditFragment(MainPresenter presenter) {
        this.presenter = presenter;
        // Required empty public constructor
    }

    public void setFood(Food food, int position){
        this.food = food;
        this.position = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        this.title = view.findViewById(R.id.et_input_name_edit);
        this.tag = view.findViewById(R.id.et_input_tag_edit);
        this.bahan = view.findViewById(R.id.et_input_bahan_edit);
        this.updateBtn = view.findViewById(R.id.btn_update);

        this.title.setText(this.food.getName());
        this.tag.setText(this.food.getTag());
        this.bahan.setText(this.food.getBahan());

        this.updateBtn.setOnClickListener(this);

        // Inflate the layout for this fragment
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
    public void onClick(View view) {
        if(view == this.updateBtn){
            this.food.setName(this.title.getText().toString());
            this.food.setTag(this.tag.getText().toString());
            this.food.setBahan(this.bahan.getText().toString());

            try {
                this.presenter.addList(this.food, this.position);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            this.fragmentListener.changePage(2);
        }
    }
}