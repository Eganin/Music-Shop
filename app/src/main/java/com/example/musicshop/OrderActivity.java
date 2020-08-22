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

        setTitle("Your order"); // изменение названия activity

        receivingDataUserName();
        receivingDataGoodsName();
        receivingDataQuantity();
        receivingDataOrderPrice();
        receivingDataPrice();
    }

    public void receivingDataUserName(){
        Intent receivedOrderIntent = getIntent();
        // получение отправленных данных
        String userName = receivedOrderIntent.getStringExtra("userName");
        TextView orderTextView = findViewById(R.id.orderTextView);
        String resultString = "Customer name: " + userName;
        orderTextView.setText(resultString);
    }

    public void receivingDataGoodsName(){
        Intent receivedOrderIntent = getIntent();
        String goodsName = receivedOrderIntent.getStringExtra("goodsName");
        TextView orderTextView = findViewById(R.id.orderGoodsName);
        String resultString = "Goods name: " + goodsName;
        orderTextView.setText(resultString);
    }

    public void receivingDataQuantity(){
        Intent receivedOrderIntent = getIntent();
        // получение числа
        Integer quantity = receivedOrderIntent.getIntExtra("quantity",0);
        TextView orderTextView = findViewById(R.id.orderQuantity);
        String resultString = "Quantity: " + quantity;
        orderTextView.setText(resultString);
    }

    public void receivingDataOrderPrice(){
        Intent receivedOrderIntent = getIntent();
        Double orderPrice = receivedOrderIntent.getDoubleExtra("orderPrice",0.0D);
        TextView orderTextView = findViewById(R.id.orderPrice);
        String resultString = "Order Price: " + orderPrice;
        orderTextView.setText(resultString);
    }

    public void receivingDataPrice(){
        Intent receivedOrderIntent = getIntent();
        Double price = receivedOrderIntent.getDoubleExtra("price",0.0D);
        TextView orderTextView = findViewById(R.id.orderTextViewPrice);
        String resultString = "Price: " + price;
        orderTextView.setText(resultString);
    }
}