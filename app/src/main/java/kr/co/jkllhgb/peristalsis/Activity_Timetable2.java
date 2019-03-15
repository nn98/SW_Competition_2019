package kr.co.jkllhgb.peristalsis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Activity_Timetable2 extends Activity {
    int[][] table;
    Field f;
    Object val;
    TextView[][] nowTable;
    Intent intent = getIntent();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable2);
        System.out.println("Print Test");
        R.id testing = new R.id();
        Class c = testing.getClass();
        String className = c.getName();
        // 레이아웃 pc들의 id 저장
        table = new int[5][7];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.println("table-"+table[i][j]);
            }
        }
        try {
            //load the AppTest at runtime
            Class cls = Class.forName(className);
            Object obj = cls.newInstance();
            //call the printIt method

            for (int i = 0; i < table.length; i++) {
                System.out.println("Rre-"+i);
                for (int j = 0; j < table[i].length; j++) {
                    System.out.println("re-"+j);
                    if (i == 0) {
                        f = cls.getDeclaredField("f" + Integer.toString(j + 1));
                        f.setAccessible(true);
                        val = f.get(obj);
                        table[i][j] = (Integer) val;

                    } else if (i == 1) {
                        f = cls.getDeclaredField("th" + Integer.toString(j + 1));
                        f.setAccessible(true);
                        val = f.get(obj);
                        table[i][j] = (Integer) val;

                    } else if (i == 2) {
                        f = cls.getDeclaredField("w" + Integer.toString(j + 1));
                        f.setAccessible(true);
                        val = f.get(obj);
                        table[i][j] = (Integer) val;

                    } else if (i == 3) {
                        f = cls.getDeclaredField("t" + Integer.toString(j + 1));
                        f.setAccessible(true);
                        val = f.get(obj);
                        table[i][j] = (Integer) val;

                    } else if (i == 4) {
                        f = cls.getDeclaredField("m" + Integer.toString(j + 1));
                        f.setAccessible(true);
                        val = f.get(obj);
                        table[i][j] = (Integer) val;

                    } else {
                        System.out.println("empty");
                    }
                    System.out.println("val-"+val);
                    System.out.println("id-"+R.id.f1);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.println("table-"+table[i][j]);
            }
        }
        //System.out.println(val);      확인용
        //System.out.println(R.id.pc1); 확인용
        //System.out.println(Arrays.toString(pcId)); 확인용
        TextView now = findViewById(table[0][0]);
        now.setText("dsfdsfds");
        TextView now0 = findViewById(table[0][2]);
        nowTable = new TextView[5][7];
        try {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss a");
            java.util.Locale.getDefault();
            String s = intent.getStringExtra("now");
            now.setText(time.parse(s).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
