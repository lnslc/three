package com.example.songlicai.three;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DialogActivity extends Activity {

    private ClickListener clickListener = null;
    private int nCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        clickListener = new ClickListener();

        Button btnNoBtn = (Button)findViewById(R.id.noButton);
        btnNoBtn.setOnClickListener(clickListener);

        Button btnTwoBtn = (Button)findViewById(R.id.twoButton);
        btnTwoBtn.setOnClickListener(clickListener);

        Button btnThreeBtn = (Button)findViewById(R.id.threeButton);
        btnThreeBtn.setOnClickListener(clickListener);

        Button btnSingle = (Button)findViewById(R.id.single);
        btnSingle.setOnClickListener(clickListener);

        Button btnMulti = (Button)findViewById(R.id.multi);
        btnMulti.setOnClickListener(clickListener);

        Button btnProgress = (Button)findViewById(R.id.progress);

        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pDialog = new ProgressDialog(DialogActivity.this);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setTitle("长长长");
                pDialog.setMessage("加载中...");
                pDialog.setProgress(100);
                pDialog.setCancelable(false);
                pDialog.show();

                new Thread()
                {
                    public void run()
                    {
                        while (nCount <= 100)
                        {
                            nCount++;
                            pDialog.setProgress(nCount);
                            try
                            {
                                Thread.sleep(50);

                            }
                            catch (Exception e)
                            {

                            }
                        }
                        nCount = 0;
                        pDialog.cancel();
                    }
                }.start();
            }
        });



        Button btnCircle = (Button)findViewById(R.id.circle);
        btnCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pDialog = new ProgressDialog(DialogActivity.this);
                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pDialog.setTitle("转转转");
                pDialog.setMessage("加载中...");
                pDialog.setProgress(100);
                pDialog.setCancelable(false);
                pDialog.show();

                new Thread()
                {
                    public void run()
                    {
                        while (nCount <= 100)
                        {
                            nCount++;
                            pDialog.setProgress(nCount);
                            try
                            {
                                Thread.sleep(50);

                            }
                            catch (Exception e)
                            {

                            }
                        }
                        nCount = 0;
                        pDialog.cancel();
                    }
                }.start();
            }
        });
    }

    private class ClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DialogActivity.this);
            alertDialog.setTitle("抬头");
            if(v.getId() == R.id.noButton)
            {
                alertDialog.setMessage("我只有内容，没有按钮!");
            }
            else if(v.getId() == R.id.twoButton)
            {
                alertDialog.setMessage("我是内容，我有两按钮!");
                alertDialog.setPositiveButton("确定", null);
                alertDialog.setNegativeButton("取消", null);
            }
            else if(v.getId() == R.id.threeButton)
            {
                alertDialog.setMessage("我是内容，我有三按钮!");
                alertDialog.setPositiveButton("确定", null);
                alertDialog.setNegativeButton("取消", null);
                alertDialog.setNeutralButton("中间", null);
            }
            else if(v.getId() == R.id.single)
            {
                String[] strTmp = {"11", "12", "13", "14"};
                alertDialog.setSingleChoiceItems(strTmp, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                });
                alertDialog.setPositiveButton("确定", null);
                alertDialog.setNegativeButton("取消", null);
            }
            else if(v.getId() == R.id.multi)
            {
                String[] strTmp = {"11", "12", "13", "14"};
                alertDialog.setMultiChoiceItems(strTmp, null, null);
                alertDialog.setPositiveButton("确定", null);
                alertDialog.setNegativeButton("取消", null);
            }
            alertDialog.create();
            alertDialog.show();
        }
    }
}
