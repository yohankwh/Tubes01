package com.example.tubes01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private DrawerLayout drawer;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();

        this.drawer = findViewById(R.id.drawer_layout);

        this.mainFragment = new MainFragment();

        ft.add(R.id.fragment_container, this.mainFragment)
                .addToBackStack(null)
                .commit();

//        FloatingActionButton floatingActionButton=findViewById(R.id.fragment_menu_floating_btn);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Floating Action Button Berhasil dibuat", Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }
}