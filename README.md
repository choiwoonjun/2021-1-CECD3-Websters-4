# 2021-1-CECD3-Websters-4
2021-1 컴퓨터공학종합설계1 03분반 Websters
비대면 환경에서의 효과적인 교육 및 비즈니스를 위한 개인 맞춤형 융합 콘텐츠 생성 기반 기술

## Team Websters
* 오지훈(팀장)
  * 김건오
  * 김태용
  * 누비아

## 프로젝트 배경
<img src="https://user-images.githubusercontent.com/66253212/122749337-2f130580-d2c8-11eb-863a-50543555666c.png" width="60%" height="60%">

- 코로나 19로 인한 비대면 사회로 접어들면서, 대부분의 수업과 회의가 원격으로 대체되어 화상회의 플랫폼들이 상용화되어 있지만,
회의 내용에 대한 자막 자동 생성 기능이나 북마크 자동 생성 기능 등 사용자를 더욱 편리하게 만들어 줄 수 있는 기능들이 부재한 상황
- 따라서 이러한 기능들을 가진 플랫폼을 개발할 경우 원격으로 진행되는 회의와 수업의 능률이 향상될 것으로 판단

## 프로젝트 목적
<img src="https://user-images.githubusercontent.com/66253212/122749115-e9563d00-d2c7-11eb-84e7-93b11b54bf1c.png" width="75%" height="75%">
<img src="https://user-images.githubusercontent.com/66253212/122748501-3980cf80-d2c7-11eb-963d-a4dcf3987bd4.png" width="75%" height="75%">

- 회의록 자동 생성, 생성된 회의록 수정, 북마크 자동 생성, 검색 및 자동 스케줄링 구현을 통해 비대면 원격 회의 및 교육의 편리함과 효율적인 사후 관리를 통하여 비대면 사회의 능률을 상승 시키는 것을 지향함

## 프로젝트 구조
<img src="https://user-images.githubusercontent.com/66253212/122749889-d5f7a180-d2c8-11eb-9807-f9628f9188cb.png" width="75%" height="75%">

## 자동 회의록 생성(auto meeting log)

### 주요 기능

- 녹화된 영상에서 Transcribe 생성
- 생성된 Transcribe 문장단위로 구분, 해당 문장과 시간 Pairing
  - 화자인식(미완)
  - 실시간 Transcribe 생성(미완)

### Tech Stack  

- ![](https://img.shields.io/badge/Frontend-React-red)
- ![](https://img.shields.io/badge/Backend-Express-blue)
- ![](https://img.shields.io/badge/Database-MongoDB-yellow)

### Requirements

- AWS Account
- Setting for AWS  SDK 
https://docs.aws.amazon.com/sdk-for-javascript/v3/developer-guide/loading-node-credentials-shared.html
- MongoDB Account

### Installation

1.  Clone the repo `git clone https://github.com/CSID-DGU/2021-1-CECD3-Websters-4.git`
2.  Go to your project folder from your terminal
3.  Run: `npm install` on both client, server folder
4. Set MongoURI on server/.env
5. Run: `npm run start` on both client, server folder


### Explanation
#### MakeTranscribe.py
- 녹화된 영상을 Transcribe으로 생성
#### Run.py
- 생성된 Transcribe을 저장
#### Load.py && Load2.py
- 생성된 Transcribe에서 문장단위로 구분, 해당 문장과 시간 Pairing
#### Search.py
- 생성된 Transcribe에서 Keyword로 검색
