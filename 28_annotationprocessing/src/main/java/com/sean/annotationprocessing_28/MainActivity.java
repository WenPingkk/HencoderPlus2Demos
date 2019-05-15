package com.sean.annotationprocessing_28;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sean.a28_annotation.BindView;
import com.sean.a28_lib_android.Binding;

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
