package com.example.tubes01;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class NavFragment extends Fragment implements View.OnClickListener{
    private Button navbtn;
    private Button exitBtn;
    private Button homeBtn;
    private Button settingBtn;

    private FragmentListener fragmentListener;

    public NavFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav, container, false);
        this.navbtn = view.findViewById(R.id.btn_nav_menu);
        this.exitBtn = view.findViewById(R.id.btn_nav_exit);
        this.homeBtn = view.findViewById(R.id.btn_nav_home);
        this.settingBtn = view.findViewById(R.id.btn_nav_setting);
        this.navbtn.setOnClickListener(this);
        this.exitBtn.setOnClickListener(this);
        this.homeBtn.setOnClickListener(this);
        this.settingBtn.setOnClickListener(this);
        view.setOnClickListener(this);
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
        if(view == this.navbtn){
            this.fragmentListener.changePage(2);
        }else if(view==this.exitBtn){
            Log.d("debug", "tombol keluar");

            AlertDialog.Builder builderAlert = new AlertDialog.Builder(getActivity());
            builderAlert.setTitle("Keluar")
                    .setMessage("Apakah kamu ingin keluar?")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("debug", "clicked Delete");
                            fragmentListener.closeApplication();
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                                    Log.d("debug", "clicked tidak jadi delete");
                            dialog.cancel();
                        }
                    });
            builderAlert.create();
            builderAlert.show();

        }else if(view.getId()==this.homeBtn.getId()){
            this.fragmentListener.changePage(1);
            Log.d("debug", "pindah halaman ke home");
        }else if(view==this.settingBtn){
//            this.fragmentListener.changePage(3);
        }
    }
}