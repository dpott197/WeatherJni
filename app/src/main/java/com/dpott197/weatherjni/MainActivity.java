package com.dpott197.weatherjni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.activity_main_textView);
        textView.setText(getTempFromJni());
    }

    static {
        System.loadLibrary("weatherboard-jni");
    }

    public native String getTempFromJni();

}
