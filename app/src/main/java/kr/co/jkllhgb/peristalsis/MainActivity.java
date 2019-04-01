/*
php - Connect.php
<?php
$conn = mysqli_connect("localhost", "root", "qq192837qq", "pj_pc");
$query = "select * from pc";
if($result = mysqli_query($conn, $query)){
    $row_num = mysqli_num_rows($result);
    echo "{";
        echo "\"status\":\"OK\",";
        echo "\"rownum\":\"$row_num\",";
        echo "\"result\":";
            echo "[";
                for($i = 0; $i < $row_num; $i++){
                    $row = mysqli_fetch_array($result);
                    echo "{";
                    echo "\"Num\":\"$row[PC_NUMBER]\", \"Status\":\"$row[PC_STATUS]\", \"Temp\":\"$row[PC_TEMP]\"";
                    echo "}";
                    if($i<$row_num-1){
                        echo ",";
                    }
                }
            echo "]";
    echo "}";
}
else{
    echo "failed to get data from database.";
}
?>
php - Connect1.php
<?php
$conn = mysqli_connect("localhost", "root", "qq192837qq", "pj_pc");
$query = "select * from pc";
if($result = mysqli_query($conn, $query)){
    $row_num = mysqli_num_rows($result);
            for($i = 0; $i < $row_num; $i++){
                $row = mysqli_fetch_array($result);
                echo "$row[PC_STATUS]";

                }
}
else{
    echo "failed to get data from database.";
}
?>
php - Connect2.php
<?php
$conn = mysqli_connect("localhost", "root", "qq192837qq", "pj_pc");
$query = "select * from user";
if($result = mysqli_query($conn, $query)){
    $row_num = mysqli_num_rows($result);
            for($i = 0; $i < $row_num; $i++){
                $row = mysqli_fetch_array($result);
                echo "$row[name],$row[depart],$row[code]";

                }
}
else{
    echo "failed to get data from database.";
}
?>
 */

package kr.co.jkllhgb.peristalsis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Enumeration;

public class MainActivity extends Activity implements View.OnClickListener {

    static String IP="http://172.30.3.55";

    private String TAG = "MainActivity";

    private Context mContext = MainActivity.this;

    private ViewGroup mainLayout;   //사이드 나왔을때 클릭방지할 영역
    private ViewGroup viewLayout;   //전체 감싸는 영역
    private ViewGroup sideLayout;   //사이드바만 감싸는 영역

    private Boolean isMenuShow = false;
    private Boolean isExitFlag = false;

    String test;
    URLConnector task;
    TextView user;
    TextView depart;
    TextView code;
    Button node;
    //TextView nodeS;
    int[] nodeStatus=new int[42];
    Intent intent;
    String[] userInfo;
    Button btn6202;
    Button btn6405;

    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("user_id", FirstActivity.id.getText());
                jsonObject.accumulate("user_pw", FirstActivity.pw.getText());

                HttpURLConnection con = null;
                BufferedReader reader = null;

