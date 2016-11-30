package com.example.songlicai.three;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ViewActivity extends Activity {


    private int index = 0;
    private int imgIndex[] = {R.drawable.icon_man_1, R.drawable.icon_man_2, R.drawable.icon_man_3, R.drawable.icon_man_4, R.drawable.icon_man_5, R.drawable.icon_man_6};
    private ImageView imageView = null;
    private Spinner spinner = null;
    private ClickListener linstener = null;
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
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "===>"+id, Toast.LENGTH_LONG).show();
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
}
