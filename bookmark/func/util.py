import matplotlib.pyplot as plt
from setting import *
from func.data import getData

# 칸투어마다 넓이 그래프
def showAreaGraph(data):
    x = range(len(data))
    y = [d['area'] for d in data]
    plt.plot(x, y)
    plt.show()

# 프레임마다 칸투어 개수 그래프
def showContourNum(cap):
    frameCount = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
    x = range(1,frameCount,30)
    y = []
    for i in x:
        cap.set(cv2.CAP_PROP_POS_FRAMES, i)
        ret, image = cap.read()
        data = getData(image)
        y.append(len(data))
        print(len(data))

    plt.plot(x, y)
    plt.show()


def showVideoInfo():
    print('Frame width:', int(cap.get(cv2.CAP_PROP_FRAME_WIDTH)))  # 비디오 가로
    print('Frame height:', int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT)))  # 비디오 세로
    print('Frame count:', int(cap.get(cv2.CAP_PROP_FRAME_COUNT)))  # 전체 프레임 수
    print('FPS:', cap.get(cv2.CAP_PROP_FPS))  # 초당 프레임 수