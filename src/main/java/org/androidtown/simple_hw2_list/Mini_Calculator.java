package org.androidtown.simple_hw2_list;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Mini_Calculator extends AppCompatActivity implements View.OnClickListener {

    EditText Edit;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0;
    Button bdiv, bmul, bsub, badd;
    Button bC, bE;
    public float result;
    Boolean on = false;//button click boolean
    Boolean clear = false;//nothing in the boolean value
    String string;
    int length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini__calculator);

        Edit = (EditText) findViewById(R.id.Edit);
        b0 = (Button) findViewById(R.id.b0);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        bC = (Button) findViewById(R.id.bC);
        bE = (Button) findViewById(R.id.bE);
        badd = (Button) findViewById(R.id.badd);
        bsub = (Button) findViewById(R.id.bsub);
        bmul = (Button) findViewById(R.id.bmul);
        bdiv = (Button) findViewById(R.id.bdiv);

        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        bC.setOnClickListener(this);
        bE.setOnClickListener(this);
        badd.setOnClickListener(this);
        bsub.setOnClickListener(this);
        bmul.setOnClickListener(this);
        bdiv.setOnClickListener(this);

        Edit.setSelection(Edit.getText().toString().length());
    }

    @Override
    public void onClick(View v) {

        if (b0.getId() == v.getId()) {
            if (on != true)
                function("0");
        }
        if (b1.getId() == v.getId()) {
            function("1");
        }
        if (b2.getId() == v.getId()) {
            function("2");
        }
        if (b3.getId() == v.getId()) {
            function("3");
        }
        if (b4.getId() == v.getId()) {
            function("4");
        }
        if (b5.getId() == v.getId()) {
            function("5");
        }
        if (b6.getId() == v.getId()) {
            function("6");
        }
        if (b7.getId() == v.getId()) {
            function("7");
        }
        if (b8.getId() == v.getId()) {
            function("8");
        }
        if (b9.getId() == v.getId()) {
            function("9");
        }

        if (badd.getId() == v.getId()) {
            functionOp("+");
        }
        if (bsub.getId() == v.getId()) {
            function("-");
        }
        if (bmul.getId() == v.getId()) {
            function("×");
        }
        if (bdiv.getId() == v.getId()) {
            function("÷");
        }
        if (bC.getId() == v.getId()) {
            Edit.setText("");
            result = 0;
        }
        if (bE.getId() == v.getId()) {

            Intent myLocalIntent = getIntent();// to get the intent in current activity
            Bundle myBundle = new Bundle();//to create the bundle data to bound
            string = Edit.getText().toString();
            length = string.length();
            String last = string.substring(length - 1, length);
            int i, j = 0;
            int point, pointTemp;
            Boolean first = true;

            for (i = 0; i < length; i++) {
                point = string.codePointAt(i);//to represent  letters in Unicode
                //addition =43 subtraction=45 multiplication=215 division=247
                if (point == 43 || point == 45 || point == 215 || point == 247) {
                    if (first == true) {//When the first value is a  number
                        result = result + Float.parseFloat(string.substring(0, i));
                        first = false;//If the first value isn't a number, the boolean value will change the false
                    }
                    if (first == false) {
                        if (point == 43) {//addidtion
                            for (j = i + 1; j < length; j++) {
                                pointTemp = string.codePointAt(j);//The following string poisition  of preceding number
                                if (pointTemp == 43 || pointTemp == 45 || pointTemp == 215 || pointTemp == 247)
                                    break;
                            }
                            result = result + Float.parseFloat(string.substring(i + 1, j));
                        } else if (point == 45) {
                            for (j = i + 1; j < length; j++) {
                                pointTemp = string.codePointAt(j);
                                if (pointTemp == 43 || pointTemp == 45 || pointTemp == 215 || pointTemp == 247)
                                    break;
                            }
                            result = result - Float.parseFloat(string.substring(i + 1, j));
                        } else if (point == 215) {
                            for (j = i + 1; j < length; j++) {
                                pointTemp = string.codePointAt(j);
                                if (pointTemp == 43 || pointTemp == 45 || pointTemp == 215 || pointTemp == 247)
                                    break;
                            }
                            result = result * Float.parseFloat(string.substring(i + 1, j));
                        } else if (point == 247) {
                            for (j = i + 1; j < length; j++) {
                                pointTemp = string.codePointAt(j);
                                if (pointTemp == 43 || pointTemp == 45 || pointTemp == 215 || pointTemp == 247)
                                    break;
                            }
                            result = result / Float.parseFloat(string.substring(i + 1, j));
                        }
                    }
                }
            }
            //Edit.setText("" + result);//to show the result
            myBundle.putFloat("vresult", result); // to save the data in bundle
            myLocalIntent.putExtras(myBundle); // to store in intent
            setResult(Activity.RESULT_OK, myLocalIntent); // return to the value in the main activity
            finish();
            result = 0;
            clear = true;//to change the boolean value repeating the calculation
        }

    }

    void function(String s) {
        string = Edit.getText().toString();
        length = Edit.getText().toString().length();

        if (string.equals("") || string.equals("0")) {//string이 아무것도 안써있거나 0이면
            Edit.setText("" + s);
            on = false;
        } else {
            Edit.setText(string + s);
            on = false;
        }
    }

    void functionOp(String s) {
        string = Edit.getText().toString();
        if (clear == true) {
            Edit.setText("");
            clear = false;
        } else if (on == false) {
            Edit.setText(string + s);
            on = true;
        }

    }
}


