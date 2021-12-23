# 2021-1-CECD3-Websters-4
2021-1 컴퓨터공학종합설계1 03분반 Websters
비대면 환경에서의 효과적인 교육 및 비즈니스를 위한 개인 맞춤형 융합 콘텐츠 생성 기반 기술

## Team Websters
* 오지훈(팀장)
  * 김건오
  * 김태용
  * 누비아

## 프로젝트 배경
<img src="https://user-images.githubusercontent.com/66253212/146129895-1a7011a9-3470-4dbe-961f-216824b5ceb7.png" width="75%" height="75%">
<img src="https://user-images.githubusercontent.com/66253212/146129596-6d348fa7-cda4-45cc-8ec7-48aad8341217.png" width="75%" height="75%">

- 코로나 19로 인한 비대면 사회로 접어들면서, 대부분의 수업과 회의가 원격으로 대체되어 화상회의 플랫폼들이 상용화되어 있지만,
회의 내용에 대한 자막 자동 생성 기능이나 북마크 자동 생성 기능 등 사용자를 더욱 편리하게 만들어 줄 수 있는 기능들이 부재한 상황
- 따라서 이러한 기능들을 가진 플랫폼을 개발할 경우 원격으로 진행되는 회의와 수업의 능률이 향상될 것으로 판단

## 프로젝트 목적
<img src="https://user-images.githubusercontent.com/66253212/146129470-ee95f3c2-6155-45c1-97ba-03ca7670879b.png" width="75%" height="75%">
<img src="https://user-images.githubusercontent.com/66253212/146129146-f5a26663-4c9b-453b-adb6-766dc1c1b2a1.png" width="75%" height="75%">

- 스크립트 자동 생성, 북마크 자동 생성 플랫폼 구현을 통해 비대면 원격 회의 및 교육의 편리함과 효율적인 사후 관리를 통하여 비대면 사회의 능률을 상승 시키는 것을 지향함

## 프로젝트 구조
<img src="https://user-images.githubusercontent.com/66253212/122749889-d5f7a180-d2c8-11eb-9807-f9628f9188cb.png" width="75%" height="75%">

## 자동 회의록 생성

### 주요 기능

- 녹화된 영상에서 Transcribe 생성
- 생성된 Transcribe 문장단위로 구분, 해당 문장과 시간 Pairing
  - 화자인식(미완)
  - 실시간 Transcribe 생성(미완)

### Tech Stack  

- ![](https://img.shields.io/badge/Frontend-React-red)
- ![](https://img.shields.io/badge/Backend-Spring-blue)
- ![](https://img.shields.io/badge/Database-MySQL-yellow)

### Requirements

- AWS Account

## 북마크 자동 생성

### 주요 기능

- 녹화된 영상 화면 캡쳐
- 프레임 별로 이전 프레임과 현재 프레임의 유사도를 비교하여 차이가 일정 수치 이상이면 북마크 생성

### Installation

1. Clone the repo `git clone https://github.com/CSID-DGU/2021-1-CECD3-Websters-4.git`
2. Go to your project folder from your terminal
3. Run: `npm install` on both client, server folder
4. Install: install h2 database
5. Setting: Make application.yml in /backend/src/main/resources
```
spring:
  servlet:
    multipart:
      max-file-size: 1024MB
      max-request-size: 1024MB
  h2:
    console:
      enabled: true
  datasource:
    driver-class-name: org.h2.Driver
    url: [Your h2 database url]
    username: [Your h2 database username]
    password: [Your h2 database password]
  jpa:
    hibernate:
      # 실제 사용에서는 schema.sql 파일을 작성하고 none 으로 변경
      ddl-auto: create

file:
  upload:
    location: [Folder location to save files]

bookmark:
  generator:
    path: [Your folder]\2021-1-CECD3-Websters-4\bookmark\pythonTest.py

cloud:
  aws:
    s3:
      bucket: [S3 bucket name]
    region:
      static: ap-northeast-2
    stack:
      auto: false
    credentials:
      access-key: [Your AWS access key]
      secret-key: [Your AWS secret key]

client:
  url: http://localhost:3000
```
6. Run: `npm run start` on both client, server folder
