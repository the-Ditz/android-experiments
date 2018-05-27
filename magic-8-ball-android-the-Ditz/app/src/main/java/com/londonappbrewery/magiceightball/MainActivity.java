package com.londonappbrewery.magiceightball;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;

/**
 * Portions of code relating to accelerometer borrowed and adapted from:
 * https://stackoverflow.com/questions/2317428/android-i-want-to-shake-it
 */
public class MainActivity extends AppCompatActivity {

    public static final int IMAGE_COUNT = 5;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private int imageSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView fortuneView = findViewById(R.id.eight_ball_image);

        final int[] ballArray = {
                R.drawable.ball1,
                R.drawable.ball2,
                R.drawable.ball3,
                R.drawable.ball4,
                R.drawable.ball5
        };

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {

                final Random random =  new Random();

                imageSelection = ballArray[random.nextInt(IMAGE_COUNT)];
                Log.d("8Ball", "Image = " + imageSelection);

                fortuneView.setImageResource(imageSelection);
                Log.d("8Ball", "fortuneView = " + fortuneView);
            }
        });
    }

    @Override
    public void onResume() {

        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,    SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {

        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}