import time
import cv2
import matplotlib.pyplot as plt

from classes.FrameUtil import FrameUtil
from func.util import getFrameUtilCache, showStickGraph
from setting import cap, frameIndexs

if cap.isOpened():
    start = time.time()

    # frameUtil = FrameUtil(None)
    frameUtil = getFrameUtilCache()
    boomarks = frameUtil.getBoomarks()
    print(boomarks)
    end = time.time()
    print(f"{end - start:.5f} sec")

    graphFrames = [frame for frame in frameUtil.frames if frame.score>20]
    x = [frame.idx for frame in graphFrames]
    scores = [frame.score for frame in graphFrames]
    showStickGraph(x,scores,"cnt")


    plt.show()
    cap.release()  # 사용한 자원 해제
    cv2.destroyAllWindows()
else:
    print('cannot open the file')
