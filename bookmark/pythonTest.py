import sys

from classes.FrameUtil import FrameUtil
from classes.VideoConfig import VideoConfig

# path = "C:/Users/김건오/Desktop/2021-1-CECD3-Websters-4/backend/src/main/resources/735fbb83-b0a3-4a59-bc54-f1a6a1ec591d.mp4"

filePath=input()
config=VideoConfig(filePath)
frameUtil = FrameUtil(config)
boomarks = frameUtil.getBoomarks()
print(boomarks)