package com.cse.mist.bookstack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ReqProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reqprofile);


        Button btn = (Button) findViewById(R.id.contact);

        /**    Defining a click event listener    */
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Creating an intent with the dialer's action name  */
                /** Since the intent is created with activity's action name, the intent is an implicit intent */
                Intent intent = new Intent("android.intent.action.DIAL");

                /** Starting the Dialer activity */
                startActivity(intent);
            }
        };
        btn.setOnClickListener(listener);

    }


}
