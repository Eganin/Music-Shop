package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        receivingDataUserName();
        //receivingDataGoodsName();
        //receivingDataQuantity();
        //receivingDataOrderPrice();
    }

    public void receivingDataUserName(){
        Intent receivedOrderIntent = getIntent();
        // получение отправленных данных
        String userName = receivedOrderIntent.getStringExtra("userName");
        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(userName);
    }

    public void receivingDataGoodsName(){
        Intent receivedOrderIntent = getIntent();
        String goodsName = receivedOrderIntent.getStringExtra("goodsName");
    }

    public void receivingDataQuantity(){
        Intent receivedOrderIntent = getIntent();
        // получение числа
        Integer quantity = receivedOrderIntent.getIntExtra("quantity",0);
    }

    public void receivingDataOrderPrice(){
        Intent receivedOrderIntent = getIntent();
        Double orderPrice = receivedOrderIntent.getDoubleExtra("orderPrice",0.0D);
    }
}