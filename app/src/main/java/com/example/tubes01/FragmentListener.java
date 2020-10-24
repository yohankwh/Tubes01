package com.example.tubes01;

import com.example.tubes01.models.Food;

public interface FragmentListener {
    void changePage(int page);
    void closeApplication();
    void showMenuDetails(Food food, int position);
    void resetEditPage();
}
