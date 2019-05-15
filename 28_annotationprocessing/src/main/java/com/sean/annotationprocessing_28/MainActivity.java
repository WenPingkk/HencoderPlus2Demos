package com.sean.annotationprocessing_28;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sean.a28_lib_reflection.BindView;
import com.sean.a28_lib_reflection.Binding;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_view) TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Binding.bind(this);
        mTextView.setText("Sean....");
    }

}
