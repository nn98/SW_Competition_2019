package kr.co.jkllhgb.peristalsis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

//import android.view.WindowManager;
//import android.widget.LinearLayout;
//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//노티케이션 바 없애기 this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//뷰적용 this.setContentView(R.layout.activity_desktop);

public class activity_desktop extends Activity {
    TextView txtText;
    TextView title;
    protected void onCreate(Bundle savedInstanceState) {
        //타이틀바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);

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

        //액티비티(팝업) 닫기
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
}