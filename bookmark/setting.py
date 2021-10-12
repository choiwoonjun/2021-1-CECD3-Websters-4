import cv2

videoName = "test3"
videoPath = f"./resource/{videoName}.mp4"  #사용할 동영상

cap = cv2.VideoCapture(videoPath)

frame_count = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
fps = int(cap.get(cv2.CAP_PROP_FPS))
frames = [i for i in range(0,frame_count,fps)]

# 바이너리화 알고리즘 사용 변수
BLK_SIZE = 9  # 블럭 사이즈
C = 5  # 차감 상수

# contour 처리 최소 넓이
AREA_LIMIT = 100

# contour 비교할때 최소 거리
NEIGHBOR_DIST = 100
MIN_SIMILARILTY = 0.1



