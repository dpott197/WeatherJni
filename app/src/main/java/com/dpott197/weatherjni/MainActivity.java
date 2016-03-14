package com.dpott197.weatherjni;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private boolean mIsDeviceOpen;

    private TextView mAltitudeTextView;
    private TextView mHumidityTextView;
    private TextView mInfraredTextView;
    private TextView mPressureTextView;
    private TextView mTempTextView;
    private TextView mUltravioletTextView;
    private TextView mVisibleTextView;

    private CountDownTimer mRefreshTimer = new CountDownTimer(Integer.MAX_VALUE, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            updateUI();
        }

        @Override
        public void onFinish() {
            start();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateView();
    }

    private void onCreateView() {
        setContentView(R.layout.activity_main);

        mAltitudeTextView = (TextView) findViewById(R.id.activity_main_altitudeTextView);
        mHumidityTextView = (TextView) findViewById(R.id.activity_main_humidityTextView);
        mPressureTextView = (TextView) findViewById(R.id.activity_main_pressureTextView);
        mTempTextView = (TextView) findViewById(R.id.activity_main_tempTextView);

        mInfraredTextView = (TextView) findViewById(R.id.activity_main_infraredTextView);
        mUltravioletTextView = (TextView) findViewById(R.id.activity_main_ultravioletTextView);
        mVisibleTextView = (TextView) findViewById(R.id.activity_main_visibleTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WeatherUtils.forcePermission();

        mIsDeviceOpen = openDevice() < 0;
        openLightDevice();

        updateUI();
        mRefreshTimer.start();
    }

    private void updateUI() {
        if (mIsDeviceOpen) {
            return;
        }

        if (mAltitudeTextView != null) {
            mAltitudeTextView.setText(String.format(String.valueOf(getAltitude())));
        }

        if (mHumidityTextView != null) {
            mHumidityTextView.setText(String.format(String.valueOf(getHumidity())));
        }

        if (mPressureTextView != null) {
            mPressureTextView.setText(String.format(String.valueOf(getPressure())));
        }

        if (mTempTextView != null) {
            mTempTextView.setText(String.format(String.valueOf(getTemp())));
        }

        if (mInfraredTextView != null) {
            mInfraredTextView.setText(String.format(String.valueOf(getInfrared())));
        }

        if (mUltravioletTextView != null) {
            mUltravioletTextView.setText(String.format(String.valueOf(getUV())));
        }

        if (mVisibleTextView != null) {
            mVisibleTextView.setText(String.format(String.valueOf(getVisible())));
        }
    }

    static {
        System.loadLibrary("weatherboard-jni");
    }

    public native int openDevice();
    public native int openLightDevice();

    public native double getAltitude();
    public native double getHumidity();
    public native double getPressure();
    public native double getTemp();

    public native double getInfrared();
    public native double getUV();
    public native double getVisible();

}
