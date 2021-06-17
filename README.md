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

### Explanation
#### MakeTranscribe.py
- 녹화된 영상을 Transcribe으로 생성
#### Run.py
- 생성된 Transcribe을 저장
#### Load.py && Load2.py
- 생성된 Transcribe에서 문장단위로 구분, 해당 문장과 시간 Pairing
#### Search.py
- 생성된 Transcribe에서 Keyword로 검색
