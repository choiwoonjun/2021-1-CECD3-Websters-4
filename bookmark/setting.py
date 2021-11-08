import cv2

# videoName = "test1"
# videoPath = f"C:/Users/김건오\Desktop/2021-1-CECD3-Websters-4/bookmark/resource/{videoName}.mp4"
#
# cap = cv2.VideoCapture(videoPath)
#
# frame_count = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
# fps = int(cap.get(cv2.CAP_PROP_FPS))
# frameIndexs = [i for i in range(0, frame_count, fps)]

# 바이너리화 알고리즘 사용 변수
BLK_SIZE = 9  # 블럭 사이즈
C = 5  # 차감 상수

# contour 처리 최소 넓이
AREA_LIMIT = 300

# contour 비교할때 최소 거리
NEIGHBOR_DIST = 10
MIN_SIMILARILTY = 2

# 유사도 분석
CNT_RATIO = 90
AREA_RATIO = 100-CNT_RATIO

# 슬라이드 점수 threshold
SLIDE_SCORE = 35

