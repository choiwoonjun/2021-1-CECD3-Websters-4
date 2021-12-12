import cv2


class VideoConfig:
    videoPath:str =""
    cap=None
    frame_count:int =0
    fps:int = 0
    frameIndexs:list = []

    def __init__(self, videoPath):
        self.videoPath=videoPath
        self.cap = cv2.VideoCapture(videoPath)
        self.frame_count = int(self.cap.get(cv2.CAP_PROP_FRAME_COUNT))
        self.fps = int(self.cap.get(cv2.CAP_PROP_FPS))
        self.frameIndexs = [i for i in range(0, self.frame_count, self.fps)]



