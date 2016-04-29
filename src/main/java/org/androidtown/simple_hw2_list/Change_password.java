package org.androidtown.simple_hw2_list;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Change_password extends AppCompatActivity implements View.OnClickListener {

    EditText editPW;
    Button okay;
    Button cancel;
    String password;
    SharedPreferences sh_Pref;
    SharedPreferences.Editor editPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        editPW = (EditText) findViewById(R.id.password);
        okay = (Button) findViewById(R.id.okay);
        cancel = (Button) findViewById(R.id.cancel);

        okay.setOnClickListener(this);
        cancel.setOnClickListener(this);
        applySharedPreferences();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == okay.getId()) {
            if (editPW.length() < 4 || editPW.length() > 4) { // if the user enter the password that exceed the length 4, toast message will be shown.
                Toast.makeText(getApplicationContext(), "Please enter the 4 digit password", Toast.LENGTH_LONG).show();
            } else {
                password = editPW.getText().toString();
                sharedPreferences();//to call sharedPreferences method
                Intent intent = new Intent(Change_password.this, LoginActivity.class);//To move Login activity
                startActivity(intent);
            }
        } else if (v.getId() == cancel.getId()) {
            Cancel();//to call the cacel method
            finish();//get out of the current activity

        }
    }

    public void sharedPreferences() {
        sh_Pref = getSharedPreferences("Password Change", 0);//to modify the data using editor
        editPre = sh_Pref.edit();
        editPre.putString("password", password);//put data in type of string putString(key,value)
        editPre.commit();//to save the modification of value
    }

    public void Cancel() {
        sh_Pref = getSharedPreferences("Password Change", 0);
        editPre = sh_Pref.edit();
        editPre.remove("password");//to remove the whole data
        editPre.commit();
    }

    private void applySharedPreferences() { //the method to apply the sharedPreference
        sh_Pref = getSharedPreferences("Password Change", 0);
        if (sh_Pref != null && sh_Pref.contains("password")) // When the sharedpreferece data contain the password value
        {
            editPW.setText(sh_Pref.getString("password", "")); // to set the password in edit
        }
    }
}
