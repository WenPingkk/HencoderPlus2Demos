package com.sean.animation_08;


import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

//    CircleView view;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.view);
//        circleView();
//        camereView();
//        keyFrameFunction();
        pointViewFunction();
    }

    private void pointViewFunction() {
        Point point = new Point((int) Utils.dpToPixel(300), (int)Utils.dpToPixel(200));
        ObjectAnimator animator = ObjectAnimator.ofObject(view,"point",new PointEvalutor(),point);
        animator.setStartDelay(1000);
        animator.setDuration(1000);
        animator.start();
    }

    /**
     * TypeEvaluator 用法
     */
    class PointEvalutor implements TypeEvaluator<Point>{

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
/*
*             float x = startValue.x + (endValue.x - startValue.x) * fraction;
            float y = startValue.y + (endValue.y - startValue.y) * fraction;
            return new Point((int) x, (int) y);*/
            float x = startValue.x + (endValue.x - startValue.x) * fraction;
            float y = startValue.y + (endValue.y - startValue.y) * fraction;
            return new Point((int)x, (int)y);
        }
    }

    private void keyFrameFunction() {
/*        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX", Utils.dpToPixel(300));
        objectAnimator.setStartDelay(1000);
        objectAnimator.setDuration(2000);
        objectAnimator.start();*/

/*        float length = Utils.dpToPixel(300);
        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 0.2f*length);
        Keyframe keyframe3 = Keyframe.ofFloat(0.4f, 0.6f*length);
        Keyframe keyframe4 = Keyframe.ofFloat(1, 1f*length);
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframe("translationX", keyframe1, keyframe2, keyframe3, keyframe4);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, propertyValuesHolder);
        animator.setStartDelay(1000);
        animator.setDuration(2000);
        animator.start();*/
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

/*        PropertyValuesHolder bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip",45);
        PropertyValuesHolder flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation",270);
        PropertyValuesHolder topFlipHolder = PropertyValuesHolder.ofFloat("topFlip",-45);
        ObjectAnimator objectAnimator =  ObjectAnimator.ofPropertyValuesHolder(view,bottomFlipHolder, flipRotationHolder, topFlipHolder);
        objectAnimator.setStartDelay(1000);
        objectAnimator.setDuration(2000);
        objectAnimator.start();*/
    }

/*    private void circleView() {
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(view,"radius",Utils.dpToPixel(50),Utils.dpToPixel(100));
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }*/
}
