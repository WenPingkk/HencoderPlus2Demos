package com.sean.animation_08;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sean.animation_08.views.CameraView;

public class MainActivity extends AppCompatActivity {

//    CircleView view;
    CameraView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.view);
//        circleView();
        camereView();
    }

    private void camereView() {

    }

/*    private void circleView() {
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(view,"radius",Utils.dpToPixel(50),Utils.dpToPixel(100));
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }*/
}
