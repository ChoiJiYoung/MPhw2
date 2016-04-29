package org.androidtown.simple_hw2_list;
/*
ID_NUMBER: 201333493
Name: 최지영
Description:
This is a login activity
At first, you have to give your secret code (4 digits) tostart the App.
The initial secret code should be 0000.
The password consists of 4 digits. So, when 4-th digit is given by the user, the
following password testing process should start automatically without explicitly
pressing a [OK] button – no [OK] button.
The characters for password box should be hidden with asterisk(*) characters.
User ID is NOT required
After the user login the app, it can be changed the password that you want to do.
* */

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editPW;
    SharedPreferences prefs;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editPW = (EditText) findViewById(R.id.password);
        prefs = getSharedPreferences("Password Change", MODE_PRIVATE);//To get sharedPreference string name: password change, mode is private
        password = prefs.getString("password", "0000");//to initialize the password that is element of sharedpreference

        editPW.setOnEditorActionListener(new TextView.OnEditorActionListener() {//The method calls When the user enter the keyboard completion button because of no button in here.
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (v.getId() == R.id.password && actionId == EditorInfo.IME_ACTION_DONE)// The user press the completion button
                    if (editPW.length() < 4 || editPW.length() > 4) { // if the user enter the password that exceed the length 4, toast message will be shown.
                        Toast.makeText(getApplicationContext(), "Please enter the 4 digit password", Toast.LENGTH_LONG).show();
                    } else {
                        if (password.equals("0000")) { // initial password
                            if (password.equals(editPW.getText().toString())) {// When login succeed, current activity moves to main activity
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "You entered the wrong password", Toast.LENGTH_LONG).show();// Entering the wrong number
                            }
                            return true;
                        } else if (!password.equals("0000") && password.equals(prefs.getString("password", ""))) { //To change the password in Change_password activity.
                            if (password.equals(editPW.getText().toString())) {// When login succeed, current activity moves to main activity
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else
                                Toast.makeText(getApplicationContext(), "You entered the wrong password", Toast.LENGTH_LONG).show();
                        }
                    }
                return false;
            }
        });
    }
}
