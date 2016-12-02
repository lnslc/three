package com.example.songlicai.three;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class ScrollViewActivity extends Activity {

    final private String[] strAddress = {"网易：www.163.com", "新浪：www.sina.com.cn", "搜狐：www.sohu.com",
            "腾讯：www.qq.com", "百度：www.baidu.com", "东方财富：www.eastmoney.com",
            "金融界：www.jrj.com.cn", "奇艺：www.iqiyi.com", "携程网：www.ctrip.com","中国移动：www.10086.cn",
            "美食中国：www.meishichina.com","工商银行：www.icbc.com.cn","CSDN：www.csdn.net","跟我学：www.genwoxue.com" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        LinearLayout scrollLinear = (LinearLayout) findViewById(R.id.scrollLayout);
        for (int i = 0; i < strAddress.length; i++)
        {
            Button btnTmp = new Button(this);
            btnTmp.setText(strAddress[i]);
            scrollLinear.addView(btnTmp);
        }
    }
}
