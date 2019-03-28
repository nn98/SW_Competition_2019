package kr.co.jkllhgb.peristalsis;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Activity_Timetable2 extends Activity {
    int active = -1, lab = -1;
    int[] day;
    int[][] table;
    TextView[] nowDay;
    TextView[][] nowTable;
    TextView now;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable2);
        //현재시간 받아오기 확인
        //Intent intent=getIntent();
        //System.out.println(intent.getStringExtra("now"));
        R.id testing = new R.id();
        Class c = testing.getClass();
        String className = c.getName();
        // 레이아웃 시간표 TV들의 id 저장
        day = new int[5];
        nowDay = new TextView[5];
        table = new int[5][7];
        nowTable = new TextView[5][7];
        try {
            //load the AppTest at runtime
            Class cls = Class.forName(className);
            Object obj = cls.newInstance();
            Field f;
            Object val;
            for (int i = 0; i < day.length; i++) {
                f = cls.getDeclaredField("d" + Integer.toString(i + 1));
                f.setAccessible(true);
                val = f.get(obj);
                day[i] = (Integer) val;
                nowDay[i] = (TextView) findViewById((Integer) val);
            }
            //call the printIt method
            for (int i = 0; i < table.length; i++) {
                System.out.println("Rre-" + i);
                for (int j = 0; j < table[i].length; j++) {
                    System.out.println("re-" + j);
                    if (i == 0) {
                        if (j > 4) continue;
                        f = cls.getDeclaredField("f" + Integer.toString(j + 1));
                        f.setAccessible(true);
                        val = f.get(obj);
                        table[i][j] = (Integer) val;
                        nowTable[i][j] = (TextView) findViewById((Integer) val);

                    } else if (i == 1) {
                        f = cls.getDeclaredField("th" + Integer.toString(j + 1));
                        f.setAccessible(true);
                        val = f.get(obj);
                        table[i][j] = (Integer) val;
                        nowTable[i][j] = (TextView) findViewById((Integer) val);

                    } else if (i == 2) {
                        f = cls.getDeclaredField("w" + Integer.toString(j + 1));
                        f.setAccessible(true);
                        val = f.get(obj);
                        table[i][j] = (Integer) val;
                        nowTable[i][j] = (TextView) findViewById((Integer) val);

                    } else if (i == 3) {
                        f = cls.getDeclaredField("t" + Integer.toString(j + 1));
                        f.setAccessible(true);
                        val = f.get(obj);
                        table[i][j] = (Integer) val;
                        nowTable[i][j] = (TextView) findViewById((Integer) val);

                    } else if (i == 4) {
                        f = cls.getDeclaredField("m" + Integer.toString(j + 1));
                        f.setAccessible(true);
                        val = f.get(obj);
                        table[i][j] = (Integer) val;
                        nowTable[i][j] = (TextView) findViewById((Integer) val);

                    } else {
                        System.out.println("empty");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.println("table-" + table[i][j]);
            }
        }
        // 현재 요일 확인 후 레이아웃 색 변경
        DateFormat d1 = new SimpleDateFormat("EEE");
        DateFormat d2 = new SimpleDateFormat("EEE HH:mm");
        d1.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        d2.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String date = d1.format(Calendar.getInstance().getTime());
        System.out.println(date);
        switch (date) {
            case "Mon":
                active = 4;
                nowDay[0].setBackgroundColor(Color.rgb(255, 255, 255));
                nowDay[0].setTextColor(Color.rgb(0, 0, 0));
                break;
            case "Tue":
                active = 3;
                nowDay[1].setBackgroundColor(Color.rgb(255, 255, 255));
                nowDay[1].setTextColor(Color.rgb(0, 0, 0));
                break;
            case "Wed":
                active = 2;
                nowDay[2].setBackgroundColor(Color.rgb(255, 255, 255));
                nowDay[2].setTextColor(Color.rgb(0, 0, 0));
                break;
            case "Thu":
                active = 1;
                nowDay[3].setBackgroundColor(Color.rgb(255, 255, 255));
                nowDay[3].setTextColor(Color.rgb(0, 0, 0));
                break;
            case "Fri":
                active = 0;
                nowDay[4].setBackgroundColor(Color.rgb(255, 255, 255));
                nowDay[4].setTextColor(Color.rgb(0, 0, 0));
                break;
            case "Sat":
                active=4;
                nowDay[0].setBackgroundColor(Color.rgb(255, 255, 255));
                nowDay[0].setTextColor(Color.rgb(0, 0, 0));
                break;
            case "Sun":
                active=4;
                nowDay[0].setBackgroundColor(Color.rgb(255, 255, 255));
                nowDay[0].setTextColor(Color.rgb(0, 0, 0));
                break;
        }
        // 현재시간 위젯
        TextView now = findViewById(R.id.now);
        now.setText(d2.format(new Date()));

        // 시간표 TV 시간 저장
        try {
            DateFormat inNow0 = new SimpleDateFormat("hh:mm", java.util.Locale.getDefault());
            inNow0.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            long inTime = System.currentTimeMillis();
            System.out.println(inTime);
            //Date inNow = new Date(inTime);
            Date inNow=new Date();
            System.out.println("inNow : "+inNow);
            System.out.println(inNow0);
            System.out.println("inNow form : "+inNow0.format(inNow));
            Date m1 = inNow0.parse("05:00");
            System.out.println(m1);
            System.out.println(m1.getTime());
            System.out.println(inNow.getTime());
            System.out.println(inNow0.format(m1));
            System.out.println(inNow0.format(m1).compareTo(inNow0.format(inNow)));
            /* Compare Default
            if(inNow0.format(inNow0.parse("05:00")).compareTo(inNow0.format(inNow))<0 && inNow0.format(inNow0.parse("09:00")).compareTo(inNow0.format(inNow))>0) {
                System.out.println("현재시간 5~9 사이");
                if (inNow0.format(inNow0.parse("09:59")).compareTo(inNow0.format(inNow))<0 && inNow0.format(inNow0.parse("12:00")).compareTo(inNow0.format(inNow))>0) {
                System.out.println("fri 1");
            } else
            }
             */
            if (inNow0.format(inNow0.parse("08:59")).compareTo(inNow0.format(inNow)) < 0 && inNow0.format(inNow0.parse("10:30")).compareTo(inNow0.format(inNow)) > 0) {
                System.out.println("lab 1");
                lab=0;
            } else if (inNow0.format(inNow0.parse("10:29")).compareTo(inNow0.format(inNow)) < 0 && inNow0.format(inNow0.parse("12:00")).compareTo(inNow0.format(inNow)) > 0) {
                System.out.println("lab 2");
                lab=1;
            } else if (inNow0.format(inNow0.parse("11:59")).compareTo(inNow0.format(inNow)) < 0 && inNow0.format(inNow0.parse("13:30")).compareTo(inNow0.format(inNow)) > 0) {
                System.out.println("lab 3");
                lab=2;
            } else if (inNow0.format(inNow0.parse("13:29")).compareTo(inNow0.format(inNow)) < 0 && inNow0.format(inNow0.parse("15:00")).compareTo(inNow0.format(inNow)) > 0) {
                System.out.println("lab 4");
                lab=3;
            } else if (inNow0.format(inNow0.parse("14:59")).compareTo(inNow0.format(inNow)) < 0 && inNow0.format(inNow0.parse("16:30")).compareTo(inNow0.format(inNow)) > 0) {
                System.out.println("lab 5");
                lab=4;
            } else if (inNow0.format(inNow0.parse("16:29")).compareTo(inNow0.format(inNow)) < 0 && inNow0.format(inNow0.parse("18:00")).compareTo(inNow0.format(inNow)) > 0) {
                System.out.println("lab 6");
                lab=5;
            } else if (inNow0.format(inNow0.parse("18:24")).compareTo(inNow0.format(inNow)) < 0 && inNow0.format(inNow0.parse("19:11")).compareTo(inNow0.format(inNow)) > 0) {
                System.out.println("lab 7");
                lab=6;
            }
            TextView activeTable=(TextView)findViewById(table[active][lab]);
            activeTable.setBackgroundColor(Color.rgb(255,255,255));
            activeTable.setTextColor(Color.rgb(0,0,0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(val);      확인용
        //System.out.println(R.id.pc1); 확인용
        //System.out.println(Arrays.toString(pcId)); 확인용
        /*
        try {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss a");
            java.util.Locale.getDefault();
            String s = intent.getStringExtra("now");
            now.setText(time.parse(s).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
    }
}
