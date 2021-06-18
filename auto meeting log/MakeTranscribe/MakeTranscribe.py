from __future__ import print_function
import time
import boto3


class TranscribeClass:

    def __init__(self):
        transcribe = boto3.client("transcribe")
        #transcribe job name -> 입력받거나 argv로 대체할 계획
        job_name = "API-test"
        #transcribe을 진행할 영상이 저장된 벜킷 주소  -> 입력받거나 argv로 대체할 계획
        job_uri = "put your bucket & mp4 file"
        transcribe.start_transcription_job(
            TranscriptionJobName=job_name,
            Media={"MediaFileUri": job_uri},
            MediaFormat="mp4",
            LanguageCode="ko-KR"
        )
        check = False;
        while True:
            status = transcribe.get_transcription_job(TranscriptionJobName=job_name)
            if status['TranscriptionJob']['TranscriptionJobStatus'] in ['COMPLETED', 'FAILED']:
                check = True;
                break
            print("Not ready yet...")
            time.sleep(5)
        self.TranscriptFile_uri = status['TranscriptionJob']["Transcript"]['TranscriptFileUri']
        print(self.TranscriptFile_uri)
        print(status)

    def get(self):
        return self.TranscriptFile_uri
