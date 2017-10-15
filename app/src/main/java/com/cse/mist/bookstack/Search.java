package com.cse.mist.bookstack;

import android.app.SearchManager;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Search extends AppCompatActivity implements SensorEventListener {

    Button PopUpButton;
    double SHAKE_THRESHOLD = 800;
    long lastTime;
    float lastx, lasty, lastz;
    private EditText editTextInput2;
    private Sensor accelerometer;
    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);


        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        PopUpButton = (Button) findViewById(R.id.popupButton);


        editTextInput2 = (EditText) findViewById(R.id.editTextInput1);
        PopUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(Search.this, PopUpButton);

                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        Toast.makeText(Search.this, "Selected:" + item.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
    }

    public void onSearchClick(View v) {
        try {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            String term = editTextInput2.getText().toString();
            intent.putExtra(SearchManager.QUERY, term);
            startActivity(intent);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.one1:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                getActionBar().getDisplayOptions();
                return true;
            case R.id.one2:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                getActionBar().getDisplayOptions();
                return true;
            case R.id.one3:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                getActionBar().getDisplayOptions();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
                    Intent in = new Intent(Search.this, Library.class);
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
