package com.dpott197.weatherjni;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateView();
    }

    private void onCreateView() {
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.activity_main_textView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        if (openDevice() < 0) {
            return;
        }

        if (mTextView != null) {
            mTextView.setText(String.format(String.valueOf(getTemp())));
        }
    }

    static {
        System.loadLibrary("weatherboard-jni");
    }

    public native int openDevice();
    public native double getTemp();

}
