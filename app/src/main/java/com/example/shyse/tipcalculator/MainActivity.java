package com.example.shyse.tipcalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    String pattern = "#####.##";
    DecimalFormat amountFormat = new DecimalFormat(pattern);

    double startAmount = 0, totalTipAmount = 0;
    int persons=1;
    String lastButton = "", recentButton = "";

    EditText input, seekCustom, split, customText;
    TextView tipText, personText, totalText;
    RadioButton customPercent;
    Button tenPercent, fifteenPercent, twentyPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText)findViewById(R.id.billAmount);
        seekCustom = (EditText)findViewById(R.id.billAmount);
        split = (EditText)findViewById(R.id.splitText);
        tipText = (TextView)findViewById(R.id.tipTextView);
        personText = (TextView)findViewById(R.id.personTextView);
        totalText = (TextView)findViewById(R.id.totalTextView);
        tenPercent = (Button) findViewById(R.id.tenPercent);
        fifteenPercent = (Button) findViewById(R.id.fifteenPercent);
        twentyPercent = (Button) findViewById(R.id.twentyPercent);
        customPercent = (RadioButton) findViewById(R.id.customRadio);
        customText = (EditText) findViewById(R.id.seekText);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(input.getText().toString())){
                    personText.setText("-");
                    totalText.setText("-");
                }else{
                    if (lastButton == "ten") {
                        onTenBtn(tenPercent);
                        startAmount = Double.parseDouble(input.getText().toString());
                        totalText.setText(amountFormat.format(startAmount));
                        setSplitAmount(persons);
                    } else if (lastButton == "fifteen") {
                        onFifteenBtn(fifteenPercent);
                        startAmount = Double.parseDouble(input.getText().toString());
                        totalText.setText(amountFormat.format(startAmount));
                        setSplitAmount(persons);
                    } else if (lastButton == "twenty") {
                        onTwentyBtn(twentyPercent);
                        startAmount = Double.parseDouble(input.getText().toString());
                        totalText.setText(amountFormat.format(startAmount));
                    } else {
                        startAmount = Double.parseDouble(input.getText().toString());
                        totalText.setText(amountFormat.format(startAmount));
                        setSplitAmount(persons);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        customText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(customText.getText().toString())){
                    personText.setText("-");
                    totalText.setText("-");
                }else{
                    Double customPercent = (Double.parseDouble(customText.getText().toString()))/100;
                    startAmount = Double.parseDouble(input.getText().toString());
                    totalTipAmount = startAmount * customPercent;
                    totalText.setText(amountFormat.format(startAmount + totalTipAmount));
                    tipText.setText(amountFormat.format(totalTipAmount));
                    setSplitAmount(persons);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



    }

    public void onTenBtn(View v) {
        //recentButton = lastButton;
        //lastButton = "ten";
        if (TextUtils.isEmpty(input.getText().toString())){
            Toast.makeText(getApplicationContext(),"Please enter bill amount",Toast.LENGTH_SHORT).show();
        } else {
            if (customPercent.isChecked()) {
                disableCustomTip();
            }
            recentButton = lastButton;
            lastButton = "ten";
            setButtonColor(v);
            startAmount = Double.parseDouble(input.getText().toString());
            totalTipAmount = startAmount * .10;
            totalText.setText(amountFormat.format(startAmount + totalTipAmount));
            tipText.setText(amountFormat.format(totalTipAmount));
            setSplitAmount(persons);
        }
        Log.d("CLICKED", "onClick: you clicked 10!!");
    }

    public void onFifteenBtn(View v) {
        recentButton = lastButton;
        lastButton = "fifteen";
        if (TextUtils.isEmpty(input.getText().toString())){
            Toast.makeText(getApplicationContext(),"Please enter bill amount",Toast.LENGTH_SHORT).show();
        } else {
            if (customPercent.isChecked()) {
                disableCustomTip();
            }
            setButtonColor(v);
            startAmount = Double.parseDouble(input.getText().toString());
            totalTipAmount = startAmount * .15;
            totalText.setText(amountFormat.format(startAmount + totalTipAmount));
            tipText.setText(amountFormat.format(totalTipAmount));
            setSplitAmount(persons);
        }
        Log.d("CLICKED", "onClick: you clicked 15!!");
    }

    public void onTwentyBtn(View v) {
        recentButton = lastButton;
        lastButton = "twenty";
        if (TextUtils.isEmpty(input.getText().toString())){
            Toast.makeText(getApplicationContext(),"Please enter bill amount",Toast.LENGTH_SHORT).show();
        } else {
            if (customPercent.isChecked()) {
                disableCustomTip();
            }
            setButtonColor(v);
            startAmount = Double.parseDouble(input.getText().toString());
            totalTipAmount = startAmount * .20;
            totalText.setText(amountFormat.format(startAmount + totalTipAmount));
            tipText.setText(amountFormat.format(totalTipAmount));
            setSplitAmount(persons);
        }
        Log.d("CLICKED", "onClick: you clicked 20!!");
    }

    public void onRadioBtn(View v){
        Log.d("RADIO BUTTON", "onClick: you clicked it");
        clearBtnColor();
        customText.setEnabled(true);
        Double customPercent = Double.parseDouble(customText.getText().toString());
        startAmount = Double.parseDouble(input.getText().toString());
        totalTipAmount = startAmount * (customPercent / 100) ;
        totalText.setText(amountFormat.format(startAmount + totalTipAmount));
        tipText.setText(amountFormat.format(totalTipAmount));
        setSplitAmount(persons);

    }

    public void onIncreaseBtn(View v) {
        persons = Integer.parseInt(split.getText().toString());
        persons = persons + 1;
        split.setText(Integer.toString(persons));
        setSplitAmount(persons);
        Log.d("CLICKED", "onClick: you clicked Increase Button + " + Integer.toString(persons) );
    }
    public void onDecreaseBtn(View v) {
        if (Integer.parseInt(split.getText().toString()) <= 1){
            Toast.makeText(getApplicationContext(),"Split amount cannot be less than 1 ",Toast.LENGTH_SHORT).show();
            Log.d("CLICKED", "onClick: ERROR!" );
        } else {
            persons = Integer.parseInt(split.getText().toString());
            persons = persons - 1;
            split.setText(Integer.toString(persons));
            setSplitAmount(persons);
            Log.d("CLICKED", "onClick: you clicked Decrease Button + " + Integer.toString(persons) );
        }
    }

    public void setSplitAmount(Integer splitway){
        Log.d("TESTING SPLIT B4", splitway.toString());
        if (TextUtils.isEmpty(input.getText().toString())){

        } else {
            double perSplit = (Double.parseDouble(totalText.getText().toString())) / splitway;
            personText.setText(Double.toString(perSplit));
            Log.d("TESTING SPLIT AFTER", Double.toString(perSplit));
        }
    }

    public void setButtonColor(View v){
        switch (recentButton){
            case "ten":
                tenPercent.setBackgroundResource(android.R.drawable.btn_default);
                break;
            case "fifteen":
                fifteenPercent.setBackgroundResource(android.R.drawable.btn_default);
                break;
            case "twenty":
                twentyPercent.setBackgroundResource(android.R.drawable.btn_default);
                break;
            default:
                v.setBackgroundResource(android.R.drawable.btn_default);
        }
        v.setBackgroundColor(Color.parseColor("#D81B60"));
    }

    public void clearBtnColor(){
        tenPercent.setBackgroundResource(android.R.drawable.btn_default);
        fifteenPercent.setBackgroundResource(android.R.drawable.btn_default);
        twentyPercent.setBackgroundResource(android.R.drawable.btn_default);
    }

    public void disableCustomTip(){
        customPercent.setChecked(false);
    }

}
