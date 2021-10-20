from classes.Frame import Frame
from typing import List

class FrameUtil:
    frameList:List[Frame] = []

    def __init__(self, frameList):
        self.frameList = frameList

    def getResultFrames(self):
        return FrameUtil([self.frameList[i + 1].getResultFrame(self.frameList[i]) for i in range(len(self.frameList) - 1)])

    def getIndexList(self):
        return [frame.idx for frame in self.frameList]