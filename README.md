# 2021-1-CECD3-Websters-4
2021-1 컴퓨터공학종합설계1 03분반 Websters

## Team Websters
* 오지훈(팀장)
  * 김건오
  * 김태용
  * 누비아

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