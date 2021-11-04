import pickle

import matplotlib.pyplot as plt
import numpy as np

from classes.Frame import Frame
from classes.FrameUtil import FrameUtil
from setting import *


# 칸투어마다 넓이 그래프
def showAreaGraph(data):
    x = range(len(data))
    y = [d['area'] for d in data]
    plt.plot(x, y)
    plt.show()


# 프레임마다 칸투어 개수 그래프
def showContourNum(cap):
    frameCount = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
    x = range(1, frameCount, 30)
    y = []
    for i in x:
        cap.set(cv2.CAP_PROP_POS_FRAMES, i)
        ret, image = cap.read()
        data = getFrameData(image)
        y.append(len(data))
        print(len(data))

    plt.plot(x, y)
    plt.show()


#
def showStickGraph(frames, values, title):
    if len(frames) != len(values):
        raise Exception(f"프레임과 값 길이가 다릅니다 \n frame : {len(frames)} values : {len(values)}")
    x = np.arange(len(frames))
    # plt.subplot(2, 1, 1)  # nrows=2, ncols=1, index=1
    plt.xticks(x, frames)
    plt.bar(x, values)
    plt.title(title)


def showVideoInfo():
    print('Frame width:', int(cap.get(cv2.CAP_PROP_FRAME_WIDTH)))  # 비디오 가로
    print('Frame height:', int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT)))  # 비디오 세로
    print('Frame count:', int(cap.get(cv2.CAP_PROP_FRAME_COUNT)))  # 전체 프레임 수
    print('FPS:', cap.get(cv2.CAP_PROP_FPS))  # 초당 프레임 수


def saveFrameUtil():
    frames = FrameUtil([Frame(frameIdx) for frameIdx in frameIndexs])
    with open(f"C:/Users/김건오\Desktop/2021-1-CECD3-Websters-4/bookmark/resource/{videoName}.txt", 'wb') as file:  # james.p 파일을 바이너리 쓰기 모드(wb)로 열기
        pickle.dump(frames, file)


def getFrameUtilCache() -> FrameUtil:
    with open(f"C:/Users/김건오\Desktop/2021-1-CECD3-Websters-4/bookmark/resource/{videoName}.txt", 'rb') as file:  # james.p 파일을 바이너리 읽기 모드(rb)로 열기
        frames = pickle.load(file)
    return frames
