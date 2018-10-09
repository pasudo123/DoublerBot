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
```
