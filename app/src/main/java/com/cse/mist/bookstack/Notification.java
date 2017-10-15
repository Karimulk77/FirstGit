package com.cse.mist.bookstack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class Notification extends AppCompatActivity {
    private ImageButton imagebtn;
    private TextView name;
    private Button button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        imagebtn = (ImageButton) findViewById(R.id.imagebtn);
        name = (TextView) findViewById(R.id.name);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        imagebtn.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View view) {

                                            Intent intent = new Intent(Notification.this, ReqProfile.class);
                                            startActivity(intent);

                                        }
                                    }
        );

        name.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View view) {

                                        Intent intent1 = new Intent(Notification.this, MapsActivity.class);
                                        startActivity(intent1);

                                    }
                                }
        );

        button2.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View view) {

                                           Intent intent1 = new Intent(Notification.this, MapsActivity.class);
                                           startActivity(intent1);

                                       }
                                   }
        );

        button3.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View view) {

                                           Intent intent1 = new Intent(Notification.this, MainPageNav.class);
                                           startActivity(intent1);

                                       }
                                   }
        );


    }
}
