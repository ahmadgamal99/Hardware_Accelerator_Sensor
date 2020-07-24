package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    // there was a mistake in name of gryscope -> Gyroscope
    //  variables
    private Accelerometer accelerometer;
    private Gryscope gryscope;
    private static final String FILE_NAME = "Results.txt";
    private static final int PERMISSION_REQUEST_CODE = 100;

    TextView translation_x;
    TextView translation_y;
    TextView translation_z;

    TextView rotation_x;
    TextView rotation_y;
    TextView rotation_z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translation_x = (TextView) findViewById(R.id.translation_x);
        translation_y = (TextView) findViewById(R.id.translation_y);
        translation_z = (TextView) findViewById(R.id.translation_z);

        rotation_x = (TextView) findViewById(R.id.rotation_x);
        rotation_y = (TextView) findViewById(R.id.rotation_y);
        rotation_z = (TextView) findViewById(R.id.rotation_z);


        // Here Take Objects
        accelerometer = new Accelerometer(this);
        gryscope = new Gryscope(this);

        // Listener for accelerometer
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float last_x, float last_y, float last_z) throws IOException {
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

//                Toast.makeText(MainActivity.this, last_x + "X-axis", Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, last_y + "Y-axis", Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, last_z + "Z-axis", Toast.LENGTH_SHORT).show();


                translation_x.setText(last_x + "");
                translation_y.setText(last_x + "");
                translation_z.setText(last_x + "");

                String text = last_x + "," + last_y + "," + last_z + "\n";
                byte[] text_byte = text.getBytes();


                writeToFile(text_byte);
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
//                Toast.makeText(MainActivity.this, rotate_x + "RX-axis", Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, rotate_y + "RY-axis", Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, rotate_z+ "RZ-axis", Toast.LENGTH_SHORT).show();

                rotation_x.setText(rotate_x + "");
                rotation_y.setText(rotate_y + "");
                rotation_z.setText(rotate_z + "");

                String text = rotate_x + "," + rotate_y + "," + rotate_z + "\n";
                byte[] text_byte = text.getBytes();


                writeToFile(text_byte);

            }
        });
    }

    // Methods
    @Override
    protected void onResume() {
        super.onResume();

        accelerometer.register();
        gryscope.register();
    }

    @Override
    protected void onPause() {
        super.onPause();

        accelerometer.unregister();
        gryscope.unregister();
    }


    public void writeToFile(byte[] text){



        try {

            FileOutputStream fileOutputStream = openFileOutput("result.txt", MODE_PRIVATE);
            fileOutputStream.write(text);
            fileOutputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

