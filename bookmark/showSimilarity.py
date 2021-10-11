import time
import cv2
import matplotlib.pyplot as plt

from func.data import getData, getMismatchContours
from setting import cap

if cap.isOpened():
    start = time.time()
    frameNum = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
    frames = range(0, frameNum, 30)
    cnts = []
    prevData = []
    for frame in frames:
        cap.set(cv2.CAP_PROP_POS_FRAMES, frame)
        ret, image = cap.read()  # 두 개의 값을 반환하므로 두 변수 지정
        if not ret:  # 새로운 프레임을 못받아 왔을 때 break
            break

        data = list()
        data = getData(image)


        notFoundList = getMismatchContours(prevData, data)
        cnts.append(len(notFoundList))
        prevData = data
        # print(len(notFoundList))

    end = time.time()

    plt.plot(frames, cnts)
    plt.show()
    cap.release()  # 사용한 자원 해제
    cv2.destroyAllWindows()

    print(f"{end - start:.5f} sec")
else:
    print('cannot open the file')
