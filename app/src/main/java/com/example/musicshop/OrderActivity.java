package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    private static final String NULL_MESSAGE = "Введите свой email";
    private static final String NOT_APPLICATION_EMAIL = "Мы не можем отрпавить сообщение";
    String[] addresses ;// почта
    String subject = "Order from Music Shop";// тема сообщения
    StringBuffer emailText = new StringBuffer("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your order"); // изменение названия activity

        /*
        Данные методы берут информацию которая была отправлена из
        MainActivity
        и применяют ее в ActivityOrder
         */
        receivingDataIntentString(R.id.orderTextView, "Customer name:", "userName");
        receivingDataIntentString(R.id.orderGoodsName, "Goods name:", "goodsName");
        receivingDataIntentInteger(R.id.orderQuantity, "Quantity:", "quantity");
        receivingDataIntentDouble(R.id.orderTextViewPrice, "Price:", "price");
        receivingDataIntentDouble(R.id.orderPrice, "Price Order:", "orderPrice");
    }

    public void submitOrder(View view){
        /*
        Метод отвечает за отправку сообщений на email
         */
        getEmailUser();
        if(addresses[0].length()>0){// если пользователь ввел email
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // отпарвляем только на почту
            intent.putExtra(Intent.EXTRA_EMAIL,addresses);
            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            // преобразовываем StringBuffer в String для отправки сообщения
            intent.putExtra(Intent.EXTRA_TEXT,String.valueOf(emailText));// текст письма
            // убеждаемся что на телефоне есть приложение способное отправить и принять сообщение
            if(intent.resolveActivity(getPackageManager()) !=null){
                startActivity(intent);
            }else{
                Button buttonSendEmail = findViewById(R.id.buttonSendEmail);
                buttonSendEmail.setText(NOT_APPLICATION_EMAIL);
            }
        }else{
            Button buttonSendEmail = findViewById(R.id.buttonSendEmail);
            buttonSendEmail.setText(NULL_MESSAGE);
        }



    }

    public void receivingDataIntentString(int idLayout, String message, String keySend) {
        Intent receivedOrderIntent = getIntent();
        // получение отправленных данных
        String data = receivedOrderIntent.getStringExtra(keySend);
        TextView orderTextView = findViewById(idLayout);
        String resultString = message + " " + data+"\n";
        emailText.append(resultString); // добавляем в сообщение на почту
        orderTextView.setText(resultString);
    }


    public void receivingDataIntentInteger(int idLayout, String message, String keySend) {
        Intent receivedOrderIntent = getIntent();
        // получение числа
        Integer data = receivedOrderIntent.getIntExtra(keySend, 0);
        TextView orderTextView = findViewById(idLayout);
        String resultString = message + " " + data+"\n";
        emailText.append(resultString); // добавляем в сообщение на почту
        orderTextView.setText(resultString);
    }

    public void receivingDataIntentDouble(int idLayout, String message, String keySend) {
        Intent receivedOrderIntent = getIntent();
        Double data = receivedOrderIntent.getDoubleExtra(keySend, 0.0D);
        TextView orderTextView = findViewById(idLayout);
        String resultString = message + " " + data+"\n";
        emailText.append(resultString); // добавляем в сообщение на почту
        orderTextView.setText(resultString);
    }

    public void getEmailUser(){
        EditText editTextTextEmail = findViewById(R.id.editTextTextEmail);
        String email = editTextTextEmail.getText().toString();
        addresses = new String[]{email};
    }


}
