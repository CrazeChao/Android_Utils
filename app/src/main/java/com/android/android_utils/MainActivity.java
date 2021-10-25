package com.android.android_utils;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.utilslibrary.view.Span;
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

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void spanString(View view) {
        TextView textView = (TextView) view;
        //使用方式1
        Span span = new Span();
        span.addSpanString("第一个玩意").deleteLine();
        span.addSpanString("第二个玩意").absoluteSizeSpan(60);
        span.addSpanString("第三个玩意").scaleSpanX(1.3f);
        span.addSpanString("第四个玩意").typeFaceSpan(Typeface.DEFAULT_BOLD);

        Drawable draw = getDrawable(R.drawable.ic_launcher_foreground);
        draw.setBounds(0,0,100,100);
        span.addSpanString("图").imageSpan(draw);
        span.addSpanString("hello World!").setOnClick(textView,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Hello World!",1).show();
            }
        });
        textView.setText(span);


        //使用方式2
        Span span2 = new Span("没有关系我们只是朋友，所以没有分开的理由！ 激情四射");
        span2.describeSpan(0,1).deleteLine();
        span2.describeSpan("只是朋友").deleteLine();
        textView.setText(span2);

    }
}