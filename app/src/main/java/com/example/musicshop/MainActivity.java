package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// используем interface AdapterView.OnItemSelectedListener чтобы прослушать Spinner
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static Integer quantity;
    private static final String NULL_RESULT = "0";// нулевое значение
    private String[] nameGoods = new String[]{"guitar", "drums", "keyboard"};
    private Double[] goodsPrice = new Double[]{500.0D, 1000.0D, 1500.0D};
    private String goodsName;
    private double price;
    private Spinner spinner;
    private HashMap<String, Double> goodsMap;
    EditText userName;
    TextView quantityOrder;
    TextView orderPrice;
    ArrayList<String> spinnerArrayList;
    ArrayAdapter<String> spinnerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillingSpinner();
        fillingMap();

    }

    public void increaseQuantity(View view) {
        /*
        прибавляем значение к textview
         */
        TextView textViewCounter = findViewById(R.id.textViewCounter);// находим по id
        String textCounter = (String) textViewCounter.getText(); // получаем текст
        quantity = Integer.parseInt(textCounter);
        quantity++;
        String result = String.valueOf(quantity);
        textViewCounter.setText(result); // вставляем текст
        TextView textViewPrice = findViewById(R.id.textViewPrice);
        String resultSum = String.valueOf(price * quantity);
        textViewPrice.setText(resultSum);
    }

    public void addingQuantity(View view) {
        /*
        уменьшаем  значение у textview
         */
        TextView textViewCounter = findViewById(R.id.textViewCounter);
        String textCounter = (String) textViewCounter.getText();
        quantity = Integer.parseInt(textCounter);
        if (quantity > 0) {// чтобы нельзя было уйти в минус
            quantity--;
            String result = String.valueOf(quantity);
            textViewCounter.setText(result);
            TextView textViewPrice = findViewById(R.id.textViewPrice);
            String resultSum = String.valueOf(price * quantity);
            textViewPrice.setText(resultSum);
        } else {
            textViewCounter.setText(NULL_RESULT);
        }

    }

    public void addToOrder(View view) {
        /*
        Добавдение информации в класс order
         */
        userName = findViewById(R.id.inputNameEditText);
        quantityOrder = findViewById(R.id.textViewCounter);
        orderPrice = findViewById(R.id.textViewPrice);


        try{
            // берем введенную информацию
            Order order = new Order(userName.getText().toString(),
                    goodsName,
                    quantity,
                    (Double) (quantity*price),
                    price);

            // запуск нового окна
            Intent orderIntent = new Intent(MainActivity.this,OrderActivity.class);
            activityPutValues(orderIntent , order);


        }catch (Exception e){// если пользователь не ввел значения
            Button addToOrder = findViewById(R.id.addToOrder);
            addToOrder.setText("Введите значения");
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*
        Метод который срабатывает на нажатие объекта из выпадающего списка - spinner
         */
        clickedItemSpinner();

        // при нажатии элемента в списке выбираем соответсвующее изображение
        setImageItem();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setImageItem() {
        ImageView imageView = findViewById(R.id.imageItem);
        switch (goodsName) {
            case "guitar":
                // установка изображения из папки drawable
                imageView.setImageResource(R.drawable.guitar);
                break;

            case "drums":
                imageView.setImageResource(R.drawable.drum);
                break;

            case "keyboard":
                imageView.setImageResource(R.drawable.piano);
                break;
        }
    }

    private void fillingSpinner() {
        /*
        создание spinner и
        наполнение spinner значением
         */
        spinner = findViewById(R.id.choiceSpinner);
        spinner.setOnItemSelectedListener(this);// активировать прослушыватель событий
        spinnerArrayList = new ArrayList<>(3);// содержимое списка spinner
        Collections.addAll(spinnerArrayList, nameGoods);// добавляем в spinnerArrayList массив
        //init adapter to spinner
        spinnerAdapter = new ArrayAdapter<>(this,//class
                android.R.layout.simple_spinner_item,//const
                spinnerArrayList);//collection

        // устанавливаем выпадающий список
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter); // add Adapter
    }

    private void fillingMap() {
        /*
        наполнение map стоимостью и названием товара
         */
        goodsMap = new HashMap<>();
        for (int i = 0; i < goodsPrice.length; i++) {
            goodsMap.put(nameGoods[i], goodsPrice[i]);
        }
    }

    public void clickedItemSpinner() {
        TextView textViewCounter = findViewById(R.id.textViewCounter);// находим по id
        String textCounter = (String) textViewCounter.getText(); // получаем текст
        // выбранное значение в spinner на данный момент
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        int quantity = Integer.parseInt(textCounter);
        TextView textViewPrice = findViewById(R.id.textViewPrice);
        String result = String.valueOf(price * quantity);
        textViewPrice.setText(result);
    }

    private void activityPutValues(Intent orderIntent , Order order){
        // отправляем информацию в другую activity
        // типом ключ значение
        orderIntent.putExtra("userName",order.getUserName());
        orderIntent.putExtra("goodsName",order.getGoods());
        orderIntent.putExtra("quantity",order.getQuantity());
        orderIntent.putExtra("orderPrice",order.getOrderPrice());
        orderIntent.putExtra("price",order.getPrice());
        startActivity(orderIntent);// запуск activity
    }

}