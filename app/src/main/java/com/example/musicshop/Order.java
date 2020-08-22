package com.example.musicshop;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class Order extends AppCompatActivity {
    private String userName;
    private String goodsName;
    private int quantity;
    private double orderPrice;
    private double price;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order(String userName , String goodsName , Integer quantity,
                 Double orderPrice,Double price){
        this.userName = userName;
        this.goodsName = goodsName;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
        this.price = price ;

    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getGoods() {
        return goodsName;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setGoods(String goods) {
        this.goodsName = goods;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
