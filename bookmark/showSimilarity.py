import math
import time
import cv2
import matplotlib.pyplot as plt
import numpy as np
from func.util import getFrameUtil, showStickGraph
from setting import cap, frameIndexs

if cap.isOpened():
    start = time.time()
    frameNum = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
    cnts = []
    prevData = []
    areas = []

    frames = getFrameUtil()

    frames.frameList = frames.frameList

    resultFrames = frames.getResultFrames()
    # frameList = frames.frameList
    # resultFrameList = [getResultFrame(frameList[i], frameList[i + 1]) for i in range(len(frameList) - 1)]
    # resultFrameList = [m for m in resultFrameList if len(m) != 0]

    resultFrameList=resultFrames.frameList
    resultFrameList = [frame for frame in resultFrameList if len(frame.contours) != 0]
    resultFrames.frameList = resultFrameList

    for frame in resultFrameList:
        contours = frame.contours
        cnts.append(len(contours))
        areas.append(sum([math.sqrt(contour.area) for contour in contours]))

    end = time.time()

    x = np.arange(len(resultFrameList))

    maxArea = max(areas)
    areas = [math.sqrt(area) for area in areas]
    areas = [area / maxArea * 50 for area in areas]

    maxCnts = max(cnts)
    cnts = [cnt / maxCnts * 50 for cnt in cnts]
    results = [areas[i] + cnts[i] for i in range(len(areas))]

    # graphData = [{"frame":resultFrameList[i]["frame"], "value":cnts[i]} for i in range(len(cnts)) if cnts[i] != 0]
    #
    #
    # # graphData = [{"frame":frameDataList[i]["frame"], "value":results[i]} for i in range(len(results)) if results[i]>10]
    #
    #
    # filteredFrames = [m.get("frame") for m in graphData]
    # values = [m.get("value") for m in graphData]
    # showStickGraph(filteredFrames, values, "result")

    # showStickGraph(resultFrames.getIndexList(),results,"result")
    showStickGraph(resultFrames.getIndexList(),results,"cnt")

    # plt.subplot(2, 2, 1)
    # showStickGraph(filteredFrames,cnts,"cnt")
    #
    # plt.subplot(2, 2, 2)
    # showStickGraph(filteredFrames, areas, "area")
    # plt.subplot(2, 2, 3)
    # showStickGraph(filteredFrames, results, "result")

    plt.show()
    cap.release()  # 사용한 자원 해제
    cv2.destroyAllWindows()

    # print([i * 30 for i, result in enumerate(results) if result > 10])

    print(f"{end - start:.5f} sec")
else:
    print('cannot open the file')
