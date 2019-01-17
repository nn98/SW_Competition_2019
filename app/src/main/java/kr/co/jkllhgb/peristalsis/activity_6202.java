package kr.co.jkllhgb.peristalsis;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Random;

public class activity_6202 extends Activity {
    String test;
    URLConnector task;
    int i = 42;
    Button btnarray[];
    int status[];
    int btncount = 0;
    Button btn_6202;
    LinearLayout layout;
    LinearLayout parent_layout;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layout6;
    LinearLayout layout7;
    LinearLayout layout8;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6202);

        test = "http://121.125.203.54/Connect1.php";
        task = new URLConnector(test);
        task.start();

        try {
            task.join();
            System.out.println("waiting... for result");
        } catch (InterruptedException e) {

        }

        String result = task.getResult();
        int stat=-1;
        try {
            JSONObject root=new JSONObject(result);

            JSONArray ja = root.getJSONArray("result");

            for(int i = 0; i < ja.length();i++) {
                JSONObject jo = ja.getJSONObject(i);
                stat = jo.getInt("PC_STATUS");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(result);

        String[] s;
        s=result.trim().split("");


        Random rnd = new Random();

        btnarray = new Button[42];
        status = new int[42];
        for (int i=1; i<43; i++){
            status[i-1] = Integer.parseInt(s[i]);
        }
        parent_layout = (LinearLayout) findViewById(R.id.parent_layout);
        layout = (LinearLayout) findViewById(R.id.createlayout);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);

        layout2 = new LinearLayout(this);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setGravity(Gravity.CENTER);
        layout3 = new LinearLayout(this);
        layout3.setOrientation(LinearLayout.HORIZONTAL);
        layout3.setGravity(Gravity.CENTER);
        layout4 = new LinearLayout(this);
        layout4.setOrientation(LinearLayout.HORIZONTAL);
        layout4.setGravity(Gravity.CENTER);
        layout5 = new LinearLayout(this);
        layout5.setOrientation(LinearLayout.HORIZONTAL);
        layout5.setGravity(Gravity.CENTER);
        layout6 = new LinearLayout(this);
        layout6.setOrientation(LinearLayout.HORIZONTAL);
        layout6.setGravity(Gravity.CENTER);
        layout7 = new LinearLayout(this);
        layout7.setOrientation(LinearLayout.HORIZONTAL);
        layout7.setGravity(Gravity.CENTER);
        layout8 = new LinearLayout(this);
        layout8.setOrientation(LinearLayout.HORIZONTAL);
        layout8.setGravity(Gravity.CENTER);
        btn_6202 = (Button) findViewById(R.id.btn_6202);
        btn_6202.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout.addView(layout2);
                layout.addView(layout3);
                layout.addView(layout4);
                layout.addView(layout5);
                layout.addView(layout6);
                layout.addView(layout7);
                layout.addView(layout8);


                setContentView(parent_layout);

                layout.setVisibility(View.VISIBLE);

                LinearLayout.LayoutParams miss = (LinearLayout.LayoutParams) btn_6202.getLayoutParams();
                miss.bottomMargin=0;
                miss.topMargin=0;

                btn_6202.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 80));
//                btn_6202.startAnimation(animation);
            }
        });
        for (; i > 36; i--) {
            Button b = new Button(this);
            b.setText("" + i);
            b.setId(i);
            b.setTextSize(10);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(70, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(2, 2, 2, 2);
            b.setLayoutParams(params);
            layout8.addView(b);
            if (i % 2 == 1) {
                TextView t = new TextView((this));
                layout8.addView(t);
            }
            btnarray[btncount] = b;
            pcstatus(btnarray[btncount], status[btncount]);
            btncount++;
            b.setGravity(1);
        }
        for (; i > 30; i--) {
            Button b = new Button(this);
            b.setText("" + i);
            b.setId(i);
            b.setTextSize(10);
            b.setGravity(1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(70, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(2, 2, 2, 2);
            b.setLayoutParams(params);
            layout7.addView(b);
            if (i % 2 == 1) {
                TextView t = new TextView((this));
                layout7.addView(t);
            }
            btnarray[btncount] = b;
            pcstatus(btnarray[btncount], status[btncount]);
            btncount++;
        }
        for (; i > 24; i--) {
            Button b = new Button(this);
            b.setText("" + i);
            b.setId(i);
            b.setTextSize(10);
            b.setGravity(1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(70, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(2, 2, 2, 2);
            b.setLayoutParams(params);
            layout6.addView(b);
            if (i % 2 == 1) {
                TextView t = new TextView((this));
                layout6.addView(t);
            }
            btnarray[btncount] = b;
            pcstatus(btnarray[btncount], status[btncount]);
            btncount++;
        }
        for (; i > 18; i--) {
            Button b = new Button(this);
            b.setText("" + i);
            b.setId(i);
            b.setTextSize(10);
            b.setGravity(1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(70, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(2, 2, 2, 2);
            b.setLayoutParams(params);
            layout5.addView(b);
            if (i % 2 == 1) {
                TextView t = new TextView((this));
                layout5.addView(t);
            }
            btnarray[btncount] = b;
            pcstatus(btnarray[btncount], status[btncount]);
            btncount++;
        }
        for (; i > 12; i--) {
            Button b = new Button(this);
            b.setText("" + i);
            b.setId(i);
            b.setTextSize(10);
            b.setGravity(1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(70, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(2, 2, 2, 2);
            b.setLayoutParams(params);
            layout4.addView(b);
            if (i % 2 == 1) {
                TextView t = new TextView((this));
                layout4.addView(t);
            }
            btnarray[btncount] = b;
            pcstatus(btnarray[btncount], status[btncount]);
            btncount++;
        }
        for (; i > 6; i--) {
            Button b = new Button(this);
            b.setText("" + i);
            b.setId(i);
            b.setTextSize(10);
            b.setGravity(1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(70, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(2, 2, 2, 2);
            b.setLayoutParams(params);
            layout3.addView(b);
            if (i % 2 == 1) {
                TextView t = new TextView((this));
                layout3.addView(t);
            }
            btnarray[btncount] = b;
            pcstatus(btnarray[btncount], status[btncount]);
            btncount++;
        }
        for (; i > 0; i--) {
            Button b = new Button(this);
            b.setText("" + i);
            b.setId(i);
            b.setTextSize(10);
            b.setGravity(1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(70, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(2, 2, 2, 2);
            b.setLayoutParams(params);
            layout2.addView(b);
            if (i % 2 == 1) {
                TextView t = new TextView((this));
                layout2.addView(t);
            }
            btnarray[btncount] = b;
            pcstatus(btnarray[btncount], status[btncount]);
            btncount++;
        }
    }

    public void pcstatus(Button b, int i){
        if (i <= 5){
            b.setBackgroundColor(Color.rgb(183,183,183));
        }
        else if (i >=8){
            b.setBackgroundColor(Color.rgb(247,208,236));
        }
    }
}
