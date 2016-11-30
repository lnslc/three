package com.example.songlicai.three;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    }
}