                try {
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
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                    try {
                        if (reader != null) {
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
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String[] resultSet=result.replaceAll("\\[","").replaceAll("\\{","").replaceAll("\\}","").replaceAll("\\]","")
                    .replaceAll("\"","").replaceAll(","," ").replaceAll("pc_status:","").split(" ");
            //nodeS.setText(Arrays.toString(resultSet));
            for(int i=0;i<nodeStatus.length;i++) {
                nodeStatus[i]=Integer.parseInt(resultSet[i]);
            }

            //tvData.setText(Arrays.toString(resultSet));//서버로 부터 받은 값을 출력해주는 부
        }
    }

    /*
    public class JSONTask0 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("user_id", "androidTest");
                jsonObject.accumulate("name", "yun");

                HttpURLConnection con = null;
                BufferedReader reader = null;

                try {
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
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                    try {
                        if (reader != null) {
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
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("Execute");
            String[] resultSet = result.replaceAll("\\[", "").replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\]", "")
                    .replaceAll("\"", "").replaceAll(",", " ").replaceAll("user_name:", "").replaceAll("user_dept:", "").replaceAll("user_id:", "").split(" ");
            System.out.println(Arrays.toString(resultSet));
            userInfo = resultSet;

            //tvData.setText(Arrays.toString(resultSet));//서버로 부터 받은 값을 출력해주는 부
        }
    }
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

        if (isMenuShow) {
            closeMenu();
        } else {

            if (isExitFlag) {
                finish();
            } else {

                isExitFlag = true;
                Toast.makeText(this, "뒤로가기를 한번더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExitFlag = false;
                    }
                }, 2000);
            }
        }
    }

    /* 로그인용 노드 시도->실패

    class JSONTaskUser extends JSONTask {
        @Override
        public void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("Execute");
            String[] resultSet = result.replaceAll("\\[", "").replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\]", "")
                    .replaceAll("\"", "").replaceAll(",", " ").replaceAll("user_name:", "").replaceAll("user_dept:", "").replaceAll("user_id:", "").split(" ");
            System.out.println(Arrays.toString(resultSet));
            userInfo = resultSet;
        }
    }

    class OnJSON extends Thread {
        @Override
        public void run() {
            System.out.println("Run");
            new JSONTaskUser().execute("http://192.168.0.68:3000/user");
        }
    }

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Main");
        //nodeS=(TextView)findViewById(R.id.nodeS);
        //nodeStatus=new int[42];
        intent=getIntent();
        userInfo =intent.getStringArrayExtra("user");
        System.out.println("userInfo:"+Arrays.toString(userInfo));
        new JSONTask().execute(IP+":3000/post");
        //new JSONTask0().execute(IP+"3000/user");


        // IP 확인 ___ 애뮬레이터를 실행하는 pc의 IP가 아닌 애뮬레이터 자체의 IP를 받아오는듯.
        System.out.println(getLocalIpAddress());

        // user information 업로드용
        /*
        php part skip
        test = "http://172.30.3.48/Connect2.php";
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

        String[] userInfo=result.trim().split(",");
         */
        System.out.println(Arrays.toString(userInfo));
        user = (TextView) findViewById(R.id.user);
        user.setText(userInfo[0]);
        depart = (TextView) findViewById(R.id.depart);
        depart.setText(userInfo[1]);
        code = (TextView) findViewById(R.id.code);
        code.setText(userInfo[2]);


        init();
        addSideView();
        /*
        try {
            ip=getLocalHostLANAddress();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }//프로젝트에서 사용했던 getLH 시도- 실패. java.lang.RuntimeException: Unable to start activity ComponentInfo
         */
        final Intent intent1 = new Intent(this, NodeActivity.class);
        node = (Button) findViewById(R.id.node);
        node.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });
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

    private void init() {

        findViewById(R.id.btn_menu).setOnClickListener(this);

        mainLayout = findViewById(R.id.id_main);
        viewLayout = findViewById(R.id.fl_silde);
        sideLayout = findViewById(R.id.view_sildebar);

    }

    private void addSideView() {

        SideBarView sidebar = new SideBarView(mContext);
        sideLayout.addView(sidebar);

        viewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sidebar.setEventListener(new SideBarView.EventListener() {

            @Override
            public void btnCancel() {
                Log.e(TAG, "btnCancel");
                closeMenu();
            }

            @Override
            public void btnLevel1() {
                Log.e(TAG, "btnLevel1");

                closeMenu();
            }
        });
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_menu:

                showMenu();
                break;
        }
    }

    public void closeMenu() {

        isMenuShow = false;
        Animation slide = AnimationUtils.loadAnimation(mContext, R.anim.sidebar_hidden);
        sideLayout.startAnimation(slide);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewLayout.setVisibility(View.GONE);
                viewLayout.setEnabled(false);
                mainLayout.setEnabled(true);
            }
        }, 450);
    }

    public void showMenu() {

        isMenuShow = true;
        Animation slide = AnimationUtils.loadAnimation(this, R.anim.sidebar_show);
        sideLayout.startAnimation(slide);
        viewLayout.setVisibility(View.VISIBLE);
        viewLayout.setEnabled(true);
        mainLayout.setEnabled(false);
        Log.e(TAG, "메뉴버튼 클릭");
    }

    private static String getLocalHostLANAddress() throws UnknownHostException {
        InetAddress local = null;        //현재 컴퓨터 IP 받아오는 함수
        try {
            local = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ip = local.getHostAddress();
        return ip;
    }

    public void mOnPopupClick(View v) {
        //데이터 담아서 팝업(액티비티) 호출
        Intent intent = new Intent(this, activity_6202.class);
        intent.putExtra("status", nodeStatus);
        startActivityForResult(intent, 1);
    }
}

/*
json 형식으로 시도하는듯?

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {

    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADD = "address";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();
        getData("http://123.111.136.54/PHP_connection.php"); //수정 필요
    }


    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String id = c.getString(TAG_ID);
                String name = c.getString(TAG_NAME);
                String address = c.getString(TAG_ADD);

                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(TAG_ID, id);
                persons.put(TAG_NAME, name);
                persons.put(TAG_ADD, address);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, personList, R.layout.list_item,
                    new String[]{TAG_ID, TAG_NAME, TAG_ADD},
                    new int[]{R.id.id, R.id.name, R.id.address}
            );

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }


            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}
 */