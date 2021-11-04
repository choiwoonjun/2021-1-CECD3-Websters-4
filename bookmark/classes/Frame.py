from __future__ import annotations
import cv2
import numpy as np

from func.image import getImage, getBinaryImage, getRandomColor
from setting import AREA_LIMIT, NEIGHBOR_DIST, MIN_SIMILARILTY


class Contour:
    x, y, area, shape = 0, 0, 0, 0

    def __init__(self, x, y, area, shape):
        self.x = x
        self.y = y
        self.area = area
        self.shape = shape

    def show(self, title):
        img1 = 255 * np.ones(shape=[720, 1280, 3], dtype=np.uint8)
        cv2.drawContours(img1, self.shape, -1, getRandomColor(), 4)
        cv2.imshow(title, img1)

class Frame:
    idx = 0
    contours = []
    misMatchContours = []
    score = 0

    def __init__(self, idx, contours=None):
        self.idx = idx
        # print(idx)
        if contours==None:
            image = getImage(idx)
            imthres = getBinaryImage(image)
            contours, hierarchy = cv2.findContours(imthres, cv2.RETR_TREE, cv2.CHAIN_APPROX_NONE)
            temp=[]
            for contour in contours:
                mmt = cv2.moments(contour)
                area = int(cv2.contourArea(contour))
                if mmt['m00'] == 0 or area < AREA_LIMIT:
                    continue
                c = Contour(int(mmt['m10'] / mmt['m00']), int(mmt['m01'] / mmt['m00']), area, contour)
                temp.append(c)
            self.contours=temp
            self.contours.sort(key=lambda a: (a.x, a.y))
        else:
            self.contours=contours
            # print(len(contours),len(self.contours))

    def show(self, title):
        img1 = 255 * np.ones(shape=[720, 1280, 3], dtype=np.uint8)
        for contour in self.contours:
            cv2.drawContours(img1, contour.shape, -1, getRandomColor(), 4)
        cv2.imshow(title, img1)

    def setNeighborRange(self,l, r, x, nowContours):
        while l < len(nowContours) and nowContours[l].x < x - NEIGHBOR_DIST:
            l += 1
        while r < len(nowContours) and nowContours[r].x < x + NEIGHBOR_DIST:
            r += 1

        return l, r

    def setMismatchContours(self, prevFrame: Frame) -> Frame:
        l, r = 0, 0
        prevContours, nowContours = prevFrame.contours, self.contours
        prevMissed, nowMissed = [True] * (len(prevContours)), [True] * (len(nowContours))
        for idx1, contour1 in enumerate(prevContours):
            x1, y1, shape1, area1 = contour1.x, contour1.y, contour1.shape, contour1.area

            l, r = self.setNeighborRange(l, r, x1, nowContours)
            for idx2 in range(l, r):
                contour2 = nowContours[idx2]
                x2, y2, shape2, area2 = contour2.x, contour2.y, contour2.shape, contour2.area

                if not y1 - NEIGHBOR_DIST < y2 < y1 + NEIGHBOR_DIST:
                    continue
                if not area1 / 2 < area2 < area1 * 2:
                    continue
                match = cv2.matchShapes(shape1, shape2, cv2.CONTOURS_MATCH_I2, 0.0)

                if match < MIN_SIMILARILTY:
                    prevMissed[idx1] = False
                    nowMissed[idx2] = False
                    # break

        mismatchContours = [prevContours[i] for i in range(len(prevMissed)) if prevMissed[i]] \
                           + [nowContours[i] for i in range(len(nowMissed)) if nowMissed[i]]
        self.misMatchContours=mismatchContours

