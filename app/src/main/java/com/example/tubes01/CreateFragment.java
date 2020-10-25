package com.example.tubes01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private EditText etLgkhMasak;
    private EditText etResto;
    private MainActivity activity;


    public CreateFragment(MainPresenter presenter, MainActivity activity){
        this.presenter = presenter;
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container, false);

        this.addMenuBtn = view.findViewById(R.id.btn_create);
        this.etName = view.findViewById(R.id.et_input_name);
        this.etBahan = view.findViewById(R.id.et_input_bahan);
        this.etTag = view.findViewById(R.id.et_input_tag);
        this.etLgkhMasak = view.findViewById(R.id.et_input_langkah);
        this.etResto = view.findViewById(R.id.et_input_resto);

        this.addMenuBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.addMenuBtn){
            String name = this.etName.getText().toString();
            String bahan = this.etBahan.getText().toString();
            String tag = this.etTag.getText().toString();
            String langkah = this.etLgkhMasak.getText().toString();
            String resto = this.etResto.getText().toString();

            if(this.etName.getText().toString().equals("")){
                AlertDialog.Builder builderAlert = new AlertDialog.Builder(getActivity());
                builderAlert.setTitle("Information")
                        .setMessage("Cek kembali nama menu")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
////                                    Log.d("debug", "clicked tidak jadi delete");
//                            dialog.cancel();
//                        }
//                    });
                builderAlert.create();
                builderAlert.show();
                activity.hideKeyboard();
            }else{
                try {
                    this.presenter.addList(name, tag, bahan, langkah, resto);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                AlertDialog.Builder builderAlert = new AlertDialog.Builder(getActivity());
                builderAlert.setTitle("Information")
                        .setMessage("Menu berhasil ditambahkan")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Log.d("debug", "clicked Delete");
                            }
                        });
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
////                                    Log.d("debug", "clicked tidak jadi delete");
//                            dialog.cancel();
//                        }
//                    });
                builderAlert.create();
                builderAlert.show();

                activity.hideKeyboard();
                this.etName.setText("");
                this.etBahan.setText("");
                this.etTag.setText("");
                this.etLgkhMasak.setText("");
                this.etResto.setText("");

            }




        }
    }

}
