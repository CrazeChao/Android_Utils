package com.android.android_utils;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.utilslibrary.view.touch.ClickAlphaAnimAdapter;
import com.android.utilslibrary.view.touch.ITouchAgent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ITouchAgent iTouchAgent = new ClickAlphaAnimAdapter(getWindow().getDecorView());
        getWindow().getDecorView().setOnTouchListener((v, event) -> {
            iTouchAgent.callOnTouchEvent(event);
            return true;
        });
    }
}