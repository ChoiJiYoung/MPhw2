package org.androidtown.simple_hw2_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Simple_Tip_Calculator extends AppCompatActivity {

    public EditText Total_amount, others;
    RadioGroup radGroup;
    RadioButton tip1;
    RadioButton tip2;
    RadioButton tip3;
    Button Caculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple__tip__calculator);

        Total_amount = (EditText) findViewById(R.id.Total_amount);
        others = (EditText) findViewById(R.id.others);
        radGroup = (RadioGroup) findViewById(R.id.radioGroup);
        tip1 = (RadioButton) findViewById(R.id.tip1);
        tip2 = (RadioButton) findViewById(R.id.tip2);
        tip3 = (RadioButton) findViewById(R.id.tip3);
        Caculate = (Button) findViewById(R.id.cal);


        Caculate.setOnClickListener(new View.OnClickListener() { //Caculate 버튼에 대한 설정

            @Override
            public void onClick(View v) { //to click the calculation button
                double tip_percent;
                double Result;
                double TA_num;
                double others_num;
                int radioId = radGroup.getCheckedRadioButtonId();//retun id value to be choosen in radiobutton

                String TA = Total_amount.getText().toString();//Enter the number in Total amount edit text (numberdecimal-positive number and floating number is posible)

                if (tip1.getId() == radioId) {//When the radiobutton value is first one
                    if (TA.compareTo("") == 0) {
                        Toast.makeText(getApplicationContext(), "Invalid! Please enter the number.", Toast.LENGTH_LONG).show();//Edit text에 아무것도 입력되지 않았을 때
                    } else {
                        TA_num = Double.parseDouble(TA); //to change type (string -> double number)
                        tip_percent = 0.15;
                        Result = TA_num * tip_percent;
                        Toast.makeText(getApplicationContext(), "Tip: " + Result + " Total: " + (Result + TA_num), Toast.LENGTH_LONG).show();
                    }
                } else if (tip2.getId() == radioId) {
                    if (TA.compareTo("") == 0) {
                        Toast.makeText(getApplicationContext(), "Invalid! Please enter the number.", Toast.LENGTH_LONG).show();
                    } else {
                        TA_num = Double.parseDouble(TA);
                        tip_percent = 0.2;
                        Result = TA_num * tip_percent;
                        Toast.makeText(getApplicationContext(), "Tip: " + Result + " Total: " + (Result + TA_num), Toast.LENGTH_LONG).show();
                    }
                } else if (tip3.getId() == radioId) {
                    if (TA.compareTo("") == 0) {
                        Toast.makeText(getApplicationContext(), "Invalid! Please enter the number.", Toast.LENGTH_LONG).show();
                    } else {
                        String ot = others.getText().toString(); //The user can enter the percent in others section others
                        if (ot.compareTo("") == 0) {//not to enter the value
                            Toast.makeText(getApplicationContext(), "Invalid! Please enter the number.", Toast.LENGTH_LONG).show();
                        } else {
                            others_num = Double.parseDouble(ot);
                            if (others_num != 15 && others_num != 20) {//put the value except of 2 preceding radiobutton values
                                TA_num = Double.parseDouble(TA);
                                tip_percent = others_num * 0.01;
                                Result = TA_num * tip_percent;
                                Toast.makeText(getApplicationContext(), "Tip: " + Result + " Total: " + (Result + TA_num), Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(getApplicationContext(), "Invalid! Please enter the another number.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }


        });

    }
}