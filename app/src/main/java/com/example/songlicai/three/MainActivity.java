package com.example.songlicai.three;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Button btnLinerLayout = (Button) findViewById(R.id.linerLayout);

        btnLinerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LinearLayoutActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button btnFrameLayout = (Button) findViewById(R.id.frameLayout);

        btnFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FrameLayoutActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button btnTableLayout = (Button)findViewById(R.id.tableLayout);
        btnTableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TableLayoutActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button btnGridLayout = (Button)findViewById(R.id.gridLayout);
        btnGridLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, GridLayoutActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button btnView = (Button)findViewById(R.id.view);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ViewActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });

        Button btnScrollView = (Button) findViewById(R.id.scrollView);
        btnScrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ScrollViewActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button btnDialog = (Button) findViewById(R.id.dialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DialogActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }
}
