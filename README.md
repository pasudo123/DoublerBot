# DoublerBot

## 참고자료
* [ Google Cloud Platform 가입 ](http://pasudo123.tistory.com/202?category=754607)
* [ Google Cloud Platform Instance Create ](http://pasudo123.tistory.com/203?category=754607)
* [ Google Cloud SDK & Eclipse Plug in ](http://pasudo123.tistory.com/210?category=754607)
* [ 플러스친구 카카오톡봇 ](https://center-pf.kakao.com/)
* [ 카카오톡 플러스친구 API 2.0 설명 ](https://github.com/plusfriend/auto_reply)

## 개발환경
* Eclipse Version: 2018-09 (4.9.0)
* Apache Tomcat 8
* Java Version 1.8.0_181
* Spring Framework 4.3.19.
* javax.servlet-api 3.1.0
* jackson-databind 2.9.6
* junit 4.11
* mockito-core 2.13.0
* jsoup 1.10.3

## 개정이력
```SQL
[2018 10 03]
1. weatherBot --> doublerBot 으로 변경
2. 구글 클라우드 서비스 --> 원룸 공인 IP 내 포트포워딩 설정.

[2018 10 04]
1. "Say Hello" 구문 찍기 성공
2. API POST /message URL 에서 Message 별 DTO(Data Transfer Object) 추가

[2018 10 09]
1. Jsoup 이용, NAVAR 검색창에서 나오는 날씨 내용 부분 크롤링

[2018 10 13]
1. Jsoup 이용했는데, HTTP URL Connection Error 나타남. 연결이 안됨
```

## 공지사항
```SQL
2018년 11월 30일 API형 스마트채팅 신규 등록 중단
2019년 12월 2일 API형 스마트채팅 완전 종료
```
위와 같은 공지를 카카오톡 플러스 친구 깃헙 내용에서 볼 수 있다. 2019년 12월 3일부터는 더 이상 카카오톡 봇을 이용할 수 없다. 자세한 내용은 [여기](https://github.com/plusfriend/auto_reply)에 들어가서 확인해야 한다.

## ISSUE
Jsoup 을 이용하여 크롤링을 진행하는 경우, 아래와 같은 에러를 마주했다.
```JAVA
HTTP error fetching URL. Status=403, URL=http:// blahblahblahblah ~ 
```
위와 같은 문제는 Jsoup 을 이용하는 경우, 유저권한이 없는 상태에서 URL 과 커넥션을 맺으려고 하기때문이다. 따라서 아래와 같이 코드를 넣어주었다.
```JAVA
Connection connection = Jsoup.connect(newURL);
connection.userAgent("Chrome");
document = connection.get();
```

## 스크린샷
<img src="https://github.com/pasudo123/DoublerBot/blob/master/Image/conversation_1.PNG" width="40%" />
<img src="https://github.com/pasudo123/DoublerBot/blob/master/Image/conversation_2.PNG" width="39.8%" />
