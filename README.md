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
|--|--|
|기존 코드|```java
Button b = new Button(this);
            b.setText("" + i);
            b.setId(i);
            b.setTextSize(10);
            b.setGravity(1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(140,140);
            params.setMargins(2, 2, 2, 2);
            b.setLayoutParams(params);
            ```|
            

</div>
</details>
