package com.example.sensor;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.appcompat.app.AppCompatActivity;

public class Gryscope {


    // Interface

    public interface Listener{
        void onRotation(float rotate_x, float rotate_y, float rotate_z);
    }

    // access l
    // Listener variable
    private Listener listener;

    public void setListener(Listener l)
    {
        listener = l;
    }

    // Variables

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    // Constructor
    Gryscope(Context context){

        // Sensors

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(listener != null){
                    listener.onRotation(sensorEvent.values[0],sensorEvent.values[1],sensorEvent.values[2]);

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }
    //
    public void register(){
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);


    }
    public void unregister(){
        sensorManager.unregisterListener(sensorEventListener);
    }
}
