package com.sean.animation_08;


import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
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
/*        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator bottomFlip = ObjectAnimator.ofFloat(view, "bottomFlip", 45);
        bottomFlip.setDuration(1500);
        ObjectAnimator flipRotation = ObjectAnimator.ofFloat(view, "flipRotation",  270);
        flipRotation.setDuration(1500);
        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", -45);
        topFlipAnimator.setDuration(1500);
        animatorSet.playSequentially(bottomFlip,flipRotation,topFlipAnimator);
        animatorSet.setDuration(3000);
        animatorSet.start();*/
        PropertyValuesHolder bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip",45);
        PropertyValuesHolder flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation",270);
        PropertyValuesHolder topFlipHolder = PropertyValuesHolder.ofFloat("topFlip",-45);
        ObjectAnimator objectAnimator =  ObjectAnimator.ofPropertyValuesHolder(view,bottomFlipHolder, flipRotationHolder, topFlipHolder);
        objectAnimator.setStartDelay(1000);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }

/*    private void circleView() {
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(view,"radius",Utils.dpToPixel(50),Utils.dpToPixel(100));
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }*/
}
