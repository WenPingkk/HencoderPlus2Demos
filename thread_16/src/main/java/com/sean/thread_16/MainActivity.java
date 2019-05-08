package com.sean.thread_16;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sean.thread_16.thread.synchronize.Synchronized2Demo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Synchronized2Demo synchronized2Demo = new Synchronized2Demo();
        synchronized2Demo.TestDemo();
    }
}
