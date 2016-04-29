package org.androidtown.simple_hw2_list;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class descript_result extends AppCompatActivity {
    Button btn_Run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descript_result);
        btn_Run = (Button) findViewById(R.id.run);

        btn_Run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(descript_result.this, Mini_Calculator.class);
                startActivityForResult(intent, 101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if ((requestCode == 101) && (resultCode == Activity.RESULT_OK)) {
                Bundle myResults = data.getExtras();
                Float vresult = myResults.getFloat("vresult");
                Toast.makeText(getApplicationContext(), "The result = " + vresult, Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Problems -" + requestCode + "  " + resultCode, Toast.LENGTH_LONG).show();

        }
    }
}
