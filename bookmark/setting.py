import cv2

videoPath = "./resource/test3.mp4"  #사용할 동영상
cap = cv2.VideoCapture(videoPath)


# 바이너리화 알고리즘 사용 변수
BLK_SIZE = 9  # 블럭 사이즈
C = 5  # 차감 상수

# contour 처리 최소 넓이
AREA_LIMIT = 100

# contour 비교할때 최소 거리
NEIGHBOR_DIST = 100
MIN_SIMILARILTY = 0.1



