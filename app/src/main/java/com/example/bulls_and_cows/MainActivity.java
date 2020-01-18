package com.example.bulls_and_cows;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b_check, b_reset;
    EditText number1, number2, number3, number4;
    TextView text_result, text_info;
    Random random;
    LinearLayout layout;

    int first_number, second_number, third_number, fourth_number;
    int g_1, g_2,g_3,g_4;
    int bulls = 0, cows = 0, tr = 0;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = (EditText) findViewById(R.id.number1);
        number2 = (EditText) findViewById(R.id.number2);
        number3 = (EditText) findViewById(R.id.number3);
        number4 = (EditText) findViewById(R.id.number4);

        number1.setFilters(new InputFilter[]{ new MinMaxFilter("1", "9")});
        number2.setFilters(new InputFilter[]{ new MinMaxFilter("1", "9")});
        number3.setFilters(new InputFilter[]{ new MinMaxFilter("1", "9")});
        number4.setFilters(new InputFilter[]{ new MinMaxFilter("1", "9")});

        b_check = (Button) findViewById(R.id.b_check);
        b_reset = (Button) findViewById(R.id.b_reset);

        text_result = (TextView) findViewById(R.id.text_result);
        text_info = (TextView) findViewById(R.id.text_info);

        layout = (LinearLayout) findViewById(R.id.r_layout);

        random = new Random();

        asked_number();

    }

    public void b_check(View view) {

        /*try{
            g_1 = Integer.parseInt(number1.getText().toString());
            g_2 = Integer.parseInt(number2.getText().toString());
            g_3 = Integer.parseInt(number3.getText().toString());
            g_4 = Integer.parseInt(number4.getText().toString());
        }catch (Exception exc){
            text_info.setText("Podaj wszystkie liczby");
        }*/

        if((number1.getText().toString().equals("")) || number2.getText().toString().equals("") || number3.getText().toString().equals("") || number4.getText().toString().equals("")){
            text_info.setText("Podaj wszystkie liczby");
        }
        else {
            g_1 = Integer.parseInt(number1.getText().toString());
            g_2 = Integer.parseInt(number2.getText().toString());
            g_3 = Integer.parseInt(number3.getText().toString());
            g_4 = Integer.parseInt(number4.getText().toString());

            if (g_1 == g_2 || g_1 == g_3 || g_1 == g_4 || g_2 == g_3 || g_2 == g_4 || g_3 == g_4 || g_1 == 0) {
                text_info.setText("Liczby nie moga sie powtarzac");
            }
            else {
                text_info.setText("");
                tr++;
                check();
                wincheck();
                bulls = 0;
                cows = 0;
            }
        }
    }

    public void b_reset(View view){
        layout.removeAllViews();
        result = "";
        text_result.setText("");
        layout.addView(text_result);

        text_info.setText("Wylosowaną liczbą była: " + first_number + second_number + third_number + fourth_number);

        tr = 0;
        asked_number();

    }

    private void asked_number() {

        first_number = random.nextInt(9) + 1;

        do{
            second_number = random.nextInt(9) + 1;
        }while (first_number == second_number);

        do{
            third_number = random.nextInt(9) + 1;
        }while (first_number == third_number || second_number == third_number);

        do{
            fourth_number = random.nextInt(9) + 1;
        }while (fourth_number == first_number || fourth_number == second_number || third_number == fourth_number );

    }

    private void check(){

        if(g_1 == first_number){
            bulls++;
        }
        if(g_2 == second_number){
            bulls++;
        }
        if(g_3 == third_number){
            bulls++;
        }
        if(g_4 == fourth_number){
            bulls++;
        }

        if(g_1 == second_number || g_1 == third_number || g_1 == fourth_number ){
            cows++;
        }
        if(g_2 == first_number || g_2 == third_number || g_2 == fourth_number ){
            cows++;
        }
        if(g_3 == second_number || g_3 == first_number || g_3 == fourth_number ){
            cows++;
        }
        if(g_4 == second_number || g_4 == third_number || g_4 == first_number ){
            cows++;
        }

        result = result + ""  + tr + ". " + g_1 + "" + g_2 + "" + g_3 + "" + g_4 + " Bulls: " + bulls + " Cows: " + cows + "\n";
        text_result.setText(result);
    }

    private void wincheck(){
        if(bulls == 4){
            text_info.setText("Wygrales. " + tr + " prób" );
        }
    }
}