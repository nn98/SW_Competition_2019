# PHP_Stud_App00
testing success

* * *
  - 19-01-05 00:53 프로젝트 데이터베이스-안드로이드 연동 성공- 콘솔 출력 성공- 레이아웃에 resultSet add 후 출력 성공.
  - 19-01-17 23:41~ 
  - ~ 19-01-18 00:42 
    - 기존 프로젝트 시연까지의 과정 새롭게 구현. Node.js 사용한 부분 php 활용한 json 인코딩, 디코딩 파싱 연습.
    - 레이아웃 등 부가적 구성 이외의 실질적 어플리케이션 기능 구현 완료.
  - 19-01-21 05:00 기존 기능 전체 재구현. 리프레시 기능 추가 구현 과정에서 스레드 조작에 난항.
<details><summary>재구현 중점</summary><div markdown="1">

|문제점|해결방식|
|--|--|
|자바 코드에서 생성된 버튼 조작 어려움|xml에서 버튼 생성-자바 코드에서 R, R.id 활용해 버튼 정의 후 조작|
|새로고침 기능 부적절|데이터 커넥팅 스레드로 구현, 스레드 활용한 새로고침 기능 구현---스레드 실행, 정지 기능 활용 미흡|
```java
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
```
총 42개의 버튼 , 분단 구별을 위한 공백 텍스트뷰 혹은 Margin값 생성.

재구현 코드
```html
<Button
                android:id="@+id/pc6"
                android:layout_width="15pt"
                android:layout_height="wrap_content"
                android:text="pc_6"
                android:layout_weight="1"
                android:textSize="5pt"/>
```
```java
for(;i<42;i++) {
            pcA[i]=(Button)findViewById(pcId[i]);
            statusA[i]=Integer.parseInt(s[i+1]);
            pcstatus(pcA[i],statusA[i]);
            pcA[i].setOnClickListener(new View.OnClickListener() {
                final int j=i;
                @Override
                public void onClick(View v) {
                    intent.putExtra("pc_Number",pcA[j].getText());
                    startActivityForResult(intent, 1);
                }
            });
        }
```
변환 필요점
```java
int i;
/
/
/
i=0;
        for(;i<42;i++) {
            pcA[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("pc_Number",pcA[i].getText());
                    startActivityForResult(intent, 1);
                }
            });
        }
해당 코드의 문제는 onClickListener의 정의에 사용한 i로 인해 발생. 

"Variable '...' is accessed from within inner class, needs to be declared final"
런타임 에러를 회피하기 위해 outer class 변수 i 사용, 위와 같은 코드로
pcA 배열 버튼들의 onClickListener를 정의한 경우 모든 pcA 배열 버튼들은
클릭 이벤트가 발생할 경우 현재 i의 값을 호출해 이벤트 처리.
위의 코드를 실행할 경우 i의 값은 42.
이 경우 모든 pcA 배열의 버튼들은 클릭될 경우 pcA[42].getText()를 실행하게 됨.

sol 1-외부 메소드 활용
public void popUpClick(Button b) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("pc_Number",b.getText());
                startActivityForResult(intent, 1);
            }
        });
    }
과 같이 개별 버튼이나 버튼 배열을 메소드 매개변수로 보내 온클릭리스너 정의

sol 2-각각 새로운 final 변수 활용
for(;i<42;i++) {
            pcA[i]=(Button)findViewById(pcId[i]);
            statusA[i]=Integer.parseInt(s[i+1]);
            pcstatus(pcA[i],statusA[i]);
            pcA[i].setOnClickListener(new View.OnClickListener() {
                final int j=i;
                @Override
                public void onClick(View v) {
                    intent.putExtra("pc_Number",pcA[j].getText());
                    startActivityForResult(intent, 1);
                }
            });
        }
        
/*등 다양한 시도를 해봤지만 실패. 간단한 해답이 있을듯도 하지만 현재로썬
각 버튼들에 각자의 온클릭리스너를 정의해주는 방법밖엔 없는듯*/
헛지랄이였고 코드 잘못써서 안된거.
sol 2 방법으로 구현.
__버튼을 this로 삼는 리스너 정의?__ inner와 outter 개념을 제대로 알아둬야 
```
</div>
</details>
<details><summary>참고 자료</summary>
  
  - [버튼 클릭이벤트 재구현](http://jizard.tistory.com/9) , [버튼 클릭이벤트 재구현2](https://medium.com/@henen/%EB%B9%A0%EB%A5%B4%EA%B2%8C-%EB%B0%B0%EC%9A%B0%EB%8A%94-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-clickevent%EB%A5%BC-%EB%A7%8C%EB%93%9C%EB%8A%94-3%EA%B0%80%EC%A7%80-%EB%B0%A9%EB%B2%95-annoymous-class-%EC%9D%B5%EB%AA%85-%ED%81%B4%EB%9E%98%EC%8A%A4-implements-1b1fbe1a74c0)
  
  
  - __[Reflection 활용한 메소드 가져오기](http://www.mkyong.com/java/how-to-use-reflection-to-call-java-method-at-runtime/),[필드 조작에 사용](https://code.i-harness.com/ko-kr/q/cc780b),[문법 참](http://darkhorizon.tistory.com/290)__

  - [스레드 조작](https://code.i-harness.com/ko-kr/q/1cb672)
  
  - [레이아웃 스타일 활용](http://itpangpang.xyz/289) 이외에도 직접 만든 스타일 개별 적용이나 테마 짜집기 시도해봤으나 실패.
  
  - [인텐트, 액티비티 추가](http://whatisthenext.tistory.com/64) , [인텐트 데이터 전송](https://lx5475.github.io/2016/01/27/android-intent/)

  </ditails>
<details><summary>오류 목록</summary>
  
  __Application Installation Failed-__
  
  Installation failed with message Failed to establish session.
  
  It is possible that this issue is resolved by uninstalling an existing version of the apk 
  
  if it is present, and then re-installing.
  
  WARNING: Uninstalling will remove the application data!

__Solve-__ _http://codedragon.tistory.com/7837_

Step 1: 디바이스 종료

Step 2: AndroidStduio에서 아래와 같이 수행합니다.

메뉴: [Build] >> [Clean Project] >>

메뉴: [Build] >> [ReBuild Project] >>

메뉴: [Build] >> [Build APK(s)] >>

Run

</details>
