package com.example.tubes01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.tubes01.models.Food;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentListener, IMainActivity {
    private FragmentManager fragmentManager;
    private DrawerLayout drawer;
    private MainFragment mainFragment;
    private MenuListFragment menuListFragment;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.presenter = new MainPresenter(this);
        this.fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();

        this.drawer = findViewById(R.id.drawer_layout);

        this.mainFragment = new MainFragment(this.presenter);
        this.menuListFragment = new MenuListFragment(this.presenter);

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

    @Override
    public void changePage(int page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==1){
            if(this.mainFragment.isAdded()){
                ft.show(this.mainFragment);
            }else{
                ft.add(R.id.fragment_container, this.mainFragment);
            }
            if(this.menuListFragment.isAdded()){
                ft.hide(this.menuListFragment);
            }
            this.drawer.closeDrawers();
//            ft.replace(R.id.fragment_container, this.fragment1)
//                    .addToBackStack(null);
        }else if(page==2){
            if(this.menuListFragment.isAdded()){
                ft.show(this.menuListFragment);
            }else{
                ft.add(R.id.fragment_container, this.menuListFragment)
                        .addToBackStack(null);
            }
            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }
            this.drawer.closeDrawers();
        }
        ft.commit();
    }

    @Override
    public void updateList(List<Food> foods) {
        this.menuListFragment.updateList(foods);
    }

    @Override
    public void resetAddForm() {

    }
}