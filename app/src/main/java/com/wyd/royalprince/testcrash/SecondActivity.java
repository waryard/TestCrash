package com.wyd.royalprince.testcrash;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class SecondActivity extends AppCompatActivity {

    Context secondActivity;
    String a = "aaa1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        secondActivity = this;
        findViewById(R.id.test_bug_second).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int b = Integer.parseInt(a);
            }
        });
    }
}
