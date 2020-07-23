package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // there was a mistake in name of gryscope -> Gyroscope
    //  variables
    private Accelerometer accelerometer;
    private Gryscope gryscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Here Take Objects
        accelerometer = new Accelerometer(this);
        gryscope = new Gryscope(this);

        // Listener for accelerometer
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float last_x, float last_y, float last_z) {
                // Here Just Test Method , then will remove it and get values and save it in file
                /*
                if(last_x > 1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }
                else if(last_x < -1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }
                 */
                //
                //Toast.makeText(MainActivity.this, last_x + "X-axis", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, last_y + "Y-axis", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, last_z + "Z-axis", Toast.LENGTH_SHORT).show();
            }
        });

        // Listener for gyroscope
        gryscope.setListener(new Gryscope.Listener() {
            @Override
            public void onRotation(float rotate_x, float rotate_y, float rotate_z) {
                /*
                if(rotate_z > 1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
                else if(rotate_z < -1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);

                }
                 */
                Toast.makeText(MainActivity.this, rotate_x + "RX-axis", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, rotate_y + "RY-axis", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, rotate_z+ "RZ-axis", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Methods
    @Override
    protected void onResume(){
        super.onResume();

        accelerometer.register();
        gryscope.register();
    }

    @Override
    protected void onPause(){
        super.onPause();

        accelerometer.unregister();
        gryscope.unregister();
    }
}