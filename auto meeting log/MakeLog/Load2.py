import json
from collections import OrderedDict


class LoadClass2:

    def __init__(self):
        fileloc = 'D:/dongguk2021-1/종설1/auto meeting log/API_TEST.json'

        with open(fileloc, 'r', encoding='UTF-8-sig') as f:
            json_data = json.load(f)
        # print(json.dumps(json_data, indent="\t", ensure_ascii=False))

        #transcribe된 json파일에서 순수 speech부분만 가져오기
        core = "".join(str(e) for e in json_data["results"]["transcripts"])
        length = len(core)
        core = core[16:length - 2]
        # print(core)

        #transcribe된 json파일에서 순수 시작 시간부분만 가져오기
        all_start_time = []  # 모든 시작시간 저장
        self.start_time = []  # 문장 시작시간 저장
        item = "".join(str(e) for e in json_data["results"]["items"])
        item = item.split(',')
        # for out in item:
        #     print(out)

        for i in range(0, len(item)):
            if 'start_time' in item[i]:
                loc = item[i].find("\'start_time\': ")
                all_start_time.append(item[i][loc + 15:len(item[i]) - 1])

        # for out in start_time:
        #     print(out)

        self.speech = core.split('. ')  # speech 한 문장씩 저장
        self.start_time.append(all_start_time[0])
        index = 0

        #문장의 시작 start time 저장
        for i in range(0, len(self.speech)):
            self.speech[i] += "."
            if i != len(self.speech) - 1:
                self.start_time.append(all_start_time[index + len(self.speech[i].split(" "))])
                index += len(self.speech[i].split(" "))

        time = "time"
        speech = "speech"
        #문장과 start time 페어링링
        self.meetinglog = []
        for i in range(0, len(self.speech)):
            self.meetinglog.append('{ '+time+' : ' + self.start_time[i] + ' , '+speech+' : ' + self.speech[i] + ' }')

        #전체회의록, 시작시간, speech json파일로 저장
        self.output_file = "D:/dongguk2021-1/종설1/auto meeting log/API_TEST_LOG2.json"
        file_data = OrderedDict()
        file_data["meeting_log"] = self.meetinglog

        with open(self.output_file, 'w', encoding="utf-8") as make_file:
            json.dump(file_data, make_file, ensure_ascii=False, indent="\t")


    def prtlog(self):
        with open(self.output_file, 'r', encoding='UTF-8-sig') as f:
            json_data = json.load(f)
        print(json.dumps(json_data, indent="\t", ensure_ascii=False))

    def getspeech(self):
        return self.speech

    def gettime(self):
        return self.start_time

    def opjson(self):
        with open(self.output_file, 'r', encoding='UTF-8-sig') as f:
            json_data = json.load(f)
        print(json.dumps(json_data, indent="\t", ensure_ascii=False))