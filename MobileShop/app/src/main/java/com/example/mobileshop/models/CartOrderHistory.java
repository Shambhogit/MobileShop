package com.example.mobileshop.models;

import java.util.ArrayList;

public class CartOrderHistory {
    ArrayList<BuyModel> buyModels;
    public CartOrderHistory(){

    }

    public CartOrderHistory(ArrayList<BuyModel> buyModels) {
        this.buyModels = buyModels;
    }

    public ArrayList<BuyModel> getBuyModels() {
        return buyModels;
    }
}
