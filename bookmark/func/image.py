# 이미지에 그리기, 출력 등 함수
from setting import *
import random

def getImage(frame):
    cap.set(cv2.CAP_PROP_POS_FRAMES, frame)
    ret, image = cap.read()
    if not ret:
        raise Exception("이미지 캡쳐 실패")
    return image

def getBinaryImage(image):
    imgray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    imthres = cv2.adaptiveThreshold(imgray, 255, cv2.ADAPTIVE_THRESH_MEAN_C, cv2.THRESH_BINARY, BLK_SIZE, C)
    return imthres


def drawText(image, data): # 중심에 인덱스 작성
    for idx, d in enumerate(data):
        cv2.putText(image, '%d' % idx, (d['x'], d['y']), cv2.FONT_HERSHEY_PLAIN, 1, (0, 0, 255), 1)


def getRandomColor():
    b = random.randrange(1, 255)
    g = random.randrange(1, 255)
    r = random.randrange(1, 255)
    return b, g, r