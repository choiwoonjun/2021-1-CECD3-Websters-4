from func.image import getBinaryImage, drawInBlank
from setting import *


# 이미지에서 데이터 추출
def getData(image):
    imthres = getBinaryImage(image)
    contours, hierarchy = cv2.findContours(imthres, cv2.RETR_TREE, cv2.CHAIN_APPROX_NONE)

    data = []
    for contour in contours:
        mmt = cv2.moments(contour)
        area = int(cv2.contourArea(contour))
        if mmt['m00'] == 0 or area < AREA_LIMIT:
            continue
        temp = {'x': int(mmt['m10'] / mmt['m00']), 'y': int(mmt['m01'] / mmt['m00']),
                'area': area, 'contour': contour}
        data.append(temp)

    data.sort(key=lambda a: (a['x'], a['y']))
    return data

#data1의 contour 중 data2와 일치하지 않는 리스트 반환
def getMismatchContours(data1, data2):
    l, r = 0, 0
    mismatchList = []
    for d1 in data1:
        x, y, contour = d1.get('x'), d1.get('y'), d1.get('contour')
        isFind = False

        l, r, neighbors = getNeighbors(l, r, x, y, data2)

        # drawInBlank([d1],"target")
        # drawInBlank(data2,"asd")
        # drawInBlank(neighbors,"neighbors1")
        # drawInBlank(neighbors2,"neighbors2")
        # cv2.waitKey(0)

        for neighbor in neighbors:
            match = cv2.matchShapes(contour, neighbor, cv2.CONTOURS_MATCH_I2, 0.0)
            if match < MIN_SIMILARILTY:
                isFind = True
                break

        if not isFind:
            mismatchList.append(d1)

    return mismatchList

# 주변 contours 찾는 함수
def getNeighbors(l, r, x, y, data):
    while l < len(data) and data[l].get('x') < x - NEIGHBOR_DIST:
        l += 1
    while r < len(data) and data[r].get('x') < x + NEIGHBOR_DIST:
        r += 1

    # x값을 기준으로 l, r 사이
    neighbors = [d.get('contour') for d in data[l:r+1] if y - NEIGHBOR_DIST < d.get('y') < y + NEIGHBOR_DIST]

    return l, r, neighbors

