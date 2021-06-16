from MakeTranscribe import TranscribeClass
from urllib import request
import json


def main():
    b = TranscribeClass()
    uri = b.get()

    #url을 통해 json 다운로드드
   download = "D:/dongguk2021-1/종설1/auto meeting log/API_TEST.json"
    request.urlretrieve(uri, download)

    # with open(download, 'wb') as f:
    #     json_data = json.load(f)
    # print(json.dump(json_data, indent="\t"))


if __name__ == '__main__':
    main()
