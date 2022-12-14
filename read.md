

# 1. 유기동물입양플랫폼, 환승주인

<div>

![01](readme/img/logo.png)

</div>

<br/>

# 2. 환승주인을 만드는 사람

| <img src="https://avatars.githubusercontent.com/24siefil" width=150px><br />[진웅휘](https://github.com/woongwhee) | <img src="https://avatars.githubusercontent.com/9yujin" width=150px><br />[염서학](https://github.com/YEOMCODING) | <img src="https://avatars.githubusercontent.com/rms5213" width=150px><br />[장현정](https://github.com/HyunjeongJang) | <img src="https://avatars.githubusercontent.com/sanbonai06" width=150px><br />[김지호](https://github.com/KJeeu) | <img src="https://avatars.githubusercontent.com/ozzing" width=150px><br />[김도윤](https://github.com/kimdory) |
|:---------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------:|

<br/>

# 3. 환승주인를 만드는 기술

- **서버**: JAVA, Servlet,JDBC 
- **프론트엔드**:  JAVASCRIPT,JQUERY
- **데이터베이스**: ORACLE 11g
- **디자인 시스템**: [storybook]([bankidz.github.io/bankidz-client](https://bankidz.github.io/bankidz-client)), [Figma](https://www.figma.com/file/pF7iygMk2IXYGZxXockGY8/%5B1.0-ver%5D-%EB%94%94%EC%9E%90%EC%9D%B8?node-id=2%3A3), Theme-provider
- **버전 관리**:MAVEN,Git with GitHub
- **CI/CD**: Github-actions, Docker-compose
- **웹뷰 앱**: React Native with EXPO

<details>
<summary>디랙토리 구조</summary>
<div markdown="1">

  ```
  .
  ├──java
  │   ├──domain 
  │   │   ├──dao # 
  │   │   ├──dto # 외부api간 데이터 교환용폴더
  │   │   ├──vo # 
  │   │   └──type # enum
  │   └──web #비지니스 로직
  ├──resource
  │   ├──key # api키 관리용 xml폴더
  │   └──sql # sql문 저장용 xml폴더
  ├──webapp
  │   ├──js 자바스크립트 파일저장용폴더
  │   ├──img 이미지 리소스 저장용폴더
  │   ├──smarteditor2 스마트에디터 관련코드
  │   ├──css css파일 저장용폴더
  │   └──view# jsp파일 저장용폴더
      
file #첨부파일 저장용폴더 ($tomcathome/webapps/file)
       
  ```
</div>
</details>

<details>
<summary>Architecture</summary>
<div markdown="1">

  <img src="README.assets/178255707-814eb2ac-be3a-4350-940c-f060890c2420.jpeg" alt="KakaoTalk_Photo_2022-07-11-20-35-48" style="zoom: 67%;" />

뱅키즈의 개발 환경은 테스트 환경(dev)과 실 서비스 환경(main)이 분리되어 있습니다. 각 환경(branch)은 Github-actions, Dock-compose 기반 CI/CD 및 AWS EC2 인스턴스 기반 서버가 구축되어 있습니다. 또한, push 및 태깅 이벤트 감지를 통해 Docker-compose로 이미지 push가 trigger 됩니다. 실 서비스 환경의 이미지는 Github의 Relase 버저닝을 통해 관리됩니다.

</div>
</details>

<br/>

# 4. 주요 기능


## 4.1. 유기동물 조회

|                       회원관리 (공통)                        |                        온보딩 (공통)                         |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
|  로그인 → 로그아웃 → 카카오 소셜 로그인 → 회원 탈퇴 |      생년월일 → 프로필 → 푸시알림 동의 → 튜토리얼 → 홈       |
| <img src="https://user-images.githubusercontent.com/83692797/195271097-8045ae4e-a79e-4ad9-b58f-5813411a1414.gif" alt="회원관리" style="width:66%;" /> | <img src="https://user-images.githubusercontent.com/83692797/195271071-dcebab69-6924-4fcb-9629-37cbe5480a2b.gif" alt="온보딩 자녀" style="width:66%;" /> |

<details>
<summary>자세히</summary>
<div markdown="1">

- 
<img src="README.assets/image.svg" alt="https://velog.velcdn.com/images/24siefil/post/945daeaa-533b-4cde-95ef-a677dc4ea940/image.svg" style="zoom:67%;" />

</div>
</details>

<br/>

[//]: # ()
[//]: # (## 4.2. 홈 탭 &#40;자녀&#41;)

[//]: # ()
[//]: # (|                         홈 탭 &#40;자녀&#41;                         |                         홈 탭 &#40;자녀&#41;                         |                       알림내역 &#40;공통&#41;                        |)

[//]: # (| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |)

[//]: # (|    걷고있는 돈길 → 돈길 포기하기<br />→ 실패한 돈길 삭제     |               대기중인 돈길 → 거절된 돈길 삭제               |                         무한 스크롤                          |)

[//]: # (| <img src="https://user-images.githubusercontent.com/83692797/195265630-cdf4a7a9-9a5b-4aef-a95e-8deec1fa73c8.gif" alt="자녀 홈 걷고있는 돈길_compressed" style="zoom:33%;" /> | <img src="https://user-images.githubusercontent.com/83692797/195265658-426cec67-a1cf-4c28-b401-a89134b562e2.gif" alt="자녀 홈 대기중인 돈길" style="zoom:33%;" /> | <img src="https://user-images.githubusercontent.com/83692797/195265679-cadcc309-da94-4371-85e7-dc00ac4c420c.gif" alt="알림 내역_compressed" style="zoom:33%;" /> |)

[//]: # ()
[//]: # (<details>)

[//]: # (<summary>자세히</summary>)

[//]: # (<div markdown="1">)

[//]: # ()
[//]: # (- 홈 탭에서는 서로간 종속성을 갖는 네가지 종류의 돈길에 대한 CRUD가 가능합니다.)

[//]: # (- 홈 탭의 데이터는 React-Query 기반의 interval refetching을 통해 최신상태를 유지합니다.)

[//]: # (- 알림내역은 무한스크롤 기반으로 데이터를 지속적으로 fetch 합니다.)

[//]: # ()
[//]: # (</div>)

[//]: # (</details>)

[//]: # ()
[//]: # (<br/>)

[//]: # ()
[//]: # (## 4.3. 돈길 계약하기, 돈길 걷기 탭 &#40;자녀&#41;)

[//]: # ()
[//]: # (|                     돈길 계약하기 &#40;자녀&#41;                     |                     돈길 걷기 탭 &#40;자녀&#41;                      |)

[//]: # (| :----------------------------------------------------------: | :----------------------------------------------------------: |)

[//]: # (| 계약 대상 → 계약 상품 → 이름, 목표금액 →<br />이자율, 매주 저금액 → 서명 → 계약서 확인 |                              -                               |)

[//]: # (| <img src="https://user-images.githubusercontent.com/83692797/195265700-e4e7d239-55a1-4982-b0cc-901705412d77.gif" alt="돈길 계약하기 이자부스터 설명 모달 포함_compressed" style="width:66%;" /> | <img src="https://user-images.githubusercontent.com/83692797/195265718-01482c86-d0d1-47ac-b9d9-55ecf1ff75e9.gif" alt="돈길 걷기" style="width:66%;" /> |)

[//]: # ()
[//]: # (<br/>)

[//]: # ()
[//]: # (## 4.4. 홈 탭, 이자 내역 탭 &#40;부모&#41;)

[//]: # ()
[//]: # (|                         홈 탭 &#40;부모&#41;                         |                         홈 탭 &#40;부모&#41;                         |                     이자 내역 탭 &#40;부모&#41;                      |)

[//]: # (| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |)

[//]: # (|             각 자녀의 제안받은 돈길, 금주의 돈길             |             제안받은 돈길 → 수락하기 → 거절하기              |    지급이 필요한 이자 → 자세히 보기<br />→ 지급 완료하기     |)

[//]: # (| <img src="https://user-images.githubusercontent.com/83692797/199890409-8cf9d313-3acf-42c9-9103-d460056ce45f.gif" alt="스켈레톤 홈 캐싱" style="zoom:33%;" /> | <img src="https://user-images.githubusercontent.com/83692797/195265781-908cd209-af8a-4e3c-bb48-39bd493484f6.gif" alt="부모 홈 제안받은 돈길_compressed" style="zoom:33%;" /> | <img src="https://user-images.githubusercontent.com/83692797/195265803-d84d6626-71cf-465f-9faf-ea4bd9e29e0d.gif" alt="이자내역 이자지급_compressed" style="zoom:33%;" /> |)

[//]: # ()
[//]: # (<details>)

[//]: # (<summary>자세히</summary>)

[//]: # (<div markdown="1">)

[//]: # ()
[//]: # (- 선택된 자녀의 데이터만 optimistic하게 fetch 하여 효율적으로 리소스를 사용합니다.)

[//]: # (- fetch된 데이터는 cache 되어 추후 로딩을 최적화 합니다.)

[//]: # ()
[//]: # (</div>)

[//]: # (</details>)

[//]: # ()
[//]: # (<br/>)

[//]: # ()
[//]: # (## 4.5. 가족 초대, 마이페이지 탭 &#40;공통&#41;)

[//]: # ()
[//]: # (|                       가족 초대 &#40;공통&#41;                       |                         설정 &#40;공통&#41;                          |)

[//]: # (| :----------------------------------------------------------: | :----------------------------------------------------------: |)

[//]: # (| 딥링크를 통한 가족 초대 → 가족그룹 참여<br />→ 가족그룹 나가기 → 가족그룹 만들기 |                              -                               |)

[//]: # (| <img src="https://user-images.githubusercontent.com/83692797/195767677-30b89754-fff1-41c4-a7cd-b28b2e5442fa.gif" alt="가족 초대" style="zoom:33%;width:66%;" /> | <img src="https://user-images.githubusercontent.com/83692797/195269471-2aa52131-de2d-4598-a45b-48c7372611bb.gif" alt="설정 자녀_compressed" style="zoom:33%;width:66%;" /> |)

[//]: # ()
[//]: # (<br/>)


# 5. 서비스 개발기

- **진웅휘** | [환승주인 개발기]()

<br/>
