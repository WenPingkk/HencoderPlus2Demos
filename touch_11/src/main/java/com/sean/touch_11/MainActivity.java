package com.sean.touch_11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.sean.touch_11.views.TestView;

public class MainActivity extends AppCompatActivity {

    private String TAG = "mTag";
    TestView mTestView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestView = findViewById(R.id.view);
        mTestView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, MainActivity.class.getSimpleName() + ":setOnTouchListener:mTestView");
                return false;
            }
        });
        mTestView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, MainActivity.class.getSimpleName() + ":setOnClickListener:mTestView");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, MainActivity.class.getSimpleName() + ":onTouchEvent:" + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, MainActivity.class.getSimpleName() + ":dispatchTouchEvent:" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }
}
