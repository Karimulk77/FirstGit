package com.cse.mist.bookstack;

import android.app.SearchManager;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Library extends AppCompatActivity implements SensorEventListener {

    ListView listviewbooks;
    DatabaseReference databasebook;
    List<Books> bookList;
    ListView ListViewBooks;
    double SHAKE_THRESHOLD = 800;
    long lastTime;
    float lastx, lasty, lastz;
    private EditText editTextInput;
    private Sensor accelerometer;
    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);


        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);


        databasebook = FirebaseDatabase.getInstance().getReference("books");


        editTextInput = (EditText) findViewById(R.id.editTextInput);
        ListViewBooks = (ListView) findViewById(R.id.listviewbooks);
        bookList = new ArrayList<>();
    }

    protected void onStart() {
        super.onStart();


        databasebook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bookList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //getting book
                    Books temp = snapshot.getValue(Books.class);

                    //adding book to the list
                    bookList.add(temp);

                }


                BookAdapter bookAdapter = new BookAdapter(Library.this, bookList);
                //BookAdapter bookAdapter1 = new BookAdapter(Main7Activity, bookList);

                //set the adapter to the list view
                ListViewBooks.setAdapter(bookAdapter);
                //bookListView.setAdapter(bookAdapter1);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    public void onSearchClick(View v) {
        try {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            String term = editTextInput.getText().toString();
            intent.putExtra(SearchManager.QUERY, term);
            startActivity(intent);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }


    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            long curTime = System.currentTimeMillis();

            if ((curTime - lastTime) > 100) {
                long diffTime = (curTime - lastTime);

                lastTime = curTime;
                double speed = Math.abs(x - lastx + y - lasty + z - lastx) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    Intent in = new Intent(Library.this, Notification.class);
                    startActivity(in);
                }
                lastx = x;
                lasty = y;
                lastz = z;

            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}