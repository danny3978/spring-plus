# SPRING PLUS


### PROJECT NAME : 내배캠 Spring Plus주차 개인 과제

##### 회원가입, 로그인, 일정, 댓글, 날씨 데이터 가져오는 기능 있음


1. Security를 사용하여 인가 작업을 진행 함
2. RestTemplate를 사용하여 날씨 데이터를 가져 옴




### Trouble Shooting

1. QueryDSL를 사용하려고 build.gradle에 QueryDSL 의존성을 추가 해서 빌드를 했는데 build 폴더에 각 엔티티의 Q클래스가 생성은 되었지만
사용이 안 되는 문제가 발생해서 구글링을 해보니 Q클래스가 소스 루트로 잡히지 않아서 발생한 문제인걸로 확인이 되었다.
소슬 루트로 잡히기 위해 build.gradle에 직접 sourceSets을 사용해서 문제를 해결하였다.
2. sourceSets {
   main.java.srcDirs += "$projectDir/build/generated"
   }