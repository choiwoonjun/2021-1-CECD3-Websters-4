import sys

from classes.FrameUtil import FrameUtil
from classes.VideoConfig import VideoConfig

#test
# filePath='C:/Users/김건오/Desktop/2021-1-CECD3-Websters-4/backend/src/main/resources/test.mp4'

filePath=input()
config=VideoConfig(filePath)
frameUtil = FrameUtil(config)
boomarks = frameUtil.getBoomarks()
boomarks = [str(int(b/config.fps)) for b in boomarks ]
result = " ".join(boomarks)
print(result)