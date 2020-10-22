package com.example.tubes01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import org.json.JSONException;

public class CreateFragment extends Fragment implements View.OnClickListener{
    private MainPresenter presenter;
    private Button addMenuBtn;
    private EditText etName;
    private EditText etTag;
    private EditText etBahan;

    public CreateFragment(MainPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container, false);

        this.addMenuBtn = view.findViewById(R.id.btn_create);
        this.etName = view.findViewById(R.id.et_input_name);
        this.etBahan = view.findViewById(R.id.et_input_bahan);
        this.etTag = view.findViewById(R.id.et_input_tag);

        this.addMenuBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.addMenuBtn){
            String name = this.etName.getText().toString();
            String bahan = this.etBahan.getText().toString();
            String tag = this.etTag.getText().toString();

            try {
                this.presenter.addList(name, tag, bahan);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
