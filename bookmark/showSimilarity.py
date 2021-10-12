import time
import cv2
import matplotlib.pyplot as plt

from func.data import getData, getMismatchContours
from func.util import getDataList
from setting import cap

if cap.isOpened():
    start = time.time()
    frameNum = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
    cnts = []
    prevData = []
    areas = []

    dataList = getDataList()

    for data in dataList:
        misMatchContours = getMismatchContours(prevData, data)
        cnts.append(len(misMatchContours))
        areas.append(sum([c.get("area") for c in misMatchContours]))
        prevData = data

    end = time.time()


    # plt.plot(frames,areas)
    # plt.show()
    # plt.plot(frames, cnts)
    plt.show()
    cap.release()  # 사용한 자원 해제
    cv2.destroyAllWindows()

    print(f"{end - start:.5f} sec")
else:
    print('cannot open the file')
