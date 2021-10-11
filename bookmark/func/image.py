# 이미지에 그리기, 출력 등 함수
from setting import *
import numpy as np
import random

def showImage(image, title):
    # image = cv2.resize(image, (960, 540))
    cv2.imshow(title, image)

def getBinaryImage(image):
    imgray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    imthres = cv2.adaptiveThreshold(imgray, 255, cv2.ADAPTIVE_THRESH_MEAN_C, cv2.THRESH_BINARY, BLK_SIZE, C)
    return imthres


def drawContour(image, data):
    for d in data:
        cv2.drawContours(image, [d['contour']], -1, getRandomColor(), 4)

def drawText(image, data): # 중심에 인덱스 작성
    for idx, d in enumerate(data):
        cv2.putText(image, '%d' % idx, (d['x'], d['y']), cv2.FONT_HERSHEY_PLAIN, 1, (0, 0, 255), 1)



def drawInBlank(data,title): # 백지에 contour 그림
    img1 = 255 * np.ones(shape=[720, 1280, 3], dtype=np.uint8)
    cv2.drawContours(img1, [d.get("contour") for d in data], -1, (0, 255, 0), 4)
    cv2.imshow(title, img1)
    #cv2.waitKey(0)


def getRandomColor():
    b = random.randrange(1, 255)
    g = random.randrange(1, 255)
    r = random.randrange(1, 255)
    return b, g, r