import math

from classes.Frame import Frame
from typing import List

from setting import frameIndexs, CNT_RATIO, AREA_RATIO


class FrameUtil:
    frames:List[Frame] = []

    def __init__(self, cache:List[Frame]):
        if cache is None:
            self.frames = [Frame(frameIdx) for frameIdx in frameIndexs]
        else:
            self.frames = cache

    def setMismatchContours(self):
        for i in range(len(self.frames) - 1):
            self.frames[i + 1].setMismatchContours(self.frames[i])

    def getIndexList(self):
        return [frame.idx for frame in self.frames]

    def setScores(self):
        cnts = [len(frame.misMatchContours) for frame in self.frames]
        maxCnt = max(cnts)
        cnts = [cnt / maxCnt * CNT_RATIO for cnt in cnts]

        areas = [sum([math.sqrt(contour.area) for contour in frame.misMatchContours]) for frame in self.frames]
        maxArea = max(areas)
        areas = [area / maxArea * AREA_RATIO for area in areas]

        for i in range(len(self.frames)):
            self.frames[i].score = cnts[i]+areas[i]


    def getBoomarks(self):
        self.setMismatchContours()
        self.setScores()
        print(self)



