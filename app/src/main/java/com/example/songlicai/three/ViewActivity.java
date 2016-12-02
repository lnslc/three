package com.example.songlicai.three;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ViewActivity extends Activity {


    private int index = 0;
    private int imgIndex[] = {R.drawable.icon_man_1, R.drawable.icon_man_2, R.drawable.icon_man_3, R.drawable.icon_man_4, R.drawable.icon_man_5, R.drawable.icon_man_6};
    private ImageView imageView = null;
    private Spinner spinner = null;
    private Spinner subSpinner = null;
    private ClickListener linstener = null;
    private CheckChangeListener checkChangeListener = null;
    final String[][] strSub = {
            {"11", "12", "13", "14"},
            {"21", "22", "23", "24", "24"},
            {"30", "31", "32", "33"},
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        linstener = new ClickListener();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapter.add("one");
        adapter.add("two");
        adapter.add("four");

        subSpinner = (Spinner)findViewById(R.id.subSpinner);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(getApplicationContext(), ">>>>"+position, Toast.LENGTH_SHORT).show();
                ArrayAdapter<String> subAdapter = new ArrayAdapter<String>(ViewActivity.this, android.R.layout.simple_spinner_item);
                subAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                for (int i = 0; i < strSub[position].length; i++)
                {
                    subAdapter.add(strSub[position][i]);
                }
                subSpinner.setAdapter(subAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        subSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        imageView = (ImageView)findViewById(R.id.image);
        ImageButton pre = (ImageButton)findViewById(R.id.pre);
        pre.setOnClickListener(linstener);

        ImageButton next = (ImageButton)findViewById(R.id.next);
        next.setOnClickListener(linstener);


        checkChangeListener = new CheckChangeListener();
        RadioButton rbtnMan = (RadioButton)findViewById(R.id.man);
        rbtnMan.setOnCheckedChangeListener(checkChangeListener);

        RadioButton rbtnWoman = (RadioButton)findViewById(R.id.woman);
        rbtnWoman.setOnCheckedChangeListener(checkChangeListener);

        RadioButton rbtnOther = (RadioButton)findViewById(R.id.other);
        rbtnOther.setOnCheckedChangeListener(checkChangeListener);

        RadioButton rbtnMore = (RadioButton)findViewById(R.id.more);
        rbtnMore.setOnCheckedChangeListener(checkChangeListener);

        CheckBox cbGood = (CheckBox)findViewById(R.id.good);
        cbGood.setOnCheckedChangeListener(checkChangeListener);

        CheckBox cbBad = (CheckBox)findViewById(R.id.bad);
        cbBad.setOnCheckedChangeListener(checkChangeListener);

    }

    private class ClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Log.e("ViewActivity", "id = "+v.getId());
            if(v.getId() == R.id.pre)
            {
                if (index > 0) {
                    index--;
                    imageView.setImageResource(imgIndex[index]);
                }
            }
            else if (v.getId() == R.id.next)
            {
                if(index < imgIndex.length-1)
                {
                    index++;
                    imageView.setImageResource(imgIndex[index]);
                }
            }
        }
    }

    private class CheckChangeListener implements CompoundButton.OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            String content = null;
            if (isChecked)
            {
                switch (buttonView.getId())
                {
                    case R.id.man: content = "选了男"; break;
                    case R.id.woman: content = "选了女"; break;
                    case R.id.other: content = "选了其他"; break;
                    case R.id.more: content = "选了更多"; break;
                    case R.id.good: content = "选中好"; break;
                    case R.id.bad: content = "选中坏"; break;
                }
            }
            else
            {
                switch (buttonView.getId())
                {
                    case R.id.good: content = "取消选中好"; break;
                    case R.id.bad: content = "取消选中坏"; break;
                }
            }
            if(content != null) {
                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
