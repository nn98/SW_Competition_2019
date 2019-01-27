package kr.co.jkllhgb.peristalsis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//import android.view.WindowManager;
//import android.widget.LinearLayout;
//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//노티케이션 바 없애기 this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//뷰적용 this.setContentView(R.layout.activity_desktop);

public class activity_desktop extends Activity {
    TextView txtText;
    TextView title;
    //TextView now;
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate");
        //타이틀바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);

        //현재시간 출력
        //now = (TextView)findViewById(R.id.now);
        //ShowTimeMethod();
        // SimpleDateFormat sdf = new SimpleDateFormat("hh");

        //UI 객체생성
        title = (TextView)findViewById(R.id.title);
        txtText = (TextView)findViewById(R.id.txtText);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("pc_Number"));
        //데이터 가져오기
        //Intent intent = getIntent();
        //String data = intent.getStringExtra("data");
        //txtText.setText(data);

    }

    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        //Intent intent = new Intent();
        //intent.putExtra("result", "Close Popup");
        //setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기\
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

    /*
    public void ShowTimeMethod() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // DateFormat 세팅 - 변수생성 후 호출 or 핸들러 내부에서 생성
                now.setText(new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss a").format(new Date()));
            }
        };
        Runnable task = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {}
                    handler.sendEmptyMessage(1);    //핸들러를 호출한다. 즉, 시간을 최신화 해준다.
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
     */
}   //http://blog.naver.com/PostView.nhn?blogId=bho7982&logNo=220908514907&parentCategoryNo=&categoryNo=106&viewDate=&isShowPopularPosts=false&from=postView

    /* 현재시간 갱신용 메소드- 실패
    java.lang.NullPointerException: Attempt to invoke virtual method 'int android.text.Layout.getLineCount()' on a null object reference
        at android.widget.TextView.onMeasure(TextView.java:8628) ..... 오류 발생
    Date dt = new Date();
    Log.d("DATE",dt.toString());

    SimpleDateFormat full_sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss a");
    Log.d("DATE",full_sdf.format(dt).toString());

    SimpleDateFormat sdf = new SimpleDateFormat("hh");
    Log.d("DATE",sdf.format(dt).toString()+"시");
    class setDate extends Thread {
        @Override
        public void run() {
            while (!close) {
                dt = new Date();
                now.setText(full_sdf.format(dt).toString());
            }
        }
    }
     */
