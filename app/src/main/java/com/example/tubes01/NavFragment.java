package com.example.tubes01;

import android.content.Context;
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
        this.navbtn.setOnClickListener(this);
        this.exitBtn.setOnClickListener(this);
        this.homeBtn.setOnClickListener(this);
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
            this.fragmentListener.closeApplication();
            Log.d("debug", "keluar dari aplikasi");
        }else if(view.getId()==this.homeBtn.getId()){
            this.fragmentListener.changePage(1);
            Log.d("debug", "pindah halaman ke home");
        }
    }
}