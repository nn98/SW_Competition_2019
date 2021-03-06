package kr.co.jkllhgb.peristalsis;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;
import java.util.TimeZone;

public class activity_6202 extends Activity {
    /*  시연당시 어플리케이션 코드
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
    Intent intent;
     */

    /*  레이아웃 id값 직접 추출(String 으로 변수 이름 표현)을 위한 reflect 연습- 실패 코드
    protected static String aaa = "Plz";

    private static activity_6202 t_instance;

    public static activity_6202 getInstance() throws Exception {
        t_instance = new activity_6202();
        return t_instance;
    }

    public static Object getProperty(String key) throws Exception {
        return t_instance.getClass().getDeclaredField(key).get(t_instance); // key 값으로 aaa 가 들어오고 return은 Plz 가 필요함.
    }
    public static void main(String[] args) {

    }
     */
    TextView now;
    String test;
    URLConnector task;
    Button refresh;
    Button[] pcA;
    int[] pcId;
    int[] statusA;
    int[] statusB;
    Intent intent;
    int i = 0;
    int j = 0;
    TextView node;
    int[] nodeStatus;
    ProgressDialog dialog;
    TextView lec;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6202);
        dialog = new ProgressDialog(activity_6202.this);
        Intent nIntent = getIntent();
        statusB = nIntent.getIntArrayExtra("status");
        System.out.println(Arrays.toString(statusB));
        node = (TextView) findViewById(R.id.node);
        now = (TextView) findViewById(R.id.now);
        ShowTimeMethod();
        // R을 활용한 id값 직접 추출을 위한 코드 연습----------------
        System.out.println("Print Test");
        R.id testing = new R.id();
        Class c = testing.getClass();
        String className = c.getName();

        //php->node
        nodeStatus=new int[42];
        new JSONTask().execute(MainActivity.IP+":3000/post");
        /*
        Class noparams[] = {};
        //String parameter
        Class[] paramString = new Class[1];
        paramString[0] = String.class;
        //int parameter
        Class[] paramInt = new Class[1];
        paramInt[0] = Integer.TYPE;
         */
        // 레이아웃 pc들의 id 저장
        pcId = new int[42];
        try {
            //load the AppTest at runtime
            Class cls = Class.forName(className);
            Object obj = cls.newInstance();
            //call the printIt method
            for (int i = 1; i < 43; i++) {
                Field f = cls.getDeclaredField("pc" + Integer.toString(i));
                f.setAccessible(true);
                Object val = f.get(obj);
                pcId[i - 1] = (Integer) val;
            }

            //System.out.println(val);      확인용
            //System.out.println(R.id.pc1); 확인용
            //System.out.println(Arrays.toString(pcId)); 확인용
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //------------------------------------------------------- 까지. 성공.
        refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        refresh.setText("Refreshing...");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        refresh.setText("Refreshing...");
                        break;
                    case MotionEvent.ACTION_UP:
                        refresh.setText("Refreshing...");
                        break;
                }
                return false;
            }
        });

        /*
        test = "http://123.111.136.92/Connect1.php";
        task = new URLConnector(test);
        task.start();
        try {
            task.join();
            System.out.println("waiting... for result");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = task.getResult();
        task.interrupt();

        System.out.println(result);
        String[] s;
        s = result.trim().split("");

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
         */

        pcA = new Button[42];
        statusA = new int[42];
        intent = new Intent(this, activity_desktop.class);

        for (; i < 42; i++) {
            pcA[i] = (Button) findViewById(pcId[i]);
            // statusA[i] = Integer.parseInt(s[i + 1]);
            pcstatus(pcA[i], statusB[i]);
            pcA[i].setOnClickListener(new View.OnClickListener() {
                final int j = i;
                @Override
                public void onClick(View v) {
                    intent.putExtra("pc_Number", pcA[j].getText());
                    intent.putExtra("pc_Status", nodeStatus[j] >= 8);
                    startActivityForResult(intent, 1);
                }
            });
        }
        //System.out.println(Arrays.toString(statusA));
        /* 가장 큰 문제점 -------------------------------------------------------


        public void popUpClick(Button b) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("pc_Number",b.getText());
                    startActivityForResult(intent, 1);
                }
            });
        } ------------------------------------------------------------------------
         */
        // popUpSet(); 헛짓
        /*
        pcA[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pcA[5].setText("Suc");
            }
        }); 테스트용 코드
         */
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 0;
                // php refresh->node
                new JSONTask().execute(MainActivity.IP+":3000/post");
                System.out.println(Arrays.toString(nodeStatus));
                for (; i < 42; i++) {
                    pcstatus(pcA[i], nodeStatus[i]);
                }
                refresh.setText("REFRESH");
            }
        });
        /* 시연당시 어플리케이션 코드
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
            intent=new Intent(this,activity_desktop.class);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(intent,1);
                }
            });
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
         */
        // PingTest 수정 :
        SimpleDateFormat onTime = new SimpleDateFormat("mm-dd hh:mm a");
        onTime.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        // pc_status++; ?
    }

    public void mOnPopupClick(View v) {
        //데이터 담아서 팝업(액티비티) 호출
        System.out.println("PopupClick");
        Intent intent = new Intent(this, Activity_Timetable2.class);
        /*
        long now=System.currentTimeMillis();
        Date date=new Date(now);
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss a");
        s.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String r=s.format(date);
        intent.putExtra("now",r);
         */
        startActivityForResult(intent, 1);
    }

    // 내가 현재 부여받은 네트워크의 아이피를 보려고 할 때
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void ShowTimeMethod() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // DateFormat 세팅 - 변수생성 후 호출 or 핸들러 내부에서 생성
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss a");
                s.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
                now.setText(s.format(new Date()));
                //now.setText(new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss a", java.util.Locale.getDefault()).format(new Date()));
            }
        };
        Runnable task = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                    handler.sendEmptyMessage(1);    //핸들러를 호출한다. 즉, 시간을 최신화 해준다.
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    public void popUpSet() {
        // 헛지랄이였네?
        int k = 0;
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 1);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 2);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 3);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 4);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 5);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 6);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 7);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 8);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 9);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 10);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 11);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 12);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 13);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 14);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 15);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 16);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 17);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 18);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 19);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 20);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 21);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 22);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 23);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 24);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 25);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 26);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 27);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 28);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 29);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 30);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 31);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 32);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 33);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 34);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 35);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 36);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 37);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 38);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 39);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 40);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 41);
                startActivityForResult(intent, 1);
            }
        });
        pcA[k++].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number", 42);
                startActivityForResult(intent, 1);
            }
        });
    }

    public void pcstatus(Button b, int i) {
        if (i <= 5) {
            b.setBackground(getResources().getDrawable(R.drawable.pc_off));
        } else if (i >= 8) {
            b.setBackground(getResources().getDrawable(R.drawable.pc_on));
        }
    }

    //php refresh->node
    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                JSONObject jsonObject = new JSONObject();
                //jsonObject.accumulate("user_id", "androidTest");
                //jsonObject.accumulate("name", "yun");

                HttpURLConnection con = null;
                BufferedReader reader = null;

                try{
                    //URL url = new URL("http://192.168.25.16:3000/users");
                    URL url = new URL(urls[0]);
                    //연결을 함
                    con = (HttpURLConnection) url.openConnection();

                    con.setRequestMethod("POST");//POST방식으로 보냄
                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                    con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
                    con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
                    con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                    con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                    con.connect();

                    //서버로 보내기위해서 스트림 만듬
                    OutputStream outStream = con.getOutputStream();
                    //버퍼를 생성하고 넣음
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(jsonObject.toString());
                    writer.flush();
                    writer.close();//버퍼를 받아줌

                    //서버로 부터 데이터를 받음
                    InputStream stream = con.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while((line = reader.readLine()) != null){
                        buffer.append(line);
                    }

                    return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임

                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(con != null){
                        con.disconnect();
                    }
                    try {
                        if(reader != null){
                            reader.close();//버퍼를 닫아줌
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Refreshing...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String[] resultSet=result.replaceAll("\\[","").replaceAll("\\{","").replaceAll("\\}","").replaceAll("\\]","")
                    .replaceAll("\"","").replaceAll(","," ").replaceAll("pc_status:","").split(" ");
            //nodeS.setText(Arrays.toString(resultSet));
            for(int i=0;i<nodeStatus.length;i++) {
                nodeStatus[i]=Integer.parseInt(resultSet[i]);
            }
            dialog.dismiss();
            //tvData.setText(Arrays.toString(resultSet));//서버로 부터 받은 값을 출력해주는 부
        }
    }
}
