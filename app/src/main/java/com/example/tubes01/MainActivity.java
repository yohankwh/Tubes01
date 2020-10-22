package com.example.tubes01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;


import com.example.tubes01.models.Food;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentListener, IMainActivity {
    private FragmentManager fragmentManager;
    private DrawerLayout drawer;
    private MainFragment mainFragment;
    private MenuListFragment menuListFragment;
    private CreateFragment createFragment;
    private MainPresenter presenter;
    private Toolbar toolbar;
    private MenuDetailFragment menuDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.presenter = new MainPresenter(this);
        this.fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();

        this.drawer = findViewById(R.id.drawer_layout);

        this.mainFragment = new MainFragment(this.presenter);
        this.createFragment = new CreateFragment(this.presenter, this);
        this.menuListFragment = new MenuListFragment(this.presenter);
        this.menuDetailFragment = new MenuDetailFragment(this.presenter);

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
        this.toolbar =findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        ActionBarDrawerToggle abdt =new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();
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
            if(this.createFragment.isAdded()){
                ft.hide(this.createFragment);
            }

            if(this.menuDetailFragment.isAdded()){
                ft.hide(this.menuDetailFragment);
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
            if(this.createFragment.isAdded()){
                ft.hide(this.createFragment);
            }

            if(this.menuDetailFragment.isAdded()){
                ft.hide(this.menuDetailFragment);
            }
            this.drawer.closeDrawers();
        }else if(page==3){
            if(this.createFragment.isAdded()){
                ft.show(this.createFragment);
            }else{
                ft.add(R.id.fragment_container, this.createFragment)
                        .addToBackStack(null);
            }
            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }
            if(this.menuListFragment.isAdded()){
                ft.hide(this.menuListFragment);
            }

            if(this.menuDetailFragment.isAdded()){
                ft.hide(this.menuDetailFragment);
            }
            this.drawer.closeDrawers();
        }else if(page==4){
            if(this.createFragment.isAdded()){
                ft.show(this.menuDetailFragment);
            }else{
                ft.add(R.id.fragment_container, this.menuDetailFragment)
                        .addToBackStack(null);
            }

            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }

            if(this.menuListFragment.isAdded()){
                ft.hide(this.menuListFragment);
            }

            if(this.createFragment.isAdded()){
                ft.hide(this.createFragment);
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

    @Override
    public void closeApplication(){
        this.moveTaskToBack(true);
        this.finish();
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void saveToFile(String content){
        File file = new File(this.getFilesDir(),"newfile.txt");

        try (FileOutputStream fop = new FileOutputStream(file)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            Log.d("LOCATION: ",this.getFilesDir()+"");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readFile(){
        File file = new File(this.getFilesDir(),"newfile.txt");

        try (FileInputStream fis = new FileInputStream(file)) {

//            Log.d("size in byte is:",fis.available()+"");

        int content;
        String msg = "";
        while ((content = fis.read()) != -1) {
            msg=msg+(char)content;
        }
        return msg;

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }



}