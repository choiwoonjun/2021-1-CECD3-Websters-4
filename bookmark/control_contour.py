# 프레임 안의 칸투어들 관찰
from func.util import *

frameNum = 3540

frame = Frame(frameNum)
idx = 0
contours = frame.contours
contourLen = len(contours)

while True:
    print(f"{idx} contour")
    contours[idx].show("contour")
    key=cv2.waitKey(0)
    if key == 110 and idx > 0:  # n 이전
        idx -= 1
    elif key == 109 and idx < contourLen - 1:  # m 다음
        idx += 1
    elif key == 106 and idx + 10 < contourLen - 1:  # j 앞으로 점프
        idx += 10
    elif key == 104 and idx - 10 > 0:  # h 뒤로 점프
        idx -= 10
    else:
        break