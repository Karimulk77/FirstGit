package com.cse.mist.bookstack;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BorrowBook extends AppCompatActivity {

    private TextView txtTimerDay;
    private TextView tvEvent;
    private Handler handler;
    private Runnable runnable;
    private TextView bookret, bookuse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrowbook);
        bookret = (TextView) findViewById(R.id.bookret);
        bookuse = (TextView) findViewById(R.id.bookuse);
        txtTimerDay = (TextView) findViewById(R.id.txtTimerDay);
        tvEvent = (TextView) findViewById(R.id.tvhappyevent);
        countDownStart();


        bookret.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View view) {

                                           Intent intent1 = new Intent(BorrowBook.this, ReqProfile.class);
                                           startActivity(intent1);

                                       }
                                   }
        );

        bookuse.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View view) {

                                           Intent intent1 = new Intent(BorrowBook.this, ReqProfile.class);
                                           startActivity(intent1);

                                       }
                                   }
        );

    }

    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");
// Please here set your event date//YYYY-MM-DD
                    Date futureDate = dateFormat.parse("2017-11-02");
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        txtTimerDay.setText("" + String.format("%02d", days));
                    } else {
                        tvEvent.setVisibility(View.VISIBLE);
                        tvEvent.setText("Return Book");
                        textViewGone();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }

    public void textViewGone() {
        findViewById(R.id.LinearLayout10).setVisibility(View.GONE);

    }
}